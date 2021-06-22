package com.parse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/data")
public class DataParser{

    @GetMapping
    public void readCsv() throws MalformedURLException {

        URL url = new URL("https://raw.githubusercontent.com/OWASP/ASVS/master/4.0/docs_en/OWASP%20Application%20Security%20Verification%20Standard%204.0.2-en.csv");

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

        try (CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for (CSVRecord csvRecord : csvParser) {
                String firstName = csvRecord.get("chapter_id");
                String lastName = csvRecord.get("chapter_name");
                String email = csvRecord.get("section_id");
                String phoneNumber = csvRecord.get("section_name");

                System.out.println(firstName + "," + lastName + "," + email + "," + phoneNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
