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
<c:if test="${empty user}">
  		
					请先<h4><a href="login.jsp">登录</a></h4>
  		</c:if>

		<c:if test="${not empty user&&user.role eq 'admin'}">
<body>
<table align="center">
<tr>
<c:forEach items="${list}" var="item" varStatus="vs">
<td><h4>${item.name }</h4>
<img src="${pageContext.request.contextPath}${item.imgurl}" width="55px" height="55px" alt="an image" class="image" align="middle"/>				
<p>价格: ￥${item.price }</p>
<a href='ProductServlet?action=del&id=${item.id}'>删除</a></td>
</c:forEach>	
</tr>
</table>

</body>
</c:if>
<c:if test="${not empty user&&user.role ne 'admin'}"><h1>权限不足</h1></c:if>