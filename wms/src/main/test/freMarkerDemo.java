import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/*
 *@Auther:HCB
 *@Date:2022/6/13
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class freMarkerDemo {

    public static void main(String[] args) throws Exception {
        //1.创建配置对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        //2.设置从哪个目录加载模板文件
        configuration.setDirectoryForTemplateLoading(new File("templates"));
        //3.获取模板对象
        Template template = configuration.getTemplate("hello.html");

        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("name","will");
        dataModel.put("age",17);
        //4.合并模板和数据
        Writer out = new FileWriter("./templates/test.html");
        template.process(dataModel,out);
    }

}
