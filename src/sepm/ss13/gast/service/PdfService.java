package sepm.ss13.gast.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Konfiguration;
import sepm.ss13.gast.domain.Rechnung;

public class PdfService {
	
	private Service s;
	
	private Rechnung r;
	private ArrayList<Bestellung> bestellungen;
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,	Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,	Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	public PdfService(Service s) {
		this.s=s;
	}

	public File getFile(Rechnung r) throws DAOException {
		byte[] pdf = s.searchRechung(r).get(0).getPdf();
		File f = null;
		
		try {
			f = File.createTempFile("gast",".pdf");
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(pdf);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
	}
	
	public void createPDF(Rechnung r) throws DAOException {
		if(r==null) throw new IllegalArgumentException();
		
		this.r = r;
		Bestellung bSearch = new Bestellung();
		bSearch.setRechnung(r.getId());
		this.bestellungen=s.searchBestellung(bSearch);
		
			try {
				Document document = new Document();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PdfWriter.getInstance(document,baos);
				document.open();
				addMetaData(document); 
				addContent(document);
				document.close();
				
				r.setPdf(baos.toByteArray());
				s.updateRechnung(r);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		private void addMetaData(Document document) {
			document.addTitle("Rechnung "+r.getId());
			document.addAuthor("GAST");
			document.addCreator("GAST");
		}
	
		private void addContent(Document document) throws DocumentException, DAOException, IOException {
			
			Paragraph titel = new Paragraph();
			titel.add(new Paragraph("Rechnung", catFont));
			titel.add(new Paragraph(r.getDatum().toString(), subFont));
			addEmptyLine(titel, 2);
			titel.setAlignment(Element.ALIGN_CENTER);
			document.add(titel);
	
			// t.setBorderColor(BaseColor.GRAY);
			// t.setPadding(4);
			// t.setSpacing(4);
			// t.setBorderWidth(1);
	
			PdfPTable table = new PdfPTable(2);
			
			PdfPCell c1 = new PdfPCell(new Phrase("Bezeichnung"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			c1 = new PdfPCell(new Phrase("Preis"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			for (Bestellung b : bestellungen) {
				table.addCell(b.getPname());
				table.addCell("� "+String.valueOf((float)b.getPreis()/100));
			}
			document.add(table);
			
			Paragraph summe = new Paragraph();
			addEmptyLine(summe, 2);
			summe.setAlignment(Element.ALIGN_RIGHT);
			summe.add(new Paragraph("Summe: � "+(float)this.calculateSum()/100));
			summe.add(new Paragraph("davon MWSt: � "+this.calculateTaxSum()/100));
			document.add(summe);
			
			Paragraph gruss = new Paragraph();
			addEmptyLine(gruss, 3);
			gruss.add(new Paragraph("Wir danken f�r Ihr kommen!", catFont));
			
			Konfiguration k = s.loadKonfiguration();
			gruss.add(new Paragraph(k.getName()));
			gruss.add(new Paragraph(k.getAdresse()));
			gruss.add(new Paragraph(k.getTel()));
			Jpeg logo = new Jpeg(k.getLogo());
			logo.scaleToFit(100,100);
			logo.setAlignment(Jpeg.ALIGN_CENTER);
			gruss.add(logo);
			
			gruss.setAlignment(Element.ALIGN_CENTER);
			document.add(gruss);
			
		}
	
		private static void addEmptyLine(Paragraph paragraph, int number) {
			for (int i = 0; i < number; i++) {
				paragraph.add(new Paragraph(" "));
			}
		}
		
		private int calculateSum() {
			int summe=0;
			for(Bestellung b:bestellungen) {
				summe+=b.getPreis();
			}
			
			return summe;
		}
		
		private float calculateTaxSum() throws IllegalArgumentException, DAOException {
			float summe=0;
			
			for(Bestellung b:bestellungen) {
				summe+=b.getPreis()*b.getSteuer()/100;
			}
			
			return summe;
		}
}
