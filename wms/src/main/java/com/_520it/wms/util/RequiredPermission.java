package com._520it.wms.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *@Auther:HCB
 *@Date:2022/5/28
 *@Description:需要权限的注解标签
 *Version:1.0
 */
@Target(ElementType.METHOD)//贴在方法上
@Retention(RetentionPolicy.RUNTIME)//可以存活在JVM中，使用反射赋予RequiredPermission功能
public @interface RequiredPermission {
    String value();//表示权限的名称
}
