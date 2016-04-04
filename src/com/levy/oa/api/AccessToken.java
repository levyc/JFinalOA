package com.levy.oa.api;

import java.io.Serializable;
import java.util.Map;

import com.jfinal.json.JFinalJson;
import com.mchange.io.ReaderUtils;

public class AccessToken implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long  timeStamp ;   
    private String userId ;
    private String userSecret ;
 

    private Long expiresTime;  //有效期
    private String json;

    public AccessToken(String userName,String userSecret,long timeStamp) {
        this.userId =userName;
        this.userSecret = userSecret;
        this.timeStamp = timeStamp ;
    }

    public String getJson() {
        return json;
    }
    
    public boolean isAvailable() {
        if (timeStamp < System.currentTimeMillis())
            return false;
        if (userId != null)
            return false;
        if (expiresTime < System.currentTimeMillis())
            return false;
        return userSecret != null;
    }


}


