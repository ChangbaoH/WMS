import com._520it.wms.domain.Employee;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IPermissionService;
import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/6/8
 *@Description:IntelliJ IDEA
 *Version:1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {

    @Test
    public void test1() throws Exception{
        String password = ConfigTools.encrypt("1234");
        System.out.println("password = " + password);
    }

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void test2() throws Exception{
        for (int i = 0;i < 10;i++){
            Employee e = new Employee();
            e.setName("Test_"+i);
            e.setPassword("1");
            employeeService.save(e);
        }
    }

    @Test
    public void test3() throws Exception{
        employeeService.batchDelete(Arrays.asList(22L,23L));
    }



}
