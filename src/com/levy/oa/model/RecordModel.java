package com.levy.oa.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class RecordModel<M extends Model<M>> extends Model<M> {

    private static final long serialVersionUID = 1L;
    private static final RecordModel dao = new RecordModel();
    private String ID = "id";
    private String UserID = "userid";
    private String Title = "title";
    private String body = "body";
    private String createTime = "creattime";
    private String author = "author";
    private String departmentId = "departmentid";

    public Integer getInt(String attr) {
        Object object = get(attr);
        if (object == null) {
            return null;
        } else if (object instanceof Integer) {
            return (Integer) object;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).intValue();
        } else if (object instanceof String) {
            try {
                return Integer.parseInt((String) object);
            } catch (Exception e) {
                throw new RuntimeException("String can not be cast to int :" + attr.toString());
            }
        }
        return null;
    }
    
    /** --------------------SeterGeter方法----------------------------------- **/
    public Integer getId() {
        return getInt(ID);
    }

    public RecordModel setId(String id) {
        set(ID, id);
        return this;
    }

    public Integer getUserID() {
        return getInt(UserID);
    }

    public RecordModel setUserID(String userID) {
        set(UserID, userID);
        return this;
    }

    public String getTitle() {
        return get(Title);
    }

    public void setTitle(String title) {
        set(Title, title);
    }

    public String getBody() {
        return body;
    }

    public RecordModel setBody(String Body) {
        set(body, Body);
        return this;
    }

    public String getCreateTime() {
        return get(createTime);
    }

    public RecordModel setCreateTime(String CreateTime) {
        set(createTime, CreateTime);
        return this;
    }

    public String getAuthor() {
        return get(author);
    }

    public RecordModel setAuthor(String Author) {
        set(author, Author);
        return this;
    }

    public Integer getDepartmentId() {
        return getInt(departmentId);
    }

    public RecordModel setDepartmentId(String DepartmentId) {
        set(departmentId, DepartmentId);
        return this;
    }



}
