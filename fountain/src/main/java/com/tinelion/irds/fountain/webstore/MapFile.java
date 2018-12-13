package com.tinelion.irds.fountain.webstore;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/1 21:20
 */
@Component
public class MapFile {

    /**
     * 读取Map
     *
     * @throws IOException
     */
    public static Map readsh() throws IOException {
        Map<Integer, String> map = new HashMap<>();
        String path = "e:\\Upan\\Projects\\irds\\fountain\\src\\main\\resources\\webDao.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        // read
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
       /* if(str==null){
            str="{0:'www.baidu.com'}";
            map.put(0, "www.baidu.com");
            String st = JSONObject.toJSONString(map);
            //清除
            FileOutputStream fs = new FileOutputStream("e:\\Upan\\MyProjects\\irds\\irds\\fountain\\src\\main\\resources\\webDao.txt");

            String paths = "e:\\Upan\\MyProjects\\irds\\irds\\fountain\\src\\main\\resources\\webDao.txt";
            File files = new File(paths);
            if (!files.exists()) {
                files.getParentFile().mkdirs();
            }
            files.createNewFile();
            // write
            FileWriter fw = new FileWriter(files, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.flush();
            bw.close();
            fw.close();
        }*/
        map = (Map) JSONObject.parse(str);

        return map;
    }


}
