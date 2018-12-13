package com.tinelion.irds.guard.tool;

import com.tinelion.irds.guard.process.GetFile;
import lombok.extern.slf4j.Slf4j;

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
public class RealListeners implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("[zzx]RealListeners start successfully.");
        GetFile getFile=new GetFile();
        getFile.run();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
