package com.tinelion.purge;

import com.tinelion.purge.customer.UsePictuer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PurgeApplication {

    public static void main(String[] args) {
        UsePictuer usePictuer=new UsePictuer();
        usePictuer.run();
       // usePictuer.del();
        SpringApplication.run(PurgeApplication.class, args);
    }
}
