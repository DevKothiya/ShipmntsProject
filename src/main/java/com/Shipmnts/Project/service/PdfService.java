package com.Shipmnts.Project.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class PdfService {

    public void generatePdf(Map<String,Object> data, String filePath){
        try(FileOutputStream byteArrayOutputStream=new FileOutputStream(filePath)){
            PdfWriter writer=new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf=new PdfDocument(writer);
            Document document=new Document(pdf);
            for(Map.Entry<String,Object> entry: data.entrySet()){
                document.add(new Paragraph(entry.toString()));
            }
            document.close();

        }catch (Exception e){
            throw new RuntimeException("Failed");
        }
    }
}
