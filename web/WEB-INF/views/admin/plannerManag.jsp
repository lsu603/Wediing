<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>매니저 관리</title>
</head>
<script>

   function Addplanner(){
    window.open('${contextPath}/admin/searchPlanner.do','plannerName',"width=400, height=300, left=100, top=50")
   }
</script>
<body>
<div id="p_Manages_Form">
    <h1>플래너 관리</h1>
    <table class="p_Manages">
        <tr>
            <td class="p_id">아이디</td>
            <td class="p_name">이름</td>
            <td class="p_manage">관리</td>
        </tr>
        <c:if test="${listPlanner !=null }" >
            <%--            플래너 목록이 존재할때--%>
            <c:forEach  var="planner" items="${listPlanner}" >
                <tr>
                    <td class="p_id">${planner.p_id}</td>
                    <td class="p_name">${planner.p_name}</td>
                    <td class="p_manage">
                        <a href="${contextPath}/admin/plannerInfo.do?p_id=${planner.p_id}">선택</a>
                        <a href="${contextPath}/admin/deletePlanner.do?id=${planner.p_id}"onclick="return confirm('삭제하시겠습니까?')">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

    </table>

    <div class="pagingNum">
        <ul>
            <c:if test="${paging.startPage != 1 }">
                <li>
                <a href="${contextPath}/admin/listPage.do?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">이전</a>
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
                        <a href="/admin/listPage.do?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
            <li>
                <a href="/admin/listPage.do?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">다음</a>
            </li>
            </c:if>

        </ul>
    </div>
    <div class="addPlanner">
        <input type="button" value="플래너 추가" onclick="Addplanner()" />
    </div>
</div>



</table>
</body>
</html>