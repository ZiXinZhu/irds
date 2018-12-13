package com.tinelion.irds.picture;

import java.io.File;
import java.io.FileInputStream;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/4 21:42
 */
public class PictureUtil {

    public static int getFileQuantity() throws Exception {
        String path = "C:\\IRDS\\pic\\resource";
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = null;
        } else {
            file.createNewFile();
            System.out.println("文件不存在");
        }
        File[] tempList = file.listFiles();
        return tempList.length;
    }

    public void controller() {

    }

}
