package com.tinelion.irds.guard.process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.tinelion.irds.guard.process.FileMethod.copyDir;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/10 10:29
 */

public class GetFile implements Runnable {

    /**
     * 开启一个线程输出图片流
     */

    public void run() {
        List<String> list = new ArrayList<>();
        while (true) {
            File file = new File("C:\\IRDS\\pic\\resource");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] name = file.list();
            for (String s : name
                    ) {
                list.add(s);
            }
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).substring(list.get(i).lastIndexOf(".") + 1).equals("jpg")) {
                    File fileD = new File("C:\\IRDS\\pic\\resource" + list.get(i));
                    fileD.delete();
                    list.remove(i);
                } else {

                }
            }
            try {
                copyDir("f:\\Pictures", "f:\\Pictures_Two");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < list.size() - 1; i++) {
                File fileD = new File("C:\\IRDS\\pic\\resource" + list.get(i));
                fileD.delete();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
