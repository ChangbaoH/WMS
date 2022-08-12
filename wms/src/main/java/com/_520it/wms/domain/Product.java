package com._520it.wms.domain;

import com._520it.wms.util.FileUploadUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//货品对象
@Getter
@Setter
public class Product extends BaseDomain{
    private String sn;
    private String name;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private String imagePath;
    private String intro;

    private Brand brand;//many2one

    public String getSmallImagePath(){
        if (imagePath == null){
            return "";
        }
        int index = imagePath.lastIndexOf('.');
        return imagePath.substring(0,index)+FileUploadUtil.suffix+imagePath.substring(index);
    }

    //返回当前货品对象的json数据
    public String getProductJson(){
        Map<String,Object> json = new HashMap<>();
        json.put("id",id);
        json.put("name",name);
        json.put("costPrice",costPrice);
        json.put("salePrice",salePrice);
        json.put("brandName",this.brand!=null?this.brand.getName():null);

        return JSON.toJSONString(json);
    }
}
