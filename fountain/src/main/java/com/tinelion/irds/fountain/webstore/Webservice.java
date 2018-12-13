package com.tinelion.irds.fountain.webstore;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/1 20:52
 */
@Service
public class Webservice implements IWebService {
    public Webservice() {

    }

    /**
     * 查询网站
     *
     * @param
     * @throws IOException
     */
    @Override
    public String select() throws IOException {
        Map map = MapFile.readsh();
        if (map != null) {
            String s = JSONObject.toJSONString(map);
            return s;
        }
        return null;
    }

    /**
     * 添加网站
     *
     * @throws IOException
     */
    @Override
    public String add(int key, String web) throws IOException {
        Map<Integer, String> map = new HashMap<>();
        if (MapFile.readsh() != null) {
            map.putAll(MapFile.readsh());
        }
        if (!map.containsValue(web) && !map.containsKey(key)) {
            map.put(key, web);
            String str = JSONObject.toJSONString(map);
            //清除
            FileOutputStream fs = new FileOutputStream("e:\\Upan\\Projects\\irds\\fountain\\src\\main\\resources\\webDao.txt");

            String path = "e:\\Upan\\Projects\\irds\\fountain\\src\\main\\resources\\webDao.txt";
            File file = new File(path);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            // write
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);

            bw.flush();
            bw.close();
            fw.close();
        }
        return new Webservice().select();
    }

    /**
     * 删除网站
     *
     * @param key
     * @return
     * @throws IOException
     */
    @Override
    public String del(int key) throws IOException {
        Map map = new HashMap();
        map = MapFile.readsh();
        if (map != null) {
            for (int i = 0; i < map.size(); i++) {
                map.remove(key);
                break;
            }
            FileOutputStream fs = new FileOutputStream("e:\\Upan\\Projects\\irds\\fountain\\src\\main\\resources\\webDao.txt");
            String s = JSONObject.toJSONString(map);
            String path = "e:\\Upan\\Projects\\irds\\fountain\\src\\main\\resources\\webDao.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            // write
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);

            bw.flush();
            bw.close();
            fw.close();

            return new Webservice().select();
        }
        return null;
    }


    @Override
    public String change(int key, String web) throws IOException {
        Map map = new HashMap();
//        map = MapFile.readsh();
//        map.replace(key,web);
        new Webservice().del(key);
        new Webservice().add(key, web);

        /*FileOutputStream fs = new FileOutputStream("e:\\Upan\\MyProjects\\irds\\irds\\fountain\\src\\main\\resources\\webDao.txt");
        String str = JSONObject.toJSONString(map);
        String path = "e:\\Upan\\MyProjects\\irds\\irds\\fountain\\src\\main\\resources\\webDao.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(str);
        bw.flush();
        bw.close();
        fw.close();
*/
        return new Webservice().select();
    }


}
