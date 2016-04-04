package com.levy.oa.cache;

public interface IAccessTokenCache {
    public void set(String key,Object value);
    public <T> T get(String key);
    public void remove(String key);
}
