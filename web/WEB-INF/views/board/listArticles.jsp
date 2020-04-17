
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="board_list">
    <h1>공지사항</h1>
    <div class="add">
        <c:if test="${user.b_rank == true}">
        <a href="${contextPath}/board/articleForm.do">글쓰기</a>
        </c:if>
    </div>
    <table class="board_tbl">
        <tr>
            <td class="t1">No</td>
            <td class="t2">제목</td>
            <td class="t3">작성자</td>
            <td class="t4">작성날짜</td>
        </tr>
        <c:forEach items="${articlesList}" var="article" varStatus="articleNum">
            <tr>
                <td class="t1">${articleNum.count}</td>
                <td class="t2"><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a></td>
                <td class="t3">${article.name}</td>
                <td class="t4">${article.writeDate}</td>
            </tr>
        </c:forEach>


    </table>
    <div class="pagingNum">
        <ul>
            <c:if test="${paging.startPage != 1 }">
                <li>
                    <a href="${contextPath}/board/listArticles.do?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">이전</a>
                </li>
            </c:if>
            <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
                <c:choose>
                    <c:when test="${p== paging.nowPage}">
                        <li>
                            <b>${p}</b>
                        </li>
                    </c:when>
                    <c:when test="${p != paging.nowPage}">
                        <li>
                            <a href="/board/listArticles.do??nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
                <li>
                    <a href="/board/listArticles.do??nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">다음</a>
                </li>
            </c:if>

        </ul>
    </div>
</div>

</body>
</html>
