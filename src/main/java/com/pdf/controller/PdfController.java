package com.pdf.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @GetMapping("/generate-pdf")
    public void generatePdf() throws IOException, DocumentException {
        // Generate the filename with the starting name "SATISH"
        String filename = "SATISH_" + System.currentTimeMillis() + ".pdf";

        // Set the project directory where the PDF will be saved
        String projectDirectory = System.getProperty("user.dir");

        // Create the full path of the PDF file
        String filePath = projectDirectory + "/" + filename;

        // Create the PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Add the heading at the top center
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph heading = new Paragraph("Heading", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        // Add the table
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        // Add table headers
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        table.addCell(new PdfPCell(new Phrase("Header 1", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Header 2", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Header 3", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Header 4", headerFont)));

        // Add table rows
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        for (int i = 0; i < 3; i++) {
            table.addCell(new PdfPCell(new Phrase("Row " + (i + 1) + ", Column 1", cellFont)));
            table.addCell(new PdfPCell(new Phrase("Row " + (i + 1) + ", Column 2", cellFont)));
            table.addCell(new PdfPCell(new Phrase("Row " + (i + 1) + ", Column 3", cellFont)));
            table.addCell(new PdfPCell(new Phrase("Row " + (i + 1) + ", Column 4", cellFont)));
        }
        document.add(table);

        // Add the heading with a paragraph
        Paragraph subHeading = new Paragraph("Sub Heading", headingFont);
        document.add(subHeading);
        Paragraph paragraph = new Paragraph("This is a paragraph.");
        document.add(paragraph);

        // Close the document
        document.close();

        System.out.println("PDF saved at: " + filePath);
    }
}
