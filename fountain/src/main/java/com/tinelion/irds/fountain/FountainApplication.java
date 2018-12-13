package com.tinelion.irds.fountain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class FountainApplication {

    public static void main(String[] args) {
     /*   Map<Integer, String> map = new HashMap<>();
        map.put(1, "zzx");
        map.put(2, "zll");
        System.out.println("*********");
        String s="s";
        String t="t";
        String z=t+s;
        System.out.println(z);
        System.out.println(map.get(2));*/
        /*Random random = new Random();
        for (int i = 0; i < 10; i++) {

            int k=random.nextInt();
            System.out.println(k);
        }*/
        System.setProperty("java.awt.headless", "true");
        SpringApplication.run(FountainApplication.class, args);
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(2);
    }
}
