package com.tinelion.irds.fountain.crawler.picture;

import com.teamdev.jxbrowser.chromium.Browser;

import java.util.Set;

public interface IBrowserProxy {

    void handlURL() throws InterruptedException;

    void setTaskLink(String url);

    Set<String> getLinkSet();

    void setFilePath(String filePath);

    Browser getBrowser();

    void setBrowser(Browser browser);

    Boolean getFinished();

    void setFinished(Boolean finished);
}
