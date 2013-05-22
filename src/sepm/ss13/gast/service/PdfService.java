package sepm.ss13.gast.service;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sepm.ss13.gast.dao.BestellungDAO;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.dao.DBConnector;
import sepm.ss13.gast.dao.JDBCBestellungDAO;
import sepm.ss13.gast.dao.JDBCRechnungDAO;
import sepm.ss13.gast.dao.RechnungDAO;
import sepm.ss13.gast.domain.Bestellung;
import sepm.ss13.gast.domain.Rechnung;

public class PdfService {
	
	private BestellungDAO bestellungDAO;
	private RechnungDAO rechnungDAO;
	
	private Rechnung r;
	private ArrayList<Bestellung> bestellungen;
	
	private static String FILE = "bill.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,	Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,	Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	public PdfService(DBConnector dbCon) {
		Connection con = dbCon.getConnection();
		this.bestellungDAO = new JDBCBestellungDAO(con);
		this.rechnungDAO = new JDBCRechnungDAO(con);
	}

	public void createPDF(Rechnung r) throws DAOException {
		this.r = r;
		Bestellung bSearch = new Bestellung();
		bSearch.setRechnung(r.getId());
		this.bestellungen=bestellungDAO.search(bSearch);
		
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(FILE));
				document.open();
				addMetaData(document); 
				addContent(document);
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		private void addMetaData(Document document) {
			document.addTitle("created bill");
			document.addAuthor("QSE_03");
			document.addCreator("QSE_03");
		}
	
		private void addContent(Document document) throws DocumentException {
			
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
			
			PdfPCell c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			c1 = new PdfPCell(new Phrase("Preis brutto"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
	
			for (Bestellung b : bestellungen) {
				//String[] t = s.split(",");
				table.addCell(b.getPname());
				PdfPCell c = new PdfPCell(new Phrase(b.getPreis()));
				c.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(c);
			}
			document.add(table);
			
			Paragraph summe = new Paragraph();
			addEmptyLine(summe, 2);
			summe.setAlignment(Element.ALIGN_RIGHT);
			//summe.add(new Paragraph("Summe brutto: " + r.getSummeBrt()));
			//summe.add(new Paragraph("davon MWST: " + (r.getSummeBrt() - r.getSummeNet()), subFont));
			//summe.add(new Paragraph("Summe netto: " + r.getSummeNet(), subFont));
			document.add(summe);
			
			Paragraph gruss = new Paragraph();
			addEmptyLine(gruss, 3);
			gruss.add(new Paragraph("Wir danken für Ihr kommen!", catFont));
			gruss.setAlignment(Element.ALIGN_CENTER);
			document.add(gruss);
			
		}
	
		private static void createList(Section subCatPart) {
			List list = new List(true, false, 10);
			list.add(new ListItem("First point"));
			list.add(new ListItem("Second point"));
			list.add(new ListItem("Third point"));
			subCatPart.add(list);
		}
	
		private static void addEmptyLine(Paragraph paragraph, int number) {
			for (int i = 0; i < number; i++) {
				paragraph.add(new Paragraph(" "));
			}
		}

		public Rechnung getR() {
			return r;
		}

		public void setR(Rechnung r) {
			this.r = r;
		}
}
