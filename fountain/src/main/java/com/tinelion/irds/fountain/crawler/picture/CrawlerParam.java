package com.tinelion.irds.fountain.crawler.picture;

import java.util.List;
import java.util.Map;

/**
 * 爬虫前端参数
 */
public class CrawlerParam {

    private String filePath;

    private Map<String,List> searchEngineAndKeyWords;

    private List<String> websites;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setSearchEngineAndKeyWords(Map<String, List> searchEngineAndKeyWords) {
        this.searchEngineAndKeyWords = searchEngineAndKeyWords;
    }

    public Map<String, List> getSearchEngineAndKeyWords() {
        return searchEngineAndKeyWords;
    }

    public void setWebsites(List<String> websites) {
        this.websites = websites;
    }

    public List<String> getWebsites() {
        return websites;
    }
}
