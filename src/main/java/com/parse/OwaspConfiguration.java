package com.parse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OwaspConfiguration {

    @Bean
    public List<OWASP> getDataList(){
        return new ArrayList<OWASP>();
    }

}
