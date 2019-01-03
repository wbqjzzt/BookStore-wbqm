<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>
			分类浏览
		</h2>
		<!--1级分类开始  遍历结果集，显示category的name-->
		
			<div class="bg_old" onmouseover="this.className = 'bg_white';" onmouseout="this.className = 'bg_old';">
				<h3>
					<!--显示category的name-->
					[<a href='#'></a>]
				</h3>
				<ul class="ul_left_list">

					<c:forEach items="${requestScope.categories['category']}" var="category" >
                        <div class="bg_old" onmouseover="this.className = 'bg_white';" onmouseout="this.className = 'bg_old';">
                            <h3>[ <a href="#">${category.name}</a> ]</h3>
                            <ul class="ul_left_list">
                                <c:forEach items="${category.subCategory}" var="subCate" >
                                    <li>
                                        <a href="${pageContext.request.contextPath}/main/booklistAction?c1=${category.id}&c2=${subCate.id}">
                                            ${subCate.name}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:forEach>
					
				</ul>
				
				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
			<!--1级分类结束-->
		
		<div class="bg_old">
			<h3>
				&nbsp;
			</h3>
		</div>
	</div>
</div>
