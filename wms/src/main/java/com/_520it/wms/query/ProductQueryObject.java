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
public class ProductQueryObject extends QueryObject{

    private String keyword;//查询的货品或sn中包含的关键字
    private Long brandId = -1L;//货品品牌
    public void customizedQuery(){
        if (hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery("obj.name LIKE ? OR obj.sn LIKE ?",key,key);
        }
        if (brandId>0){
            addQuery("obj.brand.id = ?",brandId);
        }
    }
    
}
