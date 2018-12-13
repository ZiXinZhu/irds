package com.tinelion.irds.fountain.crawler;


import com.tinelion.irds.fountain.crawler.picture.CrawlerParam;

/**
 * 爬虫服务
 */
public interface ICrawlerService {
    /**
     * 开始执行爬取任务
     *
     * @throws Exception
     */
    Boolean start(CrawlerParam crawlerParam) throws Exception;


    /**
     * 暂停爬取任务
     *
     * @param crawlerClass
     */
    Boolean pause(Class crawlerClass);

    /**
     * 停止爬取任务
     *
     * @param crawlerClass
     */
    Boolean stop(Class crawlerClass);
}
