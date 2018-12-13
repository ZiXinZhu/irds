package com.tinelion.irds.guard;

import com.tinelion.irds.guard.process.GetFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class GuardApplication {

    public static void main(String[] args) {

        SpringApplication.run(GuardApplication.class, args);
    }
}
