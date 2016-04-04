package com.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.levy.oa.model.DepartmentLeader;
import com.levy.oa.model.GeneralManagerModel;
import com.levy.oa.model.TechnologyStaff;
import com.levy.oa.model.VicePresidentModel;
import com.levy.oa.model.WorkPlanModel;
import com.levy.oa.model.WeekSummaryModel;
import com.levy.oa.model.WorkTaskModel;


public class TestGetList {
    static DruidPlugin dPlugin = null;
    static ActiveRecordPlugin activeRecordPlugin = null;

    @BeforeClass
    public static void Setup() throws Exception {
        dPlugin = new DruidPlugin(
                "jdbc:mysql://120.24.163.155:3306/oa?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
                "lu", "Lu25270638");
        dPlugin.start();
        dPlugin.setDriverClass("com.mysql.jdbc.Driver");
        activeRecordPlugin = new ActiveRecordPlugin("SECOND",dPlugin);
        activeRecordPlugin.setShowSql(true);
        activeRecordPlugin.setDialect(new MysqlDialect());
        activeRecordPlugin.addMapping("workplan", WorkPlanModel.class);
        activeRecordPlugin.addMapping("worksummary", WeekSummaryModel.class);
        activeRecordPlugin.addMapping("tasks", WorkTaskModel.class);
 //     activeRecordPlugin.addMapping("user", (Class<? extends Model<?>>) UserModel.class);
        activeRecordPlugin.addMapping("user",TechnologyStaff.class);
        activeRecordPlugin.addMapping("user",DepartmentLeader.class);
        activeRecordPlugin.addMapping("user",VicePresidentModel.class);
        activeRecordPlugin.addMapping("user",GeneralManagerModel.class);
        activeRecordPlugin.start();
    }

    @AfterClass
    public static void tearDown() {
        dPlugin.stop();
        activeRecordPlugin.stop();
    }


    @Test
    public void testgetPlanListByUserId() {
        List<WorkPlanModel> plans = new WorkPlanModel().getPlanListByUserId(45);
        boolean plan = (plans.size() > 0);
        Assert.assertEquals(plan, true);
    }

    @Test
    public void testgetSummaryListByUserId() {
        List<WeekSummaryModel> summarys = new WeekSummaryModel().getSummaryListByUserId(45);
        boolean summary = (summarys.size() > 0);
        Assert.assertEquals(summary, true);
    }

    @Test
    public void testgetTaskListByExecutor() {
        List<WorkTaskModel> tasks = new WorkTaskModel().getTaskListByExecutor("levy");
        boolean task = (tasks.size() > 0);
        Assert.assertEquals(task, true);
    }
    @Test
    public void testgetPlanListByDepartmentid(){
        List<WorkPlanModel> plans = new WorkPlanModel().getPlanListByDepartmentid(2);
        boolean plan = (plans.size() > 0);
        Assert.assertEquals(plan, true);
    }
    @Test
    public void testgetSummaryListByDepartmentId(){
        List<WeekSummaryModel> summarys = new WeekSummaryModel().getSummaryListByDepartmentId(2);
        boolean summary = (summarys.size() > 0);
        Assert.assertEquals(summary, true);
    }
    @Test
    public void testSaveStaff(){
     boolean one =  new  TechnologyStaff().set("department","测试部").set("password","123456").set("username","techstaff")
      .set("departmentid",1).set("chinaName","李四").save();
     boolean two =    new  DepartmentLeader().set("department","测试部").set("password","123456").set("username","techleader")
        .set("departmentid",1).set("chinaName","李五").save();
     boolean three =    new  VicePresidentModel().set("department","测试部").set("password","123456").set("username","VR")
        .set("departmentid",1).set("chinaName","李六").save();
     boolean four =    new  GeneralManagerModel().set("department","测试部").set("password","123456").set("username","GM")
        .set("departmentid",1).set("chinaName","李七").save();
    }

}
