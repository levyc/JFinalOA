package com.levy.oa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* 这个类提供获取和设置配置文件中的值等功能 */
public class PropertyTool {
    Properties properties = null;
 //   FileOutputStream fos = null;
    FileInputStream fis = null;

    /**
     * @author levy
     * @param path
     * @throws IOException
     */
    public void openPropertyFile(String path) throws IOException {
        File file = new File(path);
        fis = new FileInputStream(file);
//        fos = new FileOutputStream(file);
        properties = new Properties();
        properties.load(fis);
    }

    /**
     * function: Getting value by key
     * 
     * @author levy
     * @param key
     * @return value
     */
    public String getValueByKey(String key) {
        return properties.getProperty(key);
        
    }

    /**
     * @author levy
     * @param key
     * @param value
     * @throws IOException
     */
    public void setValueByKey(String key, String value) throws IOException {
        properties.setProperty(key, value);
 //       properties.store(fos, "last update");
 //       fos.close();
        fis.close();
    }
}
