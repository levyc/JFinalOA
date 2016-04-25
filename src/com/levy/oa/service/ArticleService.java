package com.levy.oa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.levy.oa.controllers.ArticleController;
import com.levy.oa.model.RecordModel;
import com.levy.oa.model.StaffModel;
import com.levy.oa.model.WorkPlanModel;
import com.levy.oa.model.WeekSummaryModel;
import com.levy.oa.model.WorkTaskModel;
import com.levy.oa.utils.DateUtils;

public class ArticleService{
    
    /**************************** 重构V1.1 save record list ,如果最后一条函数可以工作，前三条函数则删除 ***********************/
    
//    public boolean saveWorkSummary(WorkSummaryModel summary,UserModel currentUser) {
//        return summary.set("userid",currentUser.get("userid")).set("author",currentUser.get("chinaName"))
//                .set("departmentid",currentUser.get("departmentid")).save();
//    }
//
//    public boolean saveWorkPlan(WorkPlanModel plan,UserModel currentUser) {
//        return plan.set("userid",currentUser.get("userid")).set("author",currentUser.get("chinaName"))
//                .set("departmentid",currentUser.get("departmentid")).save();
//    }
//
//    public boolean saveWorkTask(WorkTaskModel task,UserModel currentUser) {
//        return task.set("userid",currentUser.get("userid")).set("author",currentUser.get("chinaName"))
//                .set("departmentid",currentUser.get("departmentid")).save();
//    }
//    
    @SuppressWarnings("rawtypes")
    public boolean saveRecord(RecordModel record,StaffModel currentUser){       
        return record.set("userid",currentUser.get("userid")).set("author",currentUser.get("chinaName"))
                .set("departmentid",currentUser.get("departmentid"))
                .set("creattime",getCurrentDate()).save();
    }
    
    public String getCurrentDate(){
        return DateUtils.format(new Date());
    }
    
    public static String getTitleWithTime(){
        return DateUtils.getThisWeekFirstDate()+"至"+DateUtils.getThisWeekLastDate()+"工作记录";
    }
    

    /******************************************************************************************/
      
    // 实现思路：1.根据领导的id获取领导的部门id（先判断当前有没有缓存到用户信息，当没有缓存到让用户重新登录），从而将最新的该部门的id工作总结记录全部读出来， 
    //        2.将他们存入一个用于装workSummary对象的集合里面，将这个记录集合放去前端，让前端页面遍历这个集合并逐个显示里面的元素 
    
     /**************************** 重构版本V1.1 get record list*********************************/
    public List<WeekSummaryModel> getUnderlingSummarysBydepartmengId(int departmengid) {
        return WeekSummaryModel.getSummaryListByDepartmentId(departmengid);
    }

    public List<WeekSummaryModel> getSummarysByUserId(int userId) {
        return WeekSummaryModel.getSummaryListByUserId(userId);
    }

    public List<WorkPlanModel> getUnderlingPlansBydepartmengId(int departmentid) {
        return WorkPlanModel.getPlanListByDepartmentid(departmentid);
    }

    public List<WorkPlanModel> getPlansByUserId(int userId) {
        return WorkPlanModel.getPlanListByUserId(userId);
    }

    public List<WorkTaskModel> getTaskListByExecutor(String executor) {
        return WorkTaskModel.getTaskListByExecutor(executor);
    }


/*****************************************************************************/
    
    
            //下面是重构V1.1版本要删除的代码
    
    
    
    
    /******************* 用于展示列表清单的旧接口！！！重构V1.1前后端分离后下一版本去除 ************************/
    public void showWorkSummary(ArticleController controller) {
        int departmentid = controller.getSessionAttr("departmentid");
        List<WeekSummaryModel> plans = new ArrayList<WeekSummaryModel>();
        plans = WeekSummaryModel.dao
                .find("select * from worksummary where departmentid=" + departmentid);
        controller.setAttr("plans", plans);
        controller.render("/pages/work/work.html");
    }

    public void showWorkPlan(ArticleController controller) {
        int departmentid = controller.getSessionAttr("departmentid");
        List<WorkPlanModel> plans = new ArrayList<WorkPlanModel>();
        plans = WorkPlanModel.dao.find("select * from workplan where departmentid=" + departmentid);
        controller.setAttr("plans", plans);
        controller.render("/pages/work/work.html");
    }

    public void showWorkTask(ArticleController controller) {
        int departmentid = controller.getSessionAttr("departmentid");
        List<WorkTaskModel> tasks = new ArrayList<WorkTaskModel>();
        tasks = WorkTaskModel.dao.find("select * from tasks where departmentid=" + departmentid
                + " and executor like " + "'%" + controller.getSessionAttr("username") + "%'");
        controller.setAttr("tasks", tasks);
        controller.render("/pages/work/work.html");
    }
    /***********************************************************************************/


