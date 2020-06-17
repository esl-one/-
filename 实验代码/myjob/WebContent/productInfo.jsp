<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>商品详细信息页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" media="screen" />

</head>

<body>
	<br>
	<br>
	<table align="center">
		<tr>
			<td rowspan="5"><img src="${pageContext.request.contextPath}${pd.imgurl}" width="100px" height="100px">
			</td>
			<td>商品名称:${pd.name}</td>
		</tr>

		<tr>
			<td>商品价格:${pd.price}</td>
		</tr>
		<tr>
			<td>商品类别:${pd.category}</td>
		</tr>
		<tr>
			<td>商品数量:${pd.pnum}</td>
		</tr>
		<tr>
			<td>商品描述:${pd.description}</td>
		</tr>

		<tr>
			<td colspan="2" align="right">
		</tr>
		
	</table>
	<a href="CartServlet?action=add&id=${pd.id }">加入购物车</a>	
</body>
</html>
