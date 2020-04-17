
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%--    <link href="${pageContext.request.contextPath}/resources/css/sytles.css?ver=1" rel="stylesheet"/>--%>
    <title>로그인창</title>
    <c:if test='${not empty message }'>
        <script>
            window.onload=function()
            {
                result();
            }

            function result(){
                alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
            }
        </script>
    </c:if>
</head>
<body>
<form class="logins" action="${contextPath}/member/login.do" method="post">
<c:choose>
    <c:when test="${isLogOn==true and not empty memberInfo and isPlanner==true}">
        <%--				플래너 로그인확인--%>
    플래너
    </c:when>
    <c:when test="${isLogOn==true and not empty memberInfo}">
        <%--				 로그인확인--%>
        회원

    </c:when>
    <c:otherwise>
    <h1 class="logins_h1">LOGIN</h1>
    <div class="logins_login">
        <input class="login_info login_id" type="text" name="b_id" placeholder="아이디" />
        <input class="login_info login_pw" type="password" name="b_pwd" placeholder="비밀번호" />
    </div>
    <input class="logins_sub" type="submit" value="로그인" />
    <a href="${contextPath}/member/memberForm.do">회원가입</a>
    </c:otherwise>
</c:choose>
</form>
</body>
</html>
