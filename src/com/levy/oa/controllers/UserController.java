package com.levy.oa.controllers;

import com.jfinal.core.Controller;
import com.levy.oa.model.StaffModel;
import com.levy.oa.service.UserService;

public class UserController extends Controller {
    
    public void registerPage(){
        render("/pages/front/register.html");
    }
    
    public void registerUser() {
        
        StaffModel userModel = getModel(StaffModel.class);
        System.out.println(userModel.get("username")+":"+userModel.get("password"));
        userModel.save();
//        String userName = getPara("userName");
//        String userPassword = getPara("userPassword");
//        String department = getPara("department");
//        String sex = getPara("sex");
//        int departmentId = Integer.valueOf(getPara("departmentId"));
//        int classNumber = Integer.valueOf(getPara("classNumber"));
//
//        new UserService().add(userName, userPassword, department, departmentId, sex, classNumber);
    }
    
    /**
     * 更新用户资料
     */
    public void updateInformation(){
        
    }
}
