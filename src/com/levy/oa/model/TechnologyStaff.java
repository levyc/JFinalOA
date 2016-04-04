package com.levy.oa.model;

public class TechnologyStaff extends StaffModel<TechnologyStaff> {

    private static final long serialVersionUID = 1L;
    private final  int classNum = 4;
    private final  int departmentId = 2; 
    public TechnologyStaff(){
        set("classN",4);
        set("departmentid",departmentId);
    }
}
