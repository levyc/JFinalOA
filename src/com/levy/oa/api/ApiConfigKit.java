package com.levy.oa.api;

import com.levy.oa.cache.DefaultAccessTokenCache;
import com.levy.oa.cache.IAccessTokenCache;
import com.levy.oa.config.ApiConfig;

/**
 * 用于将ApiConfig对象绑定到ThreadLocal的工具类，方便当前线程在各个地方获取ApiConfig
 * 继承了ApiController的控制器不用，因为里面加了ApiInterceptor拦截器，已经自动绑定
 * 
 * @author levy
 *
 */
public class ApiConfigKit {
    private static ThreadLocal<ApiConfig> threadLocal = new ThreadLocal<ApiConfig>();

    public static ApiConfig getApiConfig() {
        ApiConfig apiConfig = threadLocal.get();
        if (apiConfig == null) {
            throw new IllegalArgumentException("请先将ApiConifg对象绑定到ThreadLocal");
        }
        return apiConfig;
    }

    public static void setThreadLocalApiConfig(ApiConfig apiConfig) {
        threadLocal.set(apiConfig);
    }

    public static void removeThreadLocalApiConfig() {
        threadLocal.remove();
    }
    
    static IAccessTokenCache accessTokenCache = new DefaultAccessTokenCache();

    public static IAccessTokenCache getAccessTokenCache(){
        return  ApiConfigKit.accessTokenCache;
    }
}
