package com.tinelion.purge.customer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/12 16:51
 */
public class UsePictuer {
    List<String> list = new ArrayList<>();

    public File run(){
        while (true) {
            String path="f:\\Pictures_Two";
            File file = new File(path);
            if (!file.exists()) {
                (new File(path)).mkdir();
            }
            if(file.list()!=null) {
                String[] name = file.list();
                for (String s : name
                        ) {
                    list.add(s);
                }


            }else {
                return null;
            }
            for (int i = 0; i < list.size() - 1; i++) {
                File fileD = new File("f:\\Pictures_Two\\" + list.get(i));
                return fileD;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void del() {
        while (true) {
            String path="f:\\Pictures_Two";
            File file = new File(path);
            if (!file.exists()) {
                (new File(path)).mkdir();
            }
            if (file.list() != null) {
                String[] name = file.list();
                for (String s : name
                        ) {
                    list.add(s);
                }

            for (int i = 0; i < list.size() - 1; i++) {
                File fileD = new File("f:\\Pictures_Two\\" + list.get(i));
                fileD.delete();


            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            else {

            }
        }
    }

}
