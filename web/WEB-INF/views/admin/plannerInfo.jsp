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

   function plannerList(){
    location.href = "${contextPath}/admin/listPage.do"
   }

   $(document).ready(function () {
    $("#submit").click(function () {
        const b_tel = document.InfoFrm.b_tel.value;
        let p_memo = document.InfoFrm.p_memo.value;
        document.InfoFrm.submit();

    });
   });

</script>
<body>
<div id="plannerInfo">
    <h2>플래너 정보</h2>
    <form name="InfoFrm" method="post" action="/admin/modifyInfo.do?id=${planner.p_id}">
    <table class="plannerTbl" method="post">
        <tr>
            <td class="plannerTbl_1">이름</td>
            <td class="plannerTbl_2">${planner.p_name}</td>
        </tr>
        <tr>
            <td class="plannerTbl_1">아이디</td>
            <td class="plannerTbl_2">
                ${planner.p_id}
            </td>
        </tr>
        <tr>
            <td class="plannerTbl_1">연락처</td>
            <td class="plannerTbl_2">
                <input type="text" name="b_tel" value="${member.b_tel}" onKeyPress="return checkNum(event)"/>
            </td>
        </tr>
        <tr>
            <td class="plannerTbl_1 memo memo_1">메모</td>
            <td class="plannerTbl_2 memo memo_2">
            <textarea style="resize: none;" name="p_memo"><c:choose><c:when test="${empty planner.p_memo}">입사일 :

휴무 :

급여 :</c:when><c:when test="${not empty planner.p_memo}"><c:out value="${planner.p_memo}" /></c:when></c:choose>
            </textarea>
            </td>
        </tr>
    </table>
    </form>
    <div class="plannerInfo_oth">
        <input type="button" id="submit" value="저장"/>
        <input type="button" value="목록" onclick="plannerList()" />
    </div>
</div>
</body>
</html>