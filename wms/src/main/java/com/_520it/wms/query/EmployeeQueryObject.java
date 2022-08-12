package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:员工的高级查询对象，封装了高级查询信息
 *Version:1.0
 */
public class EmployeeQueryObject extends QueryObject{
    @Getter@Setter
    private String keyword;
    @Getter@Setter
    private Long deptId = -1L;

    /*****************************************************************************/

    public void customizedQuery(){
        if (hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery("(obj.name LIKE ? OR obj.email LIKE ?)",key,key);
        }
        if (deptId > 0){
            addQuery("obj.dept.id = ?",deptId);
        }
    }






}
