package com.levy.oa.model;

import java.util.List;

public class DepartmentLeader extends StaffModel<DepartmentLeader>{

    private static final long serialVersionUID = 7569535516285676132L;
    private final int classNum = 3;
    private List<StaffModel> Staffs ;
    
    public DepartmentLeader(){
        set("classN",classNum);
    }
   
    
}
