<%@page contentType="text/html;charset=utf-8"%>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>热销图书
</h2>
<div class="book_c_04">

	<!--热销图书A开始-->
	<c:forEach items="${requestScope.categories['hotBooks'] }" var="hotBooks">
	<div class="second_d_wai">
		<div class="img">
			<a href="${pageContext.request.contextPath}/main.do?id=${hotBooks.id }&method=1"><img
					src="${pageContext.request.contextPath}/productImages/${hotBooks.productPicture }" border=0 /> </a>
		</div>
		<div class="shuming">
			<a href=href='${pageContext.request.contextPath}/main.do?id=${hotBooks.id }&method=1'>${hotBooks.productName }</a><a href="#" target="_blank"></a>
		</div>
		<div class="price">
			定价：￥${hotBooks.fixedPrice }
		</div>
		<div class="price">
			淘书斋价：￥${hotBooks.dangPrice }
		</div>
	</div>
	<div class="book_c_xy_long"></div>
	</c:forEach>
	<!--热销图书A结束-->

</div>
<div class="clear"></div>