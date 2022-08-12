package com._520it.generator;

import com._520it.wms.domain.*;
import freemarker.template.Configuration;
import freemarker.template.Template;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

/*
 *@Auther:HCB
 *@Date:2022/6/15
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class CodeGenerator {

    private static Configuration config;
    static {
        try {
            //1.创建配置对象
            config = new Configuration(Configuration.VERSION_2_3_22);
            //2.设置模板文件加载目录
            config.setDirectoryForTemplateLoading(new File("templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        //generateCode();
        System.out.println("代码生成完成!");
    }

    public static void generateCode() throws Exception {
        ClassInfo classInfo = new ClassInfo(SaleAccount.class);
        //1.生成DAO组件
        createFile(classInfo,"IDAO.java","src/main/java/{0}/dao/"+"I{1}DAO.java");
        createFile(classInfo,"IDAOImpl.java","src/main/java/{0}/dao/impl/"+"{1}DAOImpl.java");
        //2.生成Service组件
        createFile(classInfo,"IService.java","src/main/java/{0}/service/"+"I{1}Service.java");
        createFile(classInfo,"IServiceImpl.java","src/main/java/{0}/service/impl/"+"{1}ServiceImpl.java");
        //3.生成Query和Action组件
        createFile(classInfo,"QueryObject.java","src/main/java/{0}/query/"+"{1}QueryObject.java");
        createFile(classInfo,"Action.java","src/main/java/{0}/web/action/"+"{1}Action.java");
        //4.生成list.jsp和input.jsp以及映射文件
        createFile(classInfo,"list.jsp","src/main/webapp/WEB-INF/views/{2}/list.jsp");
        createFile(classInfo,"input.jsp","src/main/webapp/WEB-INF/views/{2}/input.jsp");
        createFile(classInfo,"hbm.xml","src/main/resources/{0}/domain/{1}.hbm.xml");
        //5.追加配置文件
        appendToXml(classInfo,"dao.xml","src/main/resources/applicationContext-dao.xml");
        appendToXml(classInfo,"service.xml","src/main/resources/applicationContext-service.xml");
        appendToXml(classInfo,"action.xml","src/main/resources/applicationContext-action.xml");

    }

    /*MethodDescription
    *@param:
    *@return:
    *@auther:HCB
    *@date:
    */
    private static void appendToXml(ClassInfo classInfo,String templateName,String targetFile) throws Exception {
        Template template = config.getTemplate(templateName);

        StringWriter out = new StringWriter();
        template.process(classInfo,out);
        String appendXml = out.toString();
        XmlUtil.mergeXML(new File(targetFile),appendXml);
    }


    /*MethodDescription
    *@param:classInfo 封装数据的对象
    *@param:templateName 模板名称
    *@param:path 生成的路径
    *@return:
    *@auther:HCB
    *@date:
    */
    private static void createFile(ClassInfo classInfo,String templateName,String path) throws Exception {
        //1.生成DAO接口文件
        Template template = config.getTemplate(templateName);
        //4.设置占位参数
        String filePath = MessageFormat.format(path,classInfo.getBasePkg().replace(".","/"),classInfo.getClassName(),classInfo.getObjectName());

        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        template.process(classInfo,new FileWriter(file));
    }
}
