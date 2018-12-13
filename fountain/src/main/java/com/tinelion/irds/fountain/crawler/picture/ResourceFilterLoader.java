package com.tinelion.irds.fountain.crawler.picture;

import com.teamdev.jxbrowser.chromium.ResourceHandler;
import com.teamdev.jxbrowser.chromium.ResourceParams;
import com.teamdev.jxbrowser.chromium.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 不需要的静态资源不加载
 */
@Component
public class ResourceFilterLoader implements ResourceHandler {
    @Autowired
    private IBrowserProxy browserProxy;

    @Override
    public boolean canLoadResource(ResourceParams params) {

        if(params.getResourceType() == ResourceType.SCRIPT
                || params.getResourceType() == ResourceType.FAVICON
                || params.getResourceType() == ResourceType.FONT_RESOURCE
                || params.getResourceType() == ResourceType.STYLESHEET)
        {
            browserProxy.getLinkSet().add(params.getURL());

            return false;
        }

        return true;
    }
}
