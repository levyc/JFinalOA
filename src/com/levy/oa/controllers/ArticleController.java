package com.levy.oa.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.levy.oa.config.ApiConfig;
import com.levy.oa.interceptors.ApiInterceptor;
import com.levy.oa.model.RecordModel;
import com.levy.oa.model.StaffModel;
import com.levy.oa.model.WorkPlanModel;
import com.levy.oa.model.WeekSummaryModel;
import com.levy.oa.model.WorkTaskModel;
import com.levy.oa.service.ArticleService;
import com.levy.oa.service.UserService;
import com.levy.oa.utils.DateUtils;

@Before(ApiInterceptor.class)
@SuppressWarnings("rawtypes")
public class ArticleController extends ApiController {

    @Override
    public ApiConfig getApiConfig() {
        // TODO Auto-generated method stub
        return new ApiConfig();
    }

    /************** render function **********/

    public void showUnderlingSummarys() {
        render("/pages/work/work.html");
    }

    /**************** 重构V1.1 get record list && API for front *************/
    
    public void getUnderlingSummarys() {
        StaffModel currentUser = getSessionAttr("currentUser");
        int departmentid = currentUser.getInt("departmentid");
        List<WeekSummaryModel> summarys =
                new ArticleService().getUnderlingSummarysBydepartmengId(departmentid);
        renderJson(summarys);
    }

    public void getUnderlingPlans() {
        StaffModel currentUser = getSessionAttr("currentUser");
        int departmentid = currentUser.getInt("departmentid");
        List<WorkPlanModel> plans =
                new ArticleService().getUnderlingPlansBydepartmengId(departmentid);
        renderJson(plans);
    }
    
    public void getUserPreviousSummarys(){
        StaffModel currentUser = getSessionAttr("currentUser");
        int userId = Integer.valueOf((String) currentUser.get("id"));
        List<WeekSummaryModel> summarys = 
                new ArticleService().getSummarysByUserId(userId);
        renderJson(summarys);
    }
    
    public void getUserPreviousPlans(){
        StaffModel currentUser = getSessionAttr("currentUser");
        int userId = Integer.valueOf((String) currentUser.get("id"));
        List<WorkPlanModel> plans = 
                new ArticleService().getPlansByUserId(userId);
        renderJson(plans);
    }

    public void getTasks() {
        StaffModel currentUser = getSessionAttr("currentUser");
        String executor = (String) currentUser.get("chinaName");
        List<WorkTaskModel> tasks = new ArticleService().getTaskListByExecutor(executor);
        renderJson(tasks);
    }
    /**********************************************************************/
    
    /*********************** 重构V1.1 save record api***********************/
    public void saveSummary() {
        saveRecord("WorkSummaryModel");
    }

    public void savePlan() {
        saveRecord("WorkPlanModel");
    }

    public void saveTask() {
        saveRecord("WorkTaskModel");
    }

    public void saveRecord(String recordType) {
        StaffModel currentUser = getSessionAttr("currentUser");
        RecordModel record = null;
        try {
            record = (RecordModel) getModel(Class.forName(recordType));
        } 
        catch (ClassNotFoundException e) {
            throw new RuntimeException("this record type can not find");
        }
        new ArticleService().saveRecord(record, currentUser);
    }
    
    /*************重构V1.1 返回某部门的员工的名字***/
    public void getDepartmentStaffName(){
        
    }
    
    /**
     * 前端调用在线编辑器的action 根据判断前端准备撰写的记录类型(获取recordType参数) api=/api/record/edit?access_token-recordType
     * 
     * @param users ：仅用于领导安排任务时，返回自己的员工，用于指定执行人
     */
    public void edit() {
        String ch = getPara("recordType");
        setAttr("sumbitType", ch);
        String en = new com.levy.oa.utils.StringUtils().convertChineseToEnglish(ch);
        setAttr("sumbitTypeEn", en);
        String title = ArticleService.getTitleWithTime();
        setAttr("recordTitle",title);
        setSessionAttr("sumbitTypeEn", en);
        int departmentid = getSessionAttr("departmentid");
        List<StaffModel> users = new UserService().getByDepartmentId(departmentid);
        setAttr("users", users);
        render("/pages/templates/editorTemplate.html");
    }

