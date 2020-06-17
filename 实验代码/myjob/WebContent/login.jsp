<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<link rel="stylesheet" type="text/css" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<h1>用户网站</h1>
		<form method="post" id="f1"action="UserServlet">
			<input type="hidden" name="action" value="login">
			<table>
		    <tr>
				<td>用户</td>
				<td><input type="text" name="username"/><br /></td>
			</tr>
			<tr>
			<td>密码</td>
			<td><input type="password" name="password" /></td>
		    </tr>		 
			</table>
			<input type="submit" value="登录" />
		     <input type="reset" value="取消">
		   </form>

</body>
