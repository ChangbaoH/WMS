package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

//供应商
@Getter
@Setter
public class Supplier extends BaseDomain{
    private String name;
    private String address;
    private String phone;
}
