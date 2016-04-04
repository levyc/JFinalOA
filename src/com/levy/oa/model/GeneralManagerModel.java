package com.levy.oa.model;

import java.util.List;

public class GeneralManagerModel extends StaffModel<GeneralManagerModel> {

    private static final long serialVersionUID = 4853123355218348139L;
    private final int classNum = 1; 
    private List<VicePresidentModel> vps;
    public GeneralManagerModel(){
        set("classN",classNum);
    }

}
