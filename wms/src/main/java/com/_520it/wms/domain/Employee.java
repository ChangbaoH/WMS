package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Employee extends BaseDomain{
	private String name;
	private String password;
	private String email;
	private Integer age;
	private boolean admin = false;

	private Department dept;
	//一个用户拥有多个角色，一个角色可以被赋给多个用户，单向多对多
	private List<Role> roles = new ArrayList<>();

	//获取用户的角色
	public String getRoleNames(){
		if (this.admin){
			return "[超级管理员]";
		}
		if (roles.size()==0){
			return "[暂无角色]";
		}
		StringBuilder sb = new StringBuilder(40).append("[");
		for (Role role : roles) {
			sb.append(role.getName()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
