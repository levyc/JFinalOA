package com.levy.oa.model;

import java.util.List;

/**
 * Weekly Model
 * 
 * @author levy
 *
 */
public class WeekSummaryModel extends RecordModel<WeekSummaryModel> {

    private static final long serialVersionUID = 1L;

    public static final WeekSummaryModel dao = new WeekSummaryModel();

    /**
     * get Weekly by userId 
     * @param userId
     * @return List<weekly>
     */
    public static List<WeekSummaryModel> getSummaryListByUserId(int userId) {
        String conditionSQL = "where userid = " + userId;
        return getSummaryListByConditionSQL(conditionSQL);
    }

    /**
     * get weekly by departmentId
     * @param departmentId
     * @return List<weekly>
     */
    public static List<WeekSummaryModel> getSummaryListByDepartmentId(int departmentId) {
        String conditionSQL = "where departmentId = " + departmentId;
        return getSummaryListByConditionSQL(conditionSQL);
    }

    /**
     * get weekly by sql you write
     * @param conditionSQL
     * @return List<weekly>
     */
    public static List<WeekSummaryModel> getSummaryListByConditionSQL(String conditionSQL) {
        List<WeekSummaryModel> summarys =
                WeekSummaryModel.dao.find("select * from worksummary " + conditionSQL);
        return summarys;
    }

}
