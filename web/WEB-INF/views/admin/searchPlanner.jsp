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
            const _id = document.getElementById('id').value;

            console.log(_id);
            location.href="${contextPath}/admin/searchPlanner.do?sch_id="+_id;
        }

        function MovePage() {
            window.opener.top.location.href="${contextPath}/admin/addPlanner.do?id=${member.b_id}";
            window.close();
        }
    </script>
</head>
<body>
<div id="Planner_Sch">
    <form>
    <div class="plannerSch">
        <input class="Sch_text" type="text" placeholder="아이디 검색" id="id"/>
        <input class="Sch_btn" type="button" onclick="fn_plannerSch()" value="검색" />
    </div>

    <table class="Sch_result">
        <tr>
            <td>아이디</td>
            <td>이름</td>
        </tr>
        <c:if test="${not empty member}">
        <tr>
            <td><a onclick="MovePage()">${member.b_id}</a></td>
            <td><a onclick="MovePage()">${member.b_name}</a></td>
        </tr>
        </c:if>
        <c:if test="${empty member}">
            <tr>
                <td colspan="2">
                    없습니다.
                </td>
            </tr>
        </c:if>

    </table>

    <input type="button" value="닫기" onclick="window.close()" />
    </form>
</div>
</body>
</html>