    /*********************************************************************************************/
    /**
     * 接收编辑器传来的记录的title和body参数，并与用户id，部门id，用户名 api=/api/record/write?access_token-title-body
     * 旧版本保存记录的接口，将于重构V1.1后删除
     */
    @Clear
    public void write() throws UnsupportedEncodingException {
        String body = getPara("body");
        String plan = getPara("plan");
        String title = ArticleService.getTitleWithTime();
        String createtime = DateUtils.format(new Date());

        int userid = getSessionAttr("userid");
        int departmentid = getSessionAttr("departmentid");
        String author = getSessionAttr("username");
        String sumbitTypeEn = getSessionAttr("sumbitTypeEn");
        if ("summary".equals(sumbitTypeEn)) {
            new ArticleService().saveWorkSummary(userid, title, body, createtime, author,
                    departmentid,plan);
        } else if ("plan".equals(sumbitTypeEn)) {
            new ArticleService().saveWorkPlan(userid, title, body, createtime, author,
                    departmentid);
        } else if ("task".equals(sumbitTypeEn)) {
            String[] prioritys = getParaValues("priority");
            String priority = prioritys[0];
            String executeTime = getPara("executeTime");
            String checkboxValues[] = getParaValues("executors");
            StringBuilder executors = new StringBuilder("");
            for (String vString : checkboxValues) {
                executors.append(vString + ",");
            }
            String realexecutors = executors.toString();
            new ArticleService().saveWorkTask(userid, title, body, createtime, author, departmentid,
                    realexecutors, priority, executeTime);
        }
        setAttr("success", "success");
        render("/pages/work/work.html");
    }

  
    /*******************************************************************************/
    public StaffModel setSessionUser(StaffModel user, int id, String username) {
        setSessionAttr("sessionuser", user);
        String cookiekey = Integer.toString(id) + " " + username;
        setCookie("username", cookiekey, 60 * 60);
        return user;
    }

    public StaffModel getSessionUser() {
        StaffModel uModel = getSessionAttr("sessionuser");
        StaffModel cookieuser1 = null;
        // 如果umodel为空则看看cookie有没有，如果cookie有，则从cookie中获取id 和用户账号 从数据库中获取一个
        if (uModel == null) {
            String cookieuser = getCookie("username");
            System.out.println("coook is null?" + cookieuser);
            if (cookieuser != null) {
                int index01 = cookieuser.indexOf(" ");
                String sid = cookieuser.substring(0, index01 - 1);
                int id = Integer.parseInt(sid);
                String username = cookieuser.substring(index01 + 1, cookieuser.length());
                cookieuser1 = (StaffModel) StaffModel.dao.findFirst(
                        "select * from user where id = ? and username = ? ", id, username);
                if (cookieuser1 != null) {
                    setSessionAttr("session", cookieuser);
                }
            }

        }
        return cookieuser1;
    }



    /************************ 将本周自己下属的工作记录展示在页面的Action，旧接口，重构V1.1版本取消该接口 *****************/

    public void showSummary() {

        new ArticleService().showWorkSummary(this);
    }

    public void showPlan() {
        new ArticleService().showWorkPlan(this);
    }

    public void showTask() {
        new ArticleService().showWorkTask(this);
    }



    /************** 显示单个记录文章页面的Action,重构V1.1版本前后端分离后将删除这三个Action *************/

    public void showSummaryPage() {
        String recordTitle = getPara("recordTitle");
        WeekSummaryModel wsm = WeekSummaryModel.dao
                .findFirst("select * from worksummary where title= " + "'" + recordTitle + "'");
        setAttr("workSummaryModel", wsm);
        render("/pages/work/work.html");
    }

    public void showPlanPage() {
        String recordTitle = getPara("recordTitle");
        WorkPlanModel wpm = WorkPlanModel.dao
                .findFirst("select * from workplan where title= " + "'" + recordTitle + "'");
        setAttr("workPlanModel", wpm);
        render("/pages/work/work.html");

    }

    public void showTaskPage() {
        String recordTitle = getPara("recordTitle");
        WorkTaskModel wtm = WorkTaskModel.dao
                .findFirst("select * from tasks where title= " + "'" + recordTitle + "'");
        setAttr("workTaskModel", wtm);
        render("/pages/work/work.html");
    }

    /***********************************************************************/


    /**
     * 用于显示自己以往的某种类型的工作记录 根据前端传过来的recordType，来决定返回的记录
     * 
     * @param recordType：自己要查询的记录的类型
     */
    public void showOwnRecords() {
        String recordType = getPara("recordType");
        if ("工作总结".equals(recordType)) {
            new ArticleService().showOwnWorkSummary(this);
        } else if ("工作计划".equals(recordType)) {
            new ArticleService().showOwnWorkPlan(this);
        } else if ("工作任务".equals(recordType)) {
            new ArticleService().showOwnWorkTask(this);
        }
    }



}
