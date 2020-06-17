<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>注册界面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" media="screen" />


<style type="text/css">
span {
	font-color: red;
}
</style>

</head>

<body>

	<form id="f1" action=UserServlet method="post">
		<input type="hidden" name="action" value="regist"><!-- 我们当前是一个注册操作 -->
		<table border="1" align="center" width="65%">
			<caption>用户注册</caption>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" ><span
					id="username_span"></span></td>
			</tr>


			<tr>
				<td>密码</td>
				<td><input type="password" name="password" ><span
					id="password_span"></span></td>
			</tr>

			<tr>
				<td>昵称</td>
				<td><input type="text" name="nickname"><span
					id="nickname_span"></span></td>
			</tr>

			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email" ><span
					id="email_span"></span></td>
			</tr>
			 <tr>
				<td colspan="2" align="center"><input type="submit" value="注册">&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="reset" value="取消"></td>
			</tr>

		</table>
	</form>
</body>
</html>
