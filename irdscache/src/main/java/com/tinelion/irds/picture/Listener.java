package com.tinelion.irds.picture;


import com.tinelion.irds.fountain.crawler.Controller;
import com.tinelion.irds.fountain.crawler.picture.CrawlerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/5 8:27
 */
public class Listener implements Runnable {
    public Listener() {
    }

    @Autowired
    Controller controller;

    @Override
    public void run() {
        PratendMethod pratendMethod = new PratendMethod();
        while (true) {
            try {
                if (PictureUtil.getFileQuantity() > 7) {
                   controller.stop();
                } else if (PictureUtil.getFileQuantity() < 2) {
                    //调用开启方法
                   controller.Start(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
