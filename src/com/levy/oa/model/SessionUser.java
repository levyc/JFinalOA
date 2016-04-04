package com.levy.oa.model;

import com.jfinal.plugin.activerecord.Model;
/*这个其实就是相当于user的session容器，用来存储在会话中产生的UserModel对象（即是当前登录用户）*/
public class SessionUser<M extends Model<M>> extends Model<SessionUser> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public int getID(){
        return get("userid")==null?-1:get("userid");
    }
    
}
