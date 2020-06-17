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
	<c:if test="${empty user }">
<li>用户未登录</li>
</c:if>

<c:if test="${not empty user }">
<li>当前用户:${user.username}</li>
<li><a
	href='UserServlet?action=logout'>注销</a>
</li>
</c:if>
<table align="center" >
<tr>
<c:forEach items="${list}" var="item" varStatus="vs">
<td><h4>${item.name }</h4>
<img src="${pageContext.request.contextPath}${item.imgurl}" width="100px" height="100px" alt="an image" class="image" align="middle"/>				
<p>价格: ￥${item.price }</p>
<a href='ProductServlet?action=findById&id=${item.id}'>速速抢购</a></td>
</c:forEach>	
</tr>
</table>

</body>