    /**************展示用户自己以往总结，计划的actions！！！重构V1.1版本后将去除********************/

    public void showOwnWorkSummary(ArticleController controller) {
        // 实现思路：1.根据领导的id获取领导的部门id（先判断当前有没有缓存到用户信息，当没有缓存到让用户重新登录），从而将最新的该部门的id工作总结记录全部读出来，
        // 2.将他们存入一个用于装workSummary对象的集合里面，将这个记录集合放去前端，让前端页面遍历这个集合并逐个显示里面的元素

        int userid = controller.getSessionAttr("userid");
        List<WeekSummaryModel> records = new ArrayList<WeekSummaryModel>();
        records = WeekSummaryModel.dao.find("select * from worksummary where userid=" + userid);
        controller.setAttr("ownSummarys", records);
        controller.render("/pages/work/work.html");
    }

    public void showOwnWorkPlan(ArticleController controller) {
        int userid = controller.getSessionAttr("userid");
        List<WorkPlanModel> records = new ArrayList<WorkPlanModel>();
        records = WorkPlanModel.dao.find("select * from workplan where userid=" + userid);
        controller.setAttr("ownPlans", records);
        controller.render("/pages/work/work.html");
    }

    public void showOwnWorkTask(ArticleController controller) {
        int userid = controller.getSessionAttr("departmentid");
        List<WorkTaskModel> records = new ArrayList<WorkTaskModel>();
        records = WorkTaskModel.dao.find("select * from tasks where userid=" + userid);
        controller.setAttr("ownTasks", records);
        controller.render("/pages/work/work.html");
    }

    /************************** 获取用户id的记录列表API，重构V1.1版本将去除 *********************/
    public List<WeekSummaryModel> getOwnSummaryListByUserId(int userId) {
        List<WeekSummaryModel> records = new ArrayList<WeekSummaryModel>();
        records = WeekSummaryModel.dao.find("select * from worksummary where userid=" + userId);
        return records;
    }

    public List<WorkPlanModel> getOwnPlanListByUserId(int userId) {
        List<WorkPlanModel> records = new ArrayList<WorkPlanModel>();
        records = WorkPlanModel.dao.find("select * from workplan where userid=" + userId);
        return records;
    }

    public List<WorkTaskModel> getOwnTaskListByUserId(int userId) {
        List<WorkTaskModel> records = new ArrayList<WorkTaskModel>();
        records = WorkTaskModel.dao.find("select * from tasks where userid=" + userId);
        return records;
    }



    /******************* 根据部门id获取记录列表API，重构V1.1版本去除 ******************************/
    public List<WeekSummaryModel> getSummaryListByDepartmentId(int departmentid) {
        List<WeekSummaryModel> summarys = new ArrayList<WeekSummaryModel>();
        summarys = WeekSummaryModel.dao
                .find("select * from worksummary where departmentid=" + departmentid);
        return summarys;
    }

    public List<WorkPlanModel> getPlanListByDepartmentId(int departmentid) {
        List<WorkPlanModel> plans = new ArrayList<WorkPlanModel>();
        plans = WorkPlanModel.dao.find("select * from workplan where departmentid=" + departmentid);
        return plans;
    }

    public List<WorkTaskModel> getTaskListByDepartmentId(int departmentid) {
        List<WorkTaskModel> tasks = new ArrayList<WorkTaskModel>();
        tasks = WorkTaskModel.dao.find("select * from workplan where departmentid=" + departmentid);
        return tasks;
    }
    
    /*********************** 用于保存工作总结，计划，任务进数据库的旧函数，将于重构V1.1版本前后端分离后去除 *************************/

    public boolean saveWorkSummary(int userid, String title, String body, String createtime,
            String author, int departmentid,String plan) {
        // 新建一个worksummary的对象然后将title和body存进去
        return new WeekSummaryModel().set("title", title).set("body", body).set("userid", userid)
                .set("creattime", createtime).set("author", author).set("plan", plan)
                .set("departmentid", departmentid).save();
    }

    public boolean saveWorkPlan(int userid, String title, String body, String createtime,
            String author, int departmentid) {
        // 新建一个workPlan的对象然后将title和body存进去
        return new WorkPlanModel().set("title", title).set("body", body).set("userid", userid)
                .set("creattime", createtime).set("author", author)
                .set("departmentid", departmentid).save();
    }

    public boolean saveWorkTask(int userid, String title, String body, String createtime,
            String author, int departmentid, String executor, String priority, String executeTime) {
        // 新建一个workPlan的对象然后将title和body存进去
        return new WorkTaskModel().set("title", title).set("body", body).set("userid", userid)
                .set("creattime", createtime).set("author", author).set("executor", executor)
                .set("departmentid", departmentid).set("priority", priority)
                .set("executeTime", executeTime).save();
    }

}
