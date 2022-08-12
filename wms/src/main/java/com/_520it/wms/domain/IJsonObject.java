package com._520it.wms.domain;

//让domain类来实现，设置把哪些属性转换为json
public interface IJsonObject {
    Object toJson();
}
