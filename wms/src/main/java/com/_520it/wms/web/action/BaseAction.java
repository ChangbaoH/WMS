package com._520it.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
//所以Action的基类
public class BaseAction extends ActionSupport implements Preparable {

    public static final String LIST = "list";

    //在所有的Action方法之前都会执行
    @Override
    public void prepare() throws Exception {
    }

    //把数据存储在context,留给子类使用
    protected void putContext (String name,Object value){
        ActionContext.getContext().put(name, value);
    }
    protected void putResponseText(String msg) throws Exception{
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(msg);
    }

    /*
     * @Author: HCB
     * @Description:  向客户端输出Json字符串
     * @Date: 2022/6/21
     * @Parms: 
     * @ReturnType: 
     */
    protected void putJson(Object value) throws Exception{
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(value));
    }
}
