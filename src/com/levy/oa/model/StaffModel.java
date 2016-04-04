package com.levy.oa.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * BaseMember Model
 * @author levy
 * @param <M>
 */
@SuppressWarnings("rawtypes")
public class StaffModel<M extends Model<M>> extends Model<M> {

    private static final long serialVersionUID = 1L;
   
    public static final StaffModel dao = new StaffModel();
    
    private String username = "username";
    private String password = "password";
    private String department = "department";
    private String sex = "sex";
    private String classN = "classN";
    private String departmentid = "departmentid";
    private String chinaName = "chinaName";

    public String getUserName() {
        return get(username);
    }

    public StaffModel setUserName(String username) {
        set(username, username);
        return this;
    }

    public String getPassword() {
        return get(password);
    }

    public String getChinaName() {
        return get(chinaName);
    }

    public StaffModel setChinaName(String ChinaName) {
        set(chinaName, ChinaName);
        return this;
    }

    public StaffModel setPassword(String Password) {
        set(password, Password);
        return this;
    }

    public String getDepartment() {
        return get(department);
    }

    public StaffModel setDepartment(String Department) {
        set(department, Department);
        return this;
    }

    public String getSex() {
        return get(sex);
    }

    public StaffModel setSex(String Sex) {
        set(sex, Sex);
        return this;
    }

    public Integer getClassN() {
        return getInt(classN);
    }

    public StaffModel setClassN(int ClassN) {
        set(classN, ClassN);
        return this;
    }

    public String getDepartmentid() {
        return get(departmentid);
    }

    public StaffModel setDepartmentid(String Departmentid) {
        set(departmentid, Integer.valueOf(Departmentid));
        return this;
    }

}
