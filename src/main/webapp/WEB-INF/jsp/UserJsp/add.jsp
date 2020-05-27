<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl core库 -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!-- SpringMVC 为了方便表单处理，提供的表单标签库 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<title>ADD</title>
</head>
<body>

<!-- enctype:用来规定数据发送到服务器之前，用什么方式对表单进行编码
			 默认值是 "application/x-www-form-urlencode"键值对传递
			 		  "multipart/form-data" 将表单数据变成二进制提交
	
 -->

<sf:form method="POST" modelAttribute="use" action="add"  enctype="multipart/form-data">
<!-- 1.不需要写action 默认以相对路由/文件名提交 即/user/add 并且自动传modeAttribute作为路由映射的方法的参数
	 2. modelAttribute是向model中放入一个对象，就是这个form表单对象
-->
		用户名 : <sf:input path="username" />
		<sf:errors path="username"></sf:errors>
		<br>
		密码 : <sf:input path="password" />
		<sf:errors path="password"></sf:errors>
		<br>
			昵称 : <sf:input path="nickname" />
		<sf:errors path="nickname"></sf:errors>
		<br>
			邮箱 : <sf:input path="email" />
		<sf:errors path="email"></sf:errors>
		<br>
		文件1:<input type="file" name="files">
		文件2:<input type="file" name="files">
 <!--
 input:传递对象的成员变量属性
 error:错误校验与Controller中的注解和User类中的校验连结使用
-->
<input type="submit" value="添加该用户">
</sf:form>
</body>
</html>