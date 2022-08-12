package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:员工的高级查询对象，封装了高级查询信息
 *Version:1.0
 */
@Getter
@Setter
public class SystemMenuQueryObject extends QueryObject{

    private Long parentId = -1L;//上级菜单的id

    private String parentSn;//父菜单的sn

    public void customizedQuery(){
        if (parentId > 0){
            addQuery("obj.parent.id = ?",parentId);
        }else {
            addQuery("obj.parent IS NULL");
        }
    }
    
}
