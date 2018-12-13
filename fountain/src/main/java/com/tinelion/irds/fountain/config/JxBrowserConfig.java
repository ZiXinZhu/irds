package com.tinelion.irds.fountain.config;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.NetworkService;
import com.teamdev.jxbrowser.chromium.ResourceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 浏览器配置
 */
@Configuration
public class JxBrowserConfig {

    @Bean
    public Browser getBrowser(ResourceHandler resourceHandler) {
        Browser browser = new Browser();
        NetworkService networkService = browser.getContext().getNetworkService();
        networkService.setResourceHandler(resourceHandler);

        return browser;
    }
}
