<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>添加商品</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" media="screen" />
</head>
<c:if test="${empty user}">
  		
					请先<h4><a href="login.jsp">登录</a></h4>
  		</c:if>

		<c:if test="${not empty user&&user.role eq 'admin'}">
	<body>	
	<br>
	<br><div align="center">  		
	<form id="f1" action=AddProductServlet method="post" enctype="multipart/form-data" >
		<table border="1" width="65%" align="center">
			<caption>
				<b>添加商品</b>
			</caption>

			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price"></td>
			</tr>

			<tr>
				<td>商品类别</td>
				<td><select name="category">
						<option>--请选择类别--</option>
						<option value="生鲜">生鲜</option>
						<option value="食品">食品</option>
						<option value="非食品">非食品</option>
				</select></td>
			</tr>

			<tr>
				<td>商品数量</td>
				<td><input type="text" name="pnum"></td>
			</tr>

			<tr>
				<td>商品图片</td>
				<td><input type="file" name="f"></td>
			</tr>

			<tr>
				<td>商品描述</td>
				<td><textarea name="description" rows="10" cols="40"></textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="添加商品"> <input type="reset" value="取消"></td>
			</tr>
		</table>

	</form>
	</div>
</body>
</c:if>
<c:if test="${not empty user&&user.role ne 'admin'}"><h1>权限不足</h1></c:if>
</html>
