package com.levy.oa.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.levy.oa.config.ApiConfig;
import com.levy.oa.interceptors.ApiInterceptor;
/**
 * 所有用作api的controller都要继承该类
 * @author levy
 *
 */
@Before(ApiInterceptor.class)
public abstract class ApiController extends Controller{
   public abstract  ApiConfig getApiConfig();
   
}
