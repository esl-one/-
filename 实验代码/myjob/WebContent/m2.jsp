<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>m2.html</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/menu.css" />
  </head>
  
  <body>    
    <div class="box">
	<h2>折叠菜单</h2>
	<ul class="menu">
		<li class="level1"><a href="m3.jsp" target="mainFrame">管理页面</a></li>
		<li class="level1">
			<a href="#none">用户管理</a>
			<ul class="level2">
				<li><a href="UserServlet?action=login" target="mainFrame">用户登录</a></li>
				<li><a href="regist.jsp" target="mainFrame">用户注册</a></li>
				
			</ul>
		</li>
		<li class="level1">
			<a href="#none">订单管理</a>
			<ul class="level2">
				<li><a href="OrderServlet?action=search" target="mainFrame">查看订单</a></li>				
			</ul>
		</li>
		<li class="level1">
			<a href="#none">商品管理</a>
			<ul class="level2">
				<li><a href="addProduct.jsp" target="mainFrame">商品添加</a></li>
				<li><a href="ProductServlet?action=godel" target="mainFrame">商品删除</a></li>				
			</ul>
		</li>  
	<li class="level1">
			<a href="#none">购物车</a>
			<ul class="level2">
				<li><a href="${pageContext.request.contextPath}/showCart.jsp" target="mainFrame">购物车</a></li>				
			</ul>
		</li> 
</div>

<script type="text/javascript" src="js/menu.js"></script>
    
    </body>
</html>
