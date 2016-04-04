package com.levy.oa.model;

import java.util.List;

public class WorkTaskModel extends RecordModel<WorkTaskModel> {

    private String executor = "executor";
    private String priority = "priority";
    private String executeTime = "executeTime";

    private static final long serialVersionUID = 1L;
    public static final WorkTaskModel dao = new WorkTaskModel();

    public static List<WorkTaskModel> getTaskListByExecutor(String executor) {
        String conditionSQL = "where executor like "+"'"+executor+"'";
        return getTaskListByConditionSQL(conditionSQL);
    }
    
    public static List<WorkTaskModel> getTaskListByConditionSQL(String conditionSQL){
        List<WorkTaskModel> tasks =
                WorkTaskModel.dao.find("select * from tasks "+conditionSQL);
        return tasks;
    }
    
    
    
/**-----------------------------------Seter&&Geter----------------------------**/    
    public String getExecutor() {
        return get(executor);
    }

    public WorkTaskModel setExecutor(String Executor) {
        set(executor, Executor);
        return this;
    }

    public String getPriority() {
        return get(priority);
    }

    public WorkTaskModel setPriority(String Priority) {
        set(priority, Priority);
        return this;
    }

    public String getExecuteTime() {
        return get(executeTime);
    }

    public WorkTaskModel setExecuteTime(String ExecuteTime) {
        set(executeTime, ExecuteTime);
        return this;
    }
}
