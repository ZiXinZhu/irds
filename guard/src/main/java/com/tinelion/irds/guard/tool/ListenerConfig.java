package com.tinelion.irds.guard.tool;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/5 21:20
 */
@Configuration
public class ListenerConfig implements WebMvcConfigurer {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new RealListeners());
        System.out.println("listeners");
        return srb;
    }
}
