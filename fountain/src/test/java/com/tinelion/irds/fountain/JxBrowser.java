package com.tinelion.irds.fountain;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.DefaultNetworkDelegate;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.Charset;


public class JxBrowser {

//    static {
//        try {
//            Field e = a.class.getDeclaredField("e");
//            e.setAccessible(true);
//            Field f = a.class.getDeclaredField("f");
//            f.setAccessible(true);
//            Field modifersField = Field.class.getDeclaredField("modifiers");
//            modifersField.setAccessible(true);
//            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
//            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
//            e.set(null, new BigInteger("1"));
//            f.set(null, new BigInteger("1"));
//            modifersField.setAccessible(false);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) throws InterruptedException {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

//        JFrame frame = new JFrame("JxBrowser - Hello World");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(view, BorderLayout.CENTER);
//        frame.setSize(400, 2000);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

        NetworkService networkService = browser.getContext().getNetworkService();
//        networkService.setResourceHandler(new ResourceHandler() {
//            //返回false则不加载
//            @Override
//            public boolean canLoadResource(ResourceParams params) {
//                System.out.println("URL: " + params.getURL());
//                System.out.println("Type: " + params.getResourceType());
//                return (params.getResourceType() == ResourceType.IMAGE);
//            }
//        });

//        browser.addLoadListener(new LoadAdapter() {
//            @Override
//            public void onFinishLoadingFrame(FinishLoadingEvent event) {
//                if (event.isMainFrame()) {
//                    String filePath = "D:\\Test\\index.html";
//                    String dirPath = "D:\\Test\\resources";
//                    try {
//                        Thread.sleep(1000*10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Boolean success = event.getBrowser().saveWebPage(filePath, dirPath, SavePageType.COMPLETE_HTML);
//                    System.out.println(success);
//                }
//            }
//        });
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    int i = 1000;

                    while (true) {
                        event.getBrowser().executeJavaScript(
                                "window.scrollTo(document.body.scrollWidth," + (i += 2000) + ");");
                        try {
                            Thread.sleep(3 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        String filePath = "D:\\Test\\index.html";
        String dirPath = "D:\\Test\\resources";
        browser.loadURL("https://m.baidu.com/sf/vsearch?pd=image_content&word=汽车" + "&tn=vsearch&atn=page");
        Thread.sleep(20 * 1000);
        Boolean success = browser.saveWebPage(filePath, dirPath, SavePageType.COMPLETE_HTML);
        System.out.println(success);
    }
}
