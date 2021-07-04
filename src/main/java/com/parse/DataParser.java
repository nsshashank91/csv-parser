package com.parse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/data")
public class DataParser {

    @Autowired
    List<OWASP> dataList;


    @PostConstruct
    public void readCsv() throws MalformedURLException {

        URL url = new URL("https://raw.githubusercontent.com/OWASP/ASVS/master/4.0/docs_en/OWASP%20Application%20Security%20Verification%20Standard%204.0.2-en.csv");

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

        try (CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for (CSVRecord csvRecord : csvParser) {
                OWASP owasp = new OWASP();
                owasp.setChapterId(csvRecord.get("chapter_id"));
                owasp.setChapterName(csvRecord.get("chapter_name"));
                owasp.setSectionId(csvRecord.get("section_id"));
                owasp.setSectionName(csvRecord.get("section_name"));
                owasp.setReqId(csvRecord.get("req_id"));
                owasp.setReqDescription(csvRecord.get("req_description"));
                owasp.setLevel1(csvRecord.get("level1"));
                owasp.setLevel2(csvRecord.get("level2"));
                owasp.setLevel3(csvRecord.get("level3"));
                owasp.setCwe(csvRecord.get("cwe"));
                owasp.setNist(csvRecord.get("nist"));
                System.out.println(owasp);
                dataList.add(owasp);
            }
            dataList.remove(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/chapterId/{chapterId}")
    public List<OWASP> getDataByChapterId(@PathVariable String chapterId) {
        List<OWASP> chapterList = new ArrayList<>();
        dataList.stream().forEach(owasp -> {
            if (owasp.getChapterId().equals(chapterId)) {
                chapterList.add(owasp);

            }
        });
        return chapterList;
    }

    @GetMapping("/cwe/{cwe}")
    public List<OWASP> getDataByCwe(@PathVariable String cwe) {

        return dataList.stream().filter(data->data.getCwe().equals(cwe)).collect(Collectors.toList());

    }
}
