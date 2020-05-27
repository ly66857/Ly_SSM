<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<title>Insert title here</title>
</head>
<body>
登陆的用户：${loginUser.nickname}<br>

<h1><a href="add">添加</a></h1><br>
<!-- 相对路径是XXX/user 因为请求到该页面的路由是XXX/user/list 
所以他的相对路径是/user  在Controller增加的路由为add-->

<c:forEach items="${users}" var="user">
${user.username} -- ${user.password}---${user.nickname}---${user.email}<br>
</c:forEach>

</body>
</html>