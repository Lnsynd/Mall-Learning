package com.lqs.mpg;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Created by 刘千山 on 2023/5/31/031-17:21
 */
public class MyBatisPlusGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") ;

        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("刘千山");
        gc.setOpen(false);
        gc.setFileOverride(false);
//        实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setEntityName("%s");
        gc.setControllerName("%sController");
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setXmlName("%sMapper");
        autoGenerator.setGlobalConfig(gc);


        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mall-learning?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        autoGenerator.setDataSource(dataSourceConfig);


        // 包名配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.lqs");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("mapper.xml");
        autoGenerator.setPackageInfo(pc);


        // 策略配置 : 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名规则
        sc.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名规则
        sc.setEntityLombokModel(true);
        sc.setInclude(scanner("表名，多个英文逗号分割").split(",")); // 需要包含的表
        sc.setControllerMappingHyphenStyle(true); // 驼峰转连字符
        autoGenerator.setStrategy(sc);


        //       执行
        autoGenerator.execute();
    }
}
