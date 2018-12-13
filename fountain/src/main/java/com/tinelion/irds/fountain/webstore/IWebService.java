package com.tinelion.irds.fountain.webstore;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by:  Vivian
 * Date: 2018/7/1 20:52
 */
public interface IWebService {
    String add(int key,String web) throws IOException;
    String select() throws IOException;
    String del(int key)throws IOException;
    String change(int key,String web) throws IOException;
}
