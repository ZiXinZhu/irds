package com.tinelion.irds.fountain.crawler.picture;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.SavePageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class JxBrowserProxy implements IBrowserProxy, Runnable {
    @Autowired
    private Browser browser;
    //不需要爬取的链接Set
    private Set<String> linkSet = new LinkedHashSet<>();

    private String filePath = "c:\\IRDS\\pic";

    private String url;

    private Boolean finished = false;

    @Override
    public void run() {
        try {
            while (url != null && !url.equals("")) {
                handlURL();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTaskLink(String url) {
        this.url = url;
    }

    /**
     * 链接处理
     */
    public void handlURL() throws InterruptedException {
        this.finished = false;
        browser.getCacheStorage().clearCache();
        browser.loadURL(url);
        Thread.sleep(2 * 1000);

        String html = filePath + "\\index.html";
        String resourcePath = filePath + "\\resource";
        int want = 0;
        int actual = 0;

        do {
//            browser.saveWebPage(html, resourcePath, SavePageType.COMPLETE_HTML);
            browser.executeJavaScript("window.scrollTo(document.body.scrollWidth," + (want += 2000) + ");");
            Thread.sleep(2 * 1000);
//            actual = browser.executeJavaScriptAndReturnValue(" window.scrollY;").asNumber().getInteger();
            actual++;
        } while (actual < 8);   //Math.abs(want - actual) < 4000
        browser.saveWebPage(html, resourcePath, SavePageType.COMPLETE_HTML);
        browser.getCacheStorage().clearCache();
        this.finished = true;
        url = null;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Set<String> getLinkSet() {
        return linkSet;
    }

    public void setLinkSet(Set linkSet) {
        this.linkSet = linkSet;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
