package com.levy.oa.model;

import java.util.List;

public class VicePresidentModel extends StaffModel<VicePresidentModel> {

    private static final long serialVersionUID = 1L;
    private final int classNum = 2;
    private static List<DepartmentLeader> departmentLeaders;
    public VicePresidentModel(){
        set("classN",classNum);
    }
    
}
