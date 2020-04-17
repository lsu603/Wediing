<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
	
<html>
<body>
<c:choose>
	<c:when test="${isLogOn==true and not empty memberInfo and (rank=='planner'or rank=='admin')}">
		<%--				플래너 로그인확인--%>
		<ul>
			<c:if test="${rank == 'admin'}">
				<li><a href="${contextPath}/admin/listPage.do?">플래너관리</a></li>
			</c:if>
			<li><a href="${contextPath}/planner/couple/listCouple.do">회원관리</a></li>
			<li><a href="${contextPath}/planner/contract/listCont.do">계약관리</a></li>
			<li><a href="${contextPath}/board/listArticles.do">공지사항</a></li>
			<li><a class="logout" href="${contextPath}/member/logout.do">로그아웃</a></li>
		</ul>
	</c:when>
	<c:when test="${isLogOn==true and not empty memberInfo}">
		<%--				 로그인확인--%>
		<ul>
			<li><a class="logout" href="${contextPath}/member/logout.do">로그아웃</a></li>
		</ul>

	</c:when>
	<c:otherwise>
		<ul>
			<li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
		</ul>
 </c:otherwise>
</c:choose>
</body>
</html>

