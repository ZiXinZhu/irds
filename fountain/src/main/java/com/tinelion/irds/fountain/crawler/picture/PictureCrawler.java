package com.tinelion.irds.fountain.crawler.picture;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * 图片爬虫
 */
@Component
public class PictureCrawler extends WebCrawler {

    private static IBrowserProxy browserProxy;

    /**
     * 正则匹配指定的后缀文件
     */
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|jpeg|mp3|mp3|zip|gz|ico))$");


    public static void init(IBrowserProxy browserProxy){
        PictureCrawler.browserProxy = browserProxy;
    }
    /**
     * 这个方法决定这个url是否需要添加进待爬取序列
     * 浏览器对本页链接进行了筛选，如果不需要爬取则加入到本页Set
     * 爬虫检查这个链接，如果存在于Set则不加入待爬取序列
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase(); // 得到小写的url
        Set linkSet = browserProxy.getLinkSet();

        return !linkSet.contains(href) && !FILTERS.matcher(href).matches();
    }

    /**
     * 爬虫分析完一个页面会到这里
     * 每3s检查一次next（浏览器那边是否保存完成），如果没有完成则继续等待
     */
    @Override
    public void visit(Page page) {
        while (!browserProxy.getFinished()) {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public IBrowserProxy getBrowserProxy() {
        return browserProxy;
    }

    public void setBrowserProxy(IBrowserProxy browserProxy) {
        PictureCrawler.browserProxy = browserProxy;
    }
}
