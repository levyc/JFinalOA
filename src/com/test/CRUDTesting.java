package com.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.levy.oa.model.RecordModel;
import com.levy.oa.model.StaffModel;
import com.levy.oa.model.WorkPlanModel;
import com.levy.oa.model.WeekSummaryModel;
import com.levy.oa.model.WorkTaskModel;
import com.levy.oa.service.ArticleService;
import com.levy.oa.service.UserService;



public class CRUDTesting {
    static DruidPlugin dPlugin = null;
    static ActiveRecordPlugin activeRecordPlugin = null;

    @BeforeClass
    public static void Setup() throws Exception {
        dPlugin = new DruidPlugin(
                "jdbc:mysql://127.0.0.1:3306/oa?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
                "root", "");
        dPlugin.start();
        dPlugin.setDriverClass("com.mysql.jdbc.Driver");
        activeRecordPlugin = new ActiveRecordPlugin(dPlugin);
        activeRecordPlugin.setShowSql(true);
        activeRecordPlugin.setDialect(new MysqlDialect());
        activeRecordPlugin.addMapping("workplan", WorkPlanModel.class);
        activeRecordPlugin.addMapping("worksummary", WeekSummaryModel.class);
        activeRecordPlugin.addMapping("tasks", WorkTaskModel.class);
        activeRecordPlugin.addMapping("user", (Class<? extends Model<?>>) StaffModel.class);
        activeRecordPlugin.start();
    }

    @AfterClass
    public static void tearDown() {
        dPlugin.stop();
        activeRecordPlugin.stop();
    }

    @Test
    public void testCRUDUser() {
        UserService userService = new UserService();
        boolean add = userService.add("test", "test", "test", 8080, "test", 12);
        StaffModel userModel = userService.getByName("test");
        boolean update = userService.updateByUserName("test", "password", "newTest");
        boolean delete = userService.removeByName("test");
        Assert.assertEquals(add && update && delete && (userModel != null), true);
    }
/****************  测试保存记录API *******************************/
    @Test
    public void tsetSaveSummary() {
        ArticleService articleService = new ArticleService();
        boolean summary = articleService.saveWorkSummary(1, "testtitle", "testbody", "2016",
                "testAuthor", 88);
        Assert.assertEquals(summary, true);
    }

    @Test
    public void testSavePlan() {
        ArticleService articleService = new ArticleService();
        boolean plan =
                articleService.saveWorkPlan(1, "testtitle", "testbody", "2016", "testAuthor", 88);
        Assert.assertEquals(plan, true);
    }

    @Test
    public void testSaveTask() {
        ArticleService articleService = new ArticleService();
        boolean task = articleService.saveWorkTask(1, "testtitle", "testbody", "2016", "testAuthor",
                88, "test", "高", "2016");
        Assert.assertEquals(task, true);
    }

    /******************* 测试 根据部门id获取记录列表API ******************************/
    @Test
    public void testGetSummaryListByDepartmentId() {
        ArticleService articleService = new ArticleService();
        List<WeekSummaryModel> list = articleService.getSummaryListByDepartmentId(88);
        System.out.println("-------------GetSummaryListByDepartmentId 方法测试中----------");
        System.out.println("查询到有" + list.size() + "个");
        for (WeekSummaryModel summary : list) {
            System.out.println("summaryTitle:" + summary.get("title"));
        }
        System.out.println("----------------------------------------");
    }

    @Test
    public void testGetPlanListByDepartmentId() {
        ArticleService articleService = new ArticleService();
        List<WorkPlanModel> list = articleService.getPlanListByDepartmentId(88);
        System.out.println("-------------GetPlanListByDepartmentId 方法测试中----------");
        System.out.println("查询到有" + list.size() + "个");
        for (WorkPlanModel plan : list) {
            System.out.println("planTitle:" + plan.get("title"));
        }
        System.out.println("----------------------------------------");
    }

    @Test
    public void testGetTaskListByDepartmentId() {
        ArticleService articleService = new ArticleService();
        List<WorkTaskModel> list = articleService.getTaskListByDepartmentId(88);
        System.out.println("-------------GetTaskListByDepartmentId 方法测试中----------");
        System.out.println("查询到有" + list.size() + "个");
        for (WorkTaskModel task : list) {
            System.out.println("taskTitle:" + task.get("title"));
        }
        System.out.println("----------------------------------------");
    }

    /************************** 测试 用户查看自己记录列表API *********************/
    @Test
    public void testGetOwnSummaryListByUserId() {
        ArticleService articleService = new ArticleService();
        List<WeekSummaryModel> list = articleService.getOwnSummaryListByUserId(1);
        System.out.println("-------------GetOwnSummaryListByUserId() 方法测试中----------");
        System.out.println("查询到有" + list.size() + "个");
        for (WeekSummaryModel task : list) {
            System.out.println("owntaskTitle:" + task.get("title"));
        }
        System.out.println("----------------------------------------");
    }

    @Test
    public void testGetOwnPlanListByUserId() {
        ArticleService articleService = new ArticleService();
        List<WorkPlanModel> list = articleService.getOwnPlanListByUserId(1);
        System.out.println("-------------GetOwnPlanListByUserId 方法测试中----------");
        System.out.println("查询到有" + list.size() + "个");
        for (WorkPlanModel task : list) {
            System.out.println("owntaskTitle:" + task.get("title"));
        }
        System.out.println("----------------------------------------");
    }

    @Test
    public void testGetOwnTaskListByUserId() {
        ArticleService articleService = new ArticleService();
        List<WorkTaskModel> list = articleService.getOwnTaskListByUserId(1);
        System.out.println("------------GetOwnTaskListByUserId 方法测试中----------");
        System.out.println("查询到有" + list.size() + "个");
        for (WorkTaskModel task : list) {
            System.out.println("owntaskTitle:" + task.get("title"));
        }
        System.out.println("----------------------------------------");
    }
    



}
