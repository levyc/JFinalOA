package com.levy.oa.config;

/**
 * 用于存放OA服务中需要用到的参数
 * 
 * @author levy
 *
 */
public class ApiConfig {
    private String token = null;
    private String userId = null;
    private String userSecret = null;
    private String encodingAesKey = null;
    private boolean messageEncrypt = false; // 消息加密与否

    public ApiConfig() {

    }

    public ApiConfig(String token) {
        setToken(token);
    }

    public ApiConfig(String token, String userId, String userSecret) {
        setToken(token);
        setUserID(userId);
        setUserSecret(userSecret);
    }

    public void setToken(String Token) {
        if (token == null) {
            throw new IllegalArgumentException("token值不能为空");
        }
        this.token = Token;
    }

    public void setUserID(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId值不能为空");
        }
        this.userId = userId;
    }

    public void setUserSecret(String userSecret) {
        if (userSecret == null) {
            throw new IllegalArgumentException("userSecret值不能为空");
        }
        this.userSecret = userSecret;
    }

    public String getUserId() {
        if (userId == null) {
            throw new IllegalStateException("userId不能为空");
        }
        return this.userId;
    }

    public String getUserSecret() {
        if (userSecret == null) {
            throw new IllegalStateException("userSecret还没被赋值");
        }
        return this.userSecret;
    }
}
