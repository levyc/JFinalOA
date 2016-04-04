package com.levy.oa.model;

import java.util.List;

public class WorkPlanModel extends RecordModel<WorkPlanModel> {

    private static final long serialVersionUID = 1L;
    public static final WorkPlanModel dao = new WorkPlanModel();

    public static List<WorkPlanModel> getPlanListByDepartmentid(int departmengid) {
        String conditionSQL = "where departmentid = "+departmengid;
        return  getPlanListByConditionSQL(conditionSQL);
    }
    
    public static List<WorkPlanModel> getPlanListByUserId(int userId) {
        String conditionSQL = "where userid = "+userId;
        return  getPlanListByConditionSQL(conditionSQL);
    }
    
    public static List<WorkPlanModel> getPlanListByConditionSQL(String conditionSQL){
        List<WorkPlanModel> plans =
                WorkPlanModel.dao.find("select * from workplan "+conditionSQL);
        return plans;
    }
}
