package net.codeforyou.openpdf_image_demo;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.net.URL;

import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
public class OpenpdfImageDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenpdfImageDemoApplication.class, args);
	}

	@GetMapping("/generate-pdf")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {

		String imagePath1 = "/home/madan/anand/Spring-Boot-Projects/openpdf_image_demo/images/image1.jpg";

		try {

			Document document = new Document();

			document.open();

			File file1 = new File(imagePath1);
			BufferedImage image1 = ImageIO.read(file1);

			Image pdfImage1 = Image.getInstance(image1, null);
			pdfImage1.scaleToFit(300, 300); // Adjust the image size as needed
			document.add(pdfImage1);

			// BufferedImage image1 = ImageIO.read(new URL(imageUrl1));
			// if (image1 != null) {
			// Image pdfImage1 = Image.getInstance(image1, null);
			// pdfImage1.scaleToFit(300, 300); // Adjust the image size as needed
			// document.add(pdfImage1);
			// }

			// Image image1 = Image.getInstance(resource1.getURL());
			// document.add(image1);

			// Set the content type and headers
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=example.pdf");

			// Create the PDF document

			PdfWriter.getInstance(document, response.getOutputStream());

			// Open the document and add content

			document.add(
					new Paragraph("Hello, this is a sample PDF generated using OpenPDF in a Spring Boot application."));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
