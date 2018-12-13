package com.tinelion.irds.tool;

import com.tinelion.irds.picture.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/5 20:37
 */
@WebListener
@Slf4j
public class RealListener implements ServletContextListener {
    @Autowired
    Listener listener;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("[zzx]RealListener start successfully.");
        listener.run();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
