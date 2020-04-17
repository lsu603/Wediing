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
    <title>회원 관리</title>
</head>
<script>
    function fn_coupleSch(){
        const _name = document.getElementById('name').value;

        console.log(_name);
        location.href="${contextPath}/planner/couple/searchCouple.do?name="+_name;
    }
</script>
<body>
<div id="memberManag">
    <h2>회원 관리</h2>
    <div class="member_search">
        <input type="text" placeholder="이름" id="name" />
        <input type="button" value="검색" onclick="fn_coupleSch()" />
    </div>
    <table class="memberTbl">
        <tr>
            <td class="memT_1">담당 플래너</td>
            <td class="memT_2">
                신랑<br />
                신부
            </td>
            <td class="memT_3">
                신랑 연락처<br />
                신부 연락처
            </td>
            <td class="memT_4">진행<br />상태</td>
            <td class="memT_5"><a href="#"></a></td>
        </tr>
        <c:if test="${listCouple !=null }" >
            <%--            플래너 목록이 존재할때--%>
            <c:forEach  var="couple" items="${listCouple}" >
                <tr>
                    <td class="memT_1" rowspan="2">${couple.c_p_name}</td>
                    <td class="memT_2 T_1" >
                        ${couple.c_man}
                    </td>
                    <td class="memT_3 T_1">
                        ${couple.c_m_tel}
                    </td>
                    <td class="memT_4" rowspan="2">
                        <c:choose>
                            <c:when test="${couple.c_pro == '가'}">△</c:when>
                            <c:when test="${couple.c_pro == '정'}">⭕</c:when>
                            <c:when test="${couple.c_pro == '완료'}">★</c:when>
                            <c:when test="${couple.c_pro == '취소'}">X</c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </td>
                    <td class="memT_5" rowspan="2"><a href="${contextPath}/planner/couple/coupleInfo.do?c_num=${couple.c_mem_num}">관리</a></td>
                </tr>
                <tr>
                    <td class="memT_2 T_2" >
                            ${couple.c_woman}
                    </td>
                    <td class="memT_3 T_2">
                            ${couple.c_w_tel}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <div class="pagingNum">
        <ul>
            <c:if test="${paging.startPage != 1 }">
                <li>
                    <a href="${contextPath}/planner/couple/listCouple.do?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">이전</a>
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
                            <a href="/planner/couple/listCouple.do?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
                <li>
                    <a href="/planner/couple/listCouple.do?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">다음</a>
                </li>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
