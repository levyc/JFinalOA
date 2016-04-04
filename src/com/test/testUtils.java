package com.test;

import org.junit.Assert;
import org.junit.Test;

import com.levy.oa.service.ArticleService;



public class testUtils {
      @Test
      public void testGetTitleWithTime(){
          String title = new ArticleService().getTitleWithTime();
          System.out.println(title);
          Assert.assertEquals("2016-03-14--2016-03-18工作记录",title);
      }
}
