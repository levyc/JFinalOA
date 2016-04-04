package com.levy.oa.service;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.JsonKit;
import com.levy.oa.api.AccessToken;
import com.levy.oa.api.ApiConfigKit;
import com.levy.oa.cache.IAccessTokenCache;
import com.levy.oa.config.ApiConfig;

/**
 * 认证与获取accessToken的一些函数
 * 
 * @author levy
 *
 */
public class AccessTokenService {
    static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

    public static AccessToken getAccessToken(String userName) {
//        String userId = ApiConfigKit.getApiConfig().getUserId();
        AccessToken accessToken = accessTokenCache.get(userName);
        if (accessToken != null && accessToken.isAvailable()) {
            return accessToken;
        }
        return null;
    }
    

    public static synchronized void refreshAccessToken() {
        ApiConfig apiConfig = ApiConfigKit.getApiConfig();
        String userId = apiConfig.getUserId();
        String userSecret = apiConfig.getUserSecret();
        Map<String, String> queryParas = new HashMap<String, String>();
        queryParas.put("userId", userId);
        queryParas.put("userSecret", userSecret);
        String timeStamp = String.valueOf(System.currentTimeMillis());
        queryParas.put("timeStamp",timeStamp);
       final Map<String, String> queryPara_final = queryParas;
       String jsonToken = JsonKit.toJson(queryPara_final);
       accessTokenCache.set(apiConfig.getUserId(),jsonToken);
        
    }
 /**
  * 根据用户名称，密码和请求时间创建一个token，并存入neicun   
  * @param userId
  * @param userSecret
  * @param timeStamp
  * @return
  */
    public static AccessToken creatAccessToken(String userId,String userSecret,long timeStamp){
         AccessToken accessToken = new AccessToken(userId, userSecret, timeStamp);
        accessTokenCache.set(userId,accessToken);
        return accessToken ;
    }
}
