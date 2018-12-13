package com.tinelion.irds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class IrdscacheApplication {



    public static void main(String[] args) {


        SpringApplication.run(IrdscacheApplication.class, args);
    }
}
