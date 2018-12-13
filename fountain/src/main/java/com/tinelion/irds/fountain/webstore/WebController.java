package com.tinelion.irds.fountain.webstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/1 20:52
 */
@RestController
@RequestMapping("web")
public class WebController {

    @Autowired
    IWebService webService;

    @PostMapping("/select")
    public String select() throws IOException {
        return webService.select();
    }
    @PostMapping("/add")
    public String add(HttpServletRequest httpServletRequest, @RequestParam("web") String web) throws IOException {
        Random random = new Random();
        int key = random.nextInt(1000);
        return webService.add(key, web);
    }
    @PostMapping("del")
    public String del(HttpServletRequest httpServletRequest, @RequestParam("key") int key) throws IOException {

        return webService.del(key);
    }
    @PostMapping("change")
    public String change(HttpServletRequest httpServletRequest, @RequestParam("key") int key, @RequestParam("web") String web) throws IOException {
        return webService.change(key, web);
    }
}
