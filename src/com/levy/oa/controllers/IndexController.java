package com.levy.oa.controllers;

import java.io.IOException;

import javax.servlet.ServletException;

import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.levy.oa.api.AccessToken;
import com.levy.oa.model.StaffModel;
import com.levy.oa.service.AccessTokenService;
import com.levy.oa.service.UserService;
import com.levy.oa.utils.ImageCode;

/* 首页 */
public class IndexController extends Controller {

    /* 首页就是登录界面 */
    public void index() {
        if(getSessionAttr("currentUser")!=null){
            render("/pages/work/work.html");
        }
        render("/pages/front/login.html");
    }

    public StaffModel userModel = null;

    public StaffModel getUser() {
        return userModel;
    }
    
    /**
     * 返回登录界面
     */
    public void backToLogin() {
        render("/pages/front/login.html");
    }


    /* 登录验证用户，如果通过就跳转至工作界面 */
    public void login() {
        // 首先获取form中的数据，然后根据数据去数据库中查找是否有这个对象
        // 如果有的话就跳转到工作页面，没有的话就回到登录界面，保存之前的用户名和密码并传回给登录页面
        String username = getPara("username");
        String password = getPara("password");
        userModel = new UserService().getByNameandPassword(username, password);

        if (userModel == null) {
            setAttr("msg", "认证失败，请您重新输入");
            render("/pages/front/login.html");
        } else {
            // setSessionAttr("currentUser", userModel);
            int id = (int) userModel.get("id");
            String userName = (String) userModel.get("username");
            int departmentid = (int) userModel.get("departmentid");
           
            setAttr("username", userModel.get("username"));//用户传去前端显示
            setAttr("department", userModel.get("department"));
            
            setSessionAttr("userid", userModel.get("id"));//用于另外一个controller接收
            setSessionAttr("username", userName);
            setSessionAttr("currentUser", userModel);
            setSessionAttr("departmentid", departmentid);
            setSessionUser(userModel, id, username);
            long timeStamp = System.currentTimeMillis();
            AccessToken userAccessToken = AccessTokenService.creatAccessToken(userName, password,timeStamp);
            setSessionAttr("token", userAccessToken.toString());
            renderJson("token",userAccessToken);
            render("/pages/work/work.html");
        }

    }

    /* 用于生成注册码 */
    public void image_code() {
        try {
            new ImageCode().doGet(getRequest(), getResponse());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        renderNull();
    }

    public StaffModel setSessionUser(StaffModel user, int id, String username) {
        setSessionAttr("sessionuser", user);
        String cookiekey = Integer.toString(id) + " " + username;
        setCookie("username", cookiekey, 60 * 60);
        return user;
    }

    public StaffModel getSessionUser() {
        StaffModel uModel = getSessionAttr("sessionuser");
        StaffModel cookieuser1 = null;
        // 如果umodel为空则看看cookie有没有，如果cookie有，则从cookie中获取id 和用户账号 从数据库中获取一个
        if (uModel == null) {
            String cookieuser = getCookie("sessionuser");
            if (cookieuser != null) {
                int index01 = cookieuser.indexOf(" ");
                String sid = cookieuser.substring(0, index01 - 1);
                int id = Integer.parseInt(sid);
                String username = cookieuser.substring(index01 + 1, cookieuser.length());
                cookieuser1 = (StaffModel) StaffModel.dao.findFirst(
                        "select * from user where id = ? and username = ? ", id, username);
                if (cookieuser1 != null) {
                    setSessionAttr("session", cookieuser);
                }
            }

        }
        return cookieuser1;
    }
}
