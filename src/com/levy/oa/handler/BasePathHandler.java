package com.levy.oa.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class BasePathHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response,
            boolean[] isHandled) {
        // TODO Auto-generated method stub
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()
        +":"+request.getServerPort()+path+"/";
        request.setAttribute("basePath", basePath);
        request.setAttribute("ctx",basePath);
        nextHandler.handle(target, request, response, isHandled);
        
        
    }
     
}
