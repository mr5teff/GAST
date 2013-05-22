package sepm.ss13.gast.domain;

import java.util.ArrayList;
import java.util.Date;
import java.io.FileOutputStream;
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

public class Rechnung  {
	private Integer id;
	private Date datum;
	private ArrayList<String> bestellungen;
	private double summeNet;
	private double  summeBrt;
	private int trinkgeld;
	
	private static String FILE = "bill.pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,	Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,	Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	
	
// Rechnungskonstruktoren---------------------------------------------
	
	// Alter Konstruktor wegen JDBC
	public Rechnung(int i, Date date, int j) {
		// TODO Auto-generated constructor stub
	}
	
	// Rechnung erstellen aus der DB
	public Rechnung(Integer id, Date datum, String bestellungenString, double summeNet, double summeBrt) {
		this.id = id;
		this.datum = datum;
		this.summeBrt = summeBrt;
		this.summeNet = summeNet;
		this.createProdukte(bestellungenString);
	}

	// Rechnung erstellen aus der Bestellung
	public Rechnung(ArrayList<Bestellung> bestellListe) {
		this.datum = new Date();		
		this.createProdukte(bestellListe);
		createPDF();
	}
	
	
	
// Methoden zum erstellen der Produktliste----------------------------
	
	// Produkte aus String basteln
	private void createProdukte(String bestellungenString) {
		this.bestellungen = new ArrayList<String>();
		
		String[] s = bestellungenString.split(",");
		for (int i = 0; i < s.length; i+=2) {
			bestellungen.add(s[i] + "," + s[i+1]);
		}
	}

	// Produkte aus Liste basteln
	private void createProdukte(ArrayList<Bestellung> bestellListe) {
		this.bestellungen = new ArrayList<String>();
		
		summeNet = 0;
		summeBrt = 0;
		
		for (Bestellung p : bestellListe) {
			summeBrt += p.getPreis();
			bestellungen.add(p.getPname() + "," + p.getPreis());
		}	
		summeNet = summeBrt / 1.1;
	}
	
	public String getProdukteString() {
		String result = "";
		for (String s : bestellungen) {
			result += s + ",";
		}
		
		return result;
	}
	
	
	
// PDF erstellen -----------------------------------------------------
	
	private void createPDF() {
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
		titel.add(new Paragraph(datum.toString(), subFont));
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

		for (String s : bestellungen) {
			String[] t = s.split(",");
			table.addCell(t[0]);
			PdfPCell c = new PdfPCell(new Phrase(t[1]));
			c.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(c);
		}
		document.add(table);
		
		Paragraph summe = new Paragraph();
		addEmptyLine(summe, 2);
		summe.setAlignment(Element.ALIGN_RIGHT);
		summe.add(new Paragraph("Summe brutto: " + summeBrt));
		summe.add(new Paragraph("davon MWST: " + (summeBrt - summeNet), subFont));
		summe.add(new Paragraph("Summe netto: " + summeNet, subFont));
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
	
	
	
// Getter u Setter----------------------------------------------------	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getTrinkgeld() {
		return trinkgeld;
	}

	public void setTrinkgeld(int trinkgeld) {
		this.trinkgeld = trinkgeld;
	}
}
