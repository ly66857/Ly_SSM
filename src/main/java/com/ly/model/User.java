package com.ly.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
private String username;
private String password;
private String nickname;
private String email;
//属性要有get/set方法

public User(String a, String b, String c, String d) {
	this.username=a;
	this.password=b;
	this.nickname=c;
	this.email=d;
}



public User() {
	super();
	// TODO Auto-generated constructor stub
}



@NotEmpty(message="用户名不能为空")
//校验注解 设置在get上 用户名不能为空
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

@NotEmpty(message="密码不能为空")
@Size(min=1,max=10,message="密码长度不能少于1位，不能大于10位")
//长度校验
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}

@Email(message="请输入正确的邮箱格式")
@NotEmpty(message="邮箱不能为空")
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "User [username=" + username + ", password=" + password
			+ ", nickname=" + nickname + ", email=" + email + "]";
}



}
