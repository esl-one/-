<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="navigator">
	<div class="navigator_content">
		<div>
			<span>商品管理</span><br>
			<span><a href="${pageContext.request.contextPath }/book/BookServlet?method=addUI">添加商品</a></span><br>
			<span><a href="${pageContext.request.contextPath }/book/BookServlet?method=show">浏览商品</a></span><br>
		</div>
	</div>

</div>
