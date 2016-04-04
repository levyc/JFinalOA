package com.levy.oa.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultAccessTokenCache implements IAccessTokenCache {
    
    private Map<String,Object>  maps = new ConcurrentHashMap<String,Object>();
    @Override
    public void set(String key, Object value) {
          maps.put(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        return (T) maps.get(key);
    }

    @Override
    public void remove(String key) {
          maps.remove(key);
        
    }

}
