package com.levy.oa.config;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.levy.oa.beetl.BeetlStrUtils;
import com.levy.oa.controllers.ArticleController;
import com.levy.oa.controllers.FileController;
import com.levy.oa.controllers.IndexController;
import com.levy.oa.controllers.UserController;
import com.levy.oa.handler.BasePathHandler;
import com.levy.oa.model.DepartmentLeader;
import com.levy.oa.model.GeneralManagerModel;
import com.levy.oa.model.StaffModel;
import com.levy.oa.model.TechnologyStaff;
import com.levy.oa.model.VicePresidentModel;
import com.levy.oa.model.WorkPlanModel;
import com.levy.oa.model.WeekSummaryModel;
import com.levy.oa.model.WorkTaskModel;

public class OAConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        // TODO Auto-generated method stub
        me.setDevMode(true);
        me.setEncoding("utf-8");
        me.setMainRenderFactory(new BeetlRenderFactory());
        GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate;
        groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);

    }

    @Override
    public void configRoute(Routes me) {
        // TODO Auto-generated method stub
        me.add("/", IndexController.class);
        me.add("/api/record",ArticleController.class);
        me.add("/api/user",UserController.class);
        me.add("/api/file",FileController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
        // TODO Auto-generated method stub
       
        DruidPlugin dp = new DruidPlugin(
                "jdbc:mysql://localhost:3306/oa?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
                "root", "");
        dp.addFilter(new StatFilter());
        WallFilter wFilter =  new WallFilter();
        wFilter.setDbType("mysql");
        dp.addFilter(wFilter);
        me.add(dp);
        dp.setDriverClass("com.mysql.jdbc.Driver");
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(dp);
        activeRecordPlugin.setShowSql(true);
        activeRecordPlugin.setDialect(new MysqlDialect());
        activeRecordPlugin.addMapping("workplan", WorkPlanModel.class);
        activeRecordPlugin.addMapping("worksummary", WeekSummaryModel.class);
        activeRecordPlugin.addMapping("tasks",WorkTaskModel.class);
        activeRecordPlugin.addMapping("user",(Class<? extends Model<?>>) StaffModel.class);
        activeRecordPlugin.addMapping("user",TechnologyStaff.class);
        activeRecordPlugin.addMapping("user",DepartmentLeader.class);
        activeRecordPlugin.addMapping("user",VicePresidentModel.class);
        activeRecordPlugin.addMapping("user",GeneralManagerModel.class);
        me.add(activeRecordPlugin);


    }

    @Override
    public void configInterceptor(Interceptors me) {
        // TODO Auto-generated method stub
    }

    @Override
    public void configHandler(Handlers me) {
        // TODO Auto-generated method stub
        me.add(new BasePathHandler());
    }

}
