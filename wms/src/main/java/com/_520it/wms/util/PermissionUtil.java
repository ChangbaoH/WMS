package com._520it.wms.util;

import java.lang.reflect.Method;

/*
 *@Auther:HCB
 *@Date:2022/5/28
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class PermissionUtil {

    //拼接方法的权限表达式
    public static String buildExpression(Method method) {
        //获取当前方法所在类的全限定名称
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        return className + ":" + methodName;
    }
}
