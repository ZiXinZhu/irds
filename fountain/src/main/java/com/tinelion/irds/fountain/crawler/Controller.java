package com.tinelion.irds.fountain.crawler;

import com.tinelion.irds.fountain.crawler.picture.CrawlerParam;
import com.tinelion.irds.fountain.crawler.picture.PictureCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬虫控制器
 */
@RestController
@RequestMapping("/crawler")
public class Controller {
    @Autowired
    private ICrawlerService crawlerService;

    @RequestMapping("/start")
    public Boolean Start(CrawlerParam crawlerParam){

        try {
            return crawlerService.start(crawlerParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/stop")
    public Boolean stop(){
        return crawlerService.stop(PictureCrawler.class);
    }
}
