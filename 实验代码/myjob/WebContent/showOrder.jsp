<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>订单信息</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" media="screen" />
</head>

<body>
	<br/>
	<div align="center">
	
			<c:if test="${empty orders}">
  			<font size="5">没有订单</font>
  		</c:if>
	</div>
	<br/><c:if test="${not empty orders}">
		<font size="5">订单信息</font>
	<table align="center" border="1">
		<tr>
			<td>订单编号</td>
			<td>订单信息</td>
			<td>订单用户</td>
			<td>订单操作</td>
		</tr>


		<c:forEach items="${orders}" var="order">
			<tr>
				<td>${order.id }</td>
				<td>
					<table align="center" border="1">
						<tr>
							<td>商品名称</td>
							<td>商品单价</td>
							<td>购买数量</td>
						</tr>

						<c:forEach items="${order.orderItems}" var="item">
							<tr>
								<td>${item.name }</td>
								<td>${item.price }</td>
								<td>${item.buynum }</td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="3" align="right">总价:${order.money}</td>
						</tr>

					</table>
			</td>
		    <td>${order.username}</td>

			<td><a
				href="${pageContext.request.contextPath}/OrderServlet?action=del&id=${order.id}">取消订单</a>
			</td>
			</tr>

		</c:forEach>
	</table>
</c:if>
</body>
</html>
