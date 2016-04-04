package com.levy.oa.service;


import java.util.ArrayList;
import java.util.List;

import com.levy.oa.model.StaffModel;

public class UserService {

    public StaffModel getByNameandPassword(String userName, String password) {
        return (StaffModel) StaffModel.dao.findFirst(
                "select * from user " + "where username = ? and password = ? ", userName, password);
    }

    public StaffModel getByName(String userName) {
        return (StaffModel) StaffModel.dao.findFirst("select * from user " + "where username = ?", userName);
    }

    public List<StaffModel> getByDepartmentId(int departmengId) {
        List<StaffModel> users = new ArrayList<StaffModel>();
        users = StaffModel.dao.find("select * from user where departmentid=" + departmengId);
        return users;
    }

    public boolean add(String userName, String userPassword, String department, int departmentId,
            String sex, int classNumber) {
        StaffModel newUser = new StaffModel();
    return newUser.set("username", userName).set("password", userPassword)
                .set("department", department).set("departmentid", departmentId).set("sex", sex)
                .set("classN", classNumber).save();

    }

    public boolean removeByName(String userName) {
        StaffModel userModel = getByName(userName);
      return userModel.deleteById((Integer)userModel.get("id"));
    }

    public boolean updateByUserName(String userName, String key, Object value) {
        StaffModel userModel = getByName(userName);
      return  userModel.set(key, value).update();
    }



}
