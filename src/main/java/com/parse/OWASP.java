package com.parse;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OWASP {

    String chapterId ;
    String chapterName ;
    String sectionId;
    String sectionName;
    String reqId ;
    String reqDescription;
    String level1 ;
    String level2 ;
    String level3 ;
    String cwe ;
    String nist;


}
