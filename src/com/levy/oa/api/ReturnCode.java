package com.levy.oa.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于返回的错误码对应信息
 * @author levy
 *
 */
public class ReturnCode {
    static Map<Integer,String>  returnCodes = new ConcurrentHashMap<Integer,String>();
     public static String  get(Integer errcode){
         return  returnCodes.get(errcode);
     }
}
