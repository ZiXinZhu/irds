package com.tinelion.irds.fountain.config;

import com.tinelion.irds.fountain.crawler.picture.IRDSPageFetcher;
import com.tinelion.irds.fountain.crawler.picture.JxBrowserProxy;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.ExecutorService;

/**
 * 爬虫配置
 */
@Configuration
public class IRDSCrawlConfig {

    /**
     * 爬虫规则设置
     *
     * @return
     */
    @Order(99999)
    @Bean
    public CrawlConfig crawlConfig() {
        CrawlConfig config = new CrawlConfig(); // 实例化爬虫配置
        config.setMaxDepthOfCrawling(5);
        config.setResumableCrawling(true);
        config.setIncludeBinaryContentInCrawling(false);
        //是否允许爬取二进制文件，false，因为文件下载功能已经交给浏览器
        config.setIncludeBinaryContentInCrawling(false);
        config.setCrawlStorageFolder("c:/IRDS/init");

        return config;
    }

    /**
     * 爬虫工作设置
     * 不遵守robot.txt
     *
     * @return
     */
    @Bean
    public RobotstxtConfig robotstxtConfig() {
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);

        return robotstxtConfig;
    }

    /**
     * 爬虫服务设置
     *
     * @return
     */
    @Bean
    public RobotstxtServer robotstxtServer(RobotstxtConfig robotstxtConfig, IRDSPageFetcher pageFetcher) {
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        return robotstxtServer;
    }

    /**
     * 爬虫页面抓取工具
     *
     * @return
     */
    @Bean
    public IRDSPageFetcher pageFetcher(CrawlConfig crawlConfig, JxBrowserProxy jxBrowserProxy,
                                       ExecutorService executorService)
    {
        IRDSPageFetcher pageFetcher = new IRDSPageFetcher(crawlConfig);
        pageFetcher.setBrowser(jxBrowserProxy);
        pageFetcher.setExecutorService(executorService);

        return pageFetcher;
    }

    /**
     * 爬虫控制器
     *
     * @return
     * @throws Exception
     */
    @Bean
    public CrawlController crawlController(RobotstxtConfig robotstxtConfig, IRDSPageFetcher pageFetcher,
                                           CrawlConfig crawlConfig) throws Exception
    {
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);

        return controller;
    }
}
