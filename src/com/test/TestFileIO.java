package com.test;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.levy.oa.utils.PropertyTool;

public class TestFileIO {
    @Test
    public void testPropIO() throws IOException{
        PropertyTool pTool = new PropertyTool();
        pTool.openPropertyFile
        (new File("").getCanonicalPath()+File.separator+"src/resources"+File.separator+"DBConfig.properties");
        System.out.println(new File("").getCanonicalPath());
        String result = pTool.getValueByKey("mysql_user");
        Assert.assertEquals("root",result);
    }
}
