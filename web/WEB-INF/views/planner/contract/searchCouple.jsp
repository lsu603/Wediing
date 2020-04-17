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
    <link href="${contextPath}/resources/css/sytles.css?ver=43" rel="stylesheet"/>
    <title>계약 추가</title>
    <script>
        function fn_plannerSch(){
            const _name = document.getElementById('name').value;

            console.log(_name);
            location.href="${contextPath}/planner/contract/searchCouple.do?name="+_name;
        }

        function MovePage(num) {
            window.opener.top.location.href="${contextPath}/planner/contract/addContCouple.do?c_mem_num="+num;
            window.close();
        }
    </script>
</head>
<body>
<div id="Planner_Sch">
    <form>
    <div class="plannerSch">
        <input class="Sch_text" type="text" placeholder="이름 검색" id="name"/>
        <input class="Sch_btn" type="button" onclick="fn_plannerSch()" value="검색" />
    </div>

    <table class="Sch_result">
        <tr>
            <td>담당 플래너</td>
            <td>신랑<br>신부</td>
            <td>연락처</td>
        </tr>
        <c:choose>
        <c:when test="${not empty coupleList}">
        <c:forEach items="${coupleList}" var="couple">
        <tr>
            <td><a onclick="MovePage(${couple.c_mem_num})">${couple.c_p_name}</a></td>
            <td><a onclick="MovePage(${couple.c_mem_num})">${couple.c_man}<br>${couple.c_woman}</a></td>
            <td><a onclick="MovePage(${couple.c_mem_num})">${couple.c_m_tel}<br>${couple.c_w_tel}</a></td>
        </tr>
        </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="3">
                    없습니다.
                </td>
            </tr>
        </c:otherwise>
        </c:choose>
    </table>

    <input type="button" value="닫기" onclick="window.close()" />
    </form>
</div>
</body>
</html>
