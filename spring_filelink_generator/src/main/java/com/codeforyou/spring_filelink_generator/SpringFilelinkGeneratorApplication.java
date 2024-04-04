package com.codeforyou.spring_filelink_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class SpringFilelinkGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFilelinkGeneratorApplication.class, args);
	}

	@GetMapping("/downloadxml")
	public void postMethodName(HttpServletResponse response) throws Exception {
		try {
			File file1 = new File("/home/madan/Downloads/samplexml/SBI_20240302.xml");
			String fileName = file1.getName();
			// String filePath = "/home/madan/Downloads/samplexml/SBI_20240302.xml";
			// Read file content
			StringBuilder content = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
				String line;
				while ((line = reader.readLine()) != null) {
					content.append(line).append("\n");
				}
			}
			String fileContent = content.toString();

			// Set content type and headers for the response
			response.setContentType("application/xml");
			response.setHeader("Content-Disposition", "attachment; filename= "+ fileName+"");

			// Write file content to the response
			response.getWriter().write(fileContent);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
