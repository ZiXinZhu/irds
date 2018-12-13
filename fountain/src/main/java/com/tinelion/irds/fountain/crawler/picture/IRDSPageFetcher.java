package com.tinelion.irds.fountain.crawler.picture;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.exceptions.PageBiggerThanMaxSizeException;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.url.URLCanonicalizer;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * 爬虫的页面拉取类
 */
public class IRDSPageFetcher extends PageFetcher {

    private JxBrowserProxy browser;

    private ExecutorService executorService;

    public IRDSPageFetcher(CrawlConfig config) {
        super(config);
    }


    /**
     * 抓取页面
     * 一个URL发送两次请求，一次在这里用httpClient发送，一次用浏览器发送
     *
     * @param webUrl
     * @return
     * @throws InterruptedException
     * @throws IOException
     * @throws PageBiggerThanMaxSizeException
     */
    @Override
    public PageFetchResult fetchPage(WebURL webUrl) throws InterruptedException, IOException, PageBiggerThanMaxSizeException {
        PageFetchResult fetchResult = new PageFetchResult();
        String toFetchURL = webUrl.getURL();
        HttpUriRequest request = null;

        PageFetchResult var19;
        try {
            request = this.newHttpUriRequest(toFetchURL);
            if (this.config.getPolitenessDelay() > 0) {
                Object var5 = this.mutex;
                synchronized (this.mutex) {
                    long now = (new Date()).getTime();
                    if (now - this.lastFetchTime < (long) this.config.getPolitenessDelay()) {
                        Thread.sleep((long) this.config.getPolitenessDelay() - (now - this.lastFetchTime));
                    }

                    this.lastFetchTime = (new Date()).getTime();
                }
            }
            //清除前次页面不需要爬取的链接set
            browser.getLinkSet().clear();
            //浏览器发送请求
            browser.setTaskLink(toFetchURL);
            //浏览器异步执行
            executorService.submit(browser);
            //Fetcher发送请求
            CloseableHttpResponse response = this.httpClient.execute(request);
            fetchResult.setEntity(response.getEntity());
            fetchResult.setResponseHeaders(response.getAllHeaders());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 301 && statusCode != 302 && statusCode != 300 && statusCode != 303 && statusCode != 307 && statusCode != 308) {
                if (statusCode >= 200 && statusCode <= 299) {
                    fetchResult.setFetchedUrl(toFetchURL);
                    String uri = request.getURI().toString();
                    if (!uri.equals(toFetchURL) && !URLCanonicalizer.getCanonicalURL(uri).equals(toFetchURL)) {
                        fetchResult.setFetchedUrl(uri);
                    }

                    if (fetchResult.getEntity() != null) {
                        long size = fetchResult.getEntity().getContentLength();
                        if (size == -1L) {
                            Header length = response.getLastHeader("Content-Length");
                            if (length == null) {
                                length = response.getLastHeader("Content-length");
                            }

                            if (length != null) {
                                size = (long) Integer.parseInt(length.getValue());
                            }
                        }

                        if (size > (long) this.config.getMaxDownloadSize()) {
                            response.close();
                            throw new PageBiggerThanMaxSizeException(size);
                        }
                    }
                }
            } else {
                Header header = response.getFirstHeader("Location");
                if (header != null) {
                    String movedToUrl = URLCanonicalizer.getCanonicalURL(header.getValue(), toFetchURL);
                    fetchResult.setMovedToUrl(movedToUrl);
                }
            }

            fetchResult.setStatusCode(statusCode);
            var19 = fetchResult;
        } finally {
            if (fetchResult.getEntity() == null && request != null) {
                request.abort();
            }

        }

        return var19;
    }
    public void setBrowser(JxBrowserProxy browser) {
        this.browser = browser;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

}
