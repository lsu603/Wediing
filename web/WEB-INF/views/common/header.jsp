<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
	
<html>
<body>
<a href="${contextPath}/member/loginForm.do">
	<img class="wc_img" src="${contextPath}/resources/images/main.jpg">
	<span class="wc_span">Wedding Consulting</span>
</a>
</body>
</html>

