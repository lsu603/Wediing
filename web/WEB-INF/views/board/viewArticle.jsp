
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<c:set var="article"  value="${articleMap.article}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />
<html>
<head>
    <title>Title</title>
    <script>
        function contList(){
            location.href = "${contextPath}/board/listArticles.do";
        }
        function modArticle() {
            location.href = "${contextPath}/board/modify.do?articleNO=${article.articleNO}";
        }
    </script>
</head>
<body>
<div class="board_view">
    <table class="view_tbl">
        <tr>
            <td class="t1">${article.articleNO}</td>
            <td class="t2">${article.title}</td>
            <td class="t3">${article.name}</td>
            <td class="t4">${article.writeDate}</td>
        </tr>
        <tr>
            <td colspan="4" class="content">
<c:if test="${not empty imageFileList && imageFileList!='null' }">
    <c:forEach var="item" items="${imageFileList}" varStatus="status" >
        <img src="${contextPath}/img/${article.articleNO}/${item.imageFileName}" id="preview"  /><br>
    </c:forEach>
</c:if>
${article.content}
            </td>
        </tr>
    </table>
    <div>
        <c:if test="${user.b_rank == true}">
        <input type="button" value="수정" onclick="modArticle()"/>
        </c:if>
        <input type="button" value="목록" onclick="contList()" />
    </div>
</div>
</body>
</html>
