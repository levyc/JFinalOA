package com.levy.oa.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.ehcache.RenderType;
import com.jfinal.render.Render;
import com.levy.oa.api.AccessToken;
import com.levy.oa.api.ApiConfigKit;
import com.levy.oa.controllers.ApiController;
import com.levy.oa.service.AccessTokenService;
import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;

/**
 * ApiInterceptor为ApiController 绑定ApiConfig对象到当前线程
 * 以便之后使用ApiConfigKit.getApiConfig（）获取该对象
 * @author levy
 *
 */
public class ApiInterceptor implements Interceptor{

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if(controller instanceof ApiController == false){
             throw new RuntimeException("该控制器要继承ApiController");
        }
        try{  
            String client_accessToken = controller.getPara("access_token");
            String client_accessToken_json =  JsonKit.toJson(client_accessToken);
            AccessToken service_accessToken  =  AccessTokenService.getAccessToken(controller.getSessionAttr("username"));
            String service_accessToken_str  = JsonKit.toJson(service_accessToken);
            
                  if(client_accessToken==null){
                    
                           controller.renderText("访问令牌不允许为空");
                          // throw new IllegalStateException("访问令牌不允许为空");
          
                     
                  }
                  if (client_accessToken.equals(service_accessToken_str)) {
                    controller.render("你的令牌认证失败，请重新登录");
                    controller. render("/pages/front/login.html");
                }
            inv.invoke();
        }
        finally{
            
        }
        
    }
     
}
