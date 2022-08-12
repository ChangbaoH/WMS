package com._520it.generator;

import com._520it.wms.domain.BaseDomain;
import lombok.Getter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;


/*
 *@Auther:HCB
 *@Date:2022/6/15
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
@Getter
public class ClassInfo {
    private String basePkg;//基础包名
    private String className;//domain类的简单类名
    private String objectName;//domain对象的名称,首字母小写
    private List<String> props = new ArrayList<>();//当前对象所有的属性名称
    public ClassInfo(Class<?> tClass) throws Exception {
        basePkg = tClass.getPackage().getName().substring(0,tClass.getPackage().getName().lastIndexOf('.'));
        className = tClass.getSimpleName();
        objectName = className.substring(0,1).toLowerCase()+className.substring(1);
        //内省
        BeanInfo beanInfo = Introspector.getBeanInfo(tClass, BaseDomain.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            props.add(pd.getName());
        }
    }

}
