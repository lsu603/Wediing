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
    <title>매니저 추가</title>
    <script>
        function fn_plannerSch(){
            const _name = document.getElementById('name').value;

            console.log(_name);
            location.href="${contextPath}/planner/couple/searchPlanner.do?sch_name="+_name+"&c_mem_num=${c_mem_num}";
        }

        function MovePage(p_id) {
            console.log(p_id);
            window.opener.top.location.href="${contextPath}/planner/couple/modifyCouplePlanner.do?p_id="+p_id+"&c_mem_num=${c_mem_num}";
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
            <td>아이디</td>
            <td>이름</td>
        </tr>
        <c:choose>
        <c:when test="${not empty plannerList}">
        <c:forEach items="${plannerList}" var="planner">
        <tr>
            <td><a onclick="MovePage('${planner.p_id}')">${planner.p_id}</a></td>
            <td><a onclick="MovePage('${planner.p_id}')">${planner.p_name}</a></td>
        </tr>
        </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="2">
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
