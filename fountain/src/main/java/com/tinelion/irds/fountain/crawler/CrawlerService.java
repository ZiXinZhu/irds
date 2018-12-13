package com.tinelion.irds.fountain.crawler;

import com.tinelion.irds.fountain.crawler.picture.CrawlerParam;
import com.tinelion.irds.fountain.crawler.picture.IBrowserProxy;
import com.tinelion.irds.fountain.crawler.picture.PictureCrawler;
import com.tinelion.irds.fountain.webstore.IWebService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬虫服务类，爬取任务的控制
 */
@Service
public class CrawlerService implements ICrawlerService {
    //默认爬虫线程数:1
    private static final int CRAW_NUM = 1;

    private static final String SEED = "https://m.baidu.com/sf/vsearch?pd=image_content&word=风景&tn=vsearch&atn=page";
    private Map<String, CrawlController> crawlerMap = new HashMap<>();
    @Autowired
    IWebService webService;
    @Autowired
    private CrawlConfig config;
    @Autowired
    private CrawlController crawlController;
    @Autowired
    private IBrowserProxy browserProxy;


    /**
     * 开始执行爬取任务
     *
     * @param crawlerParam
     * @throws Exception
     */
    public Boolean start(CrawlerParam crawlerParam) {
        String rootFolder = crawlerParam.getFilePath();

        if (rootFolder == null || rootFolder.equals("")) {
            rootFolder = "c:/IRDS"; // 定义爬虫数据存储位置
        }
        List<String> seeds = getSeed(crawlerParam);
        if (seeds.size() == 0) {
            seeds.add(SEED);
        }
        for (String seed : seeds) {
            crawlController.addSeed(seed);
        }
        config.setCrawlStorageFolder(rootFolder); // 设置爬虫文件存储位置
        crawlerMap.put(PictureCrawler.class.getName(), crawlController);
        PictureCrawler.init(browserProxy);
        crawlController.start(PictureCrawler.class, CRAW_NUM);

        return true;
    }


    /**
     * 暂停爬取任务
     *
     * @param crawlerClass
     */
    public Boolean pause(Class crawlerClass) {
        CrawlController controller = crawlerMap.get(crawlerClass.getName());
        if (controller != null) {
            controller.waitUntilFinish();

            return true;
        }
        return false;
    }

    /**
     * 停止爬取任务
     *
     * @param crawlerClass
     */
    public Boolean stop(Class crawlerClass) {
        CrawlController controller = crawlerMap.get(crawlerClass.getName());
        if (controller != null) {
            controller.shutdown();

            return true;
        }
        return false;
    }

    private List<String> getSeed(CrawlerParam crawlerParam) {
        List<String> seeds = new ArrayList<>();
        Map<String, List> searchEngines = crawlerParam.getSearchEngineAndKeyWords();
        List<String> websites = crawlerParam.getWebsites();

        if (searchEngines != null && searchEngines.size() > 0) {
            for (Map.Entry<String, List> engineAndKeyWords : searchEngines.entrySet()) {
                String url = engineAndKeyWords.getKey();
                List keyWords = engineAndKeyWords.getValue();

                if (keyWords.size() == 0) {
                    continue;
                }
                for (Object keyWord : keyWords) {
                    String link = this.replaceKeyWord(url, (String) keyWord);

                    if (link != null && !link.equals("")) {
                        seeds.add(link);
                    }
                }
            }
        }
        if (websites != null && websites.size() > 0) {
            seeds.addAll(websites);
        }
        return seeds;
    }

    private String replaceKeyWord(String url, String keyWord) {
        String placeHolder = "@";
        if (!url.contains(placeHolder) || keyWord == null || keyWord.equals("")) {
            return null;
        }

        return url.replaceAll(placeHolder, keyWord);
    }
}
