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
    <script>
        if (${meg != null}) {
            alert(${meg});
        }

        $(document).ready(function () {
            $("#submitEt").click(function () {
                let et_target = document.getElementById("et");
                const c_et = et_target.options[et_target.selectedIndex].value;

                document.EtFrm.c_et.value = c_et;
                document.EtFrm.submit();
            });
        });

        function contList(){
            location.href = "${contextPath}/planner/contract/listCont.do";
        }
    </script>
</head>
<body>
<div id="hallInfo">
    <h1>기타 계약</h1>
    <form name="EtFrm" action="/planner/contract/modifyEt.do?c_mem_num=${cont.c_mem_num}" method="post">
    <table class="h_info_Tbl">
        <tr>
            <td class="t_1">담당 플래너</td>
            <td class="t_2">
                ${cont.c_p_name}
            </td>
            <td class="t_1">진행 상태</td>
            <td class="t_2">
                <input type="hidden" name="c_et">
                <select id="et">
                    <c:forEach var="pro" items="${proList}">
                        <c:choose>
                            <c:when test="${cont.c_et == pro}">
                                <option name="pro" value="${pro}" selected="true">${pro}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="pro" value="${pro}">${pro}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="t_1">신랑</td>
            <td class="t_2 m_name">${cont.c_man}</td>
            <td class="t_1">신부</td>
            <td class="t_2 w_name">${cont.c_woman}</td>
        </tr>
        <tr>
            <td class="t_1">예식날짜</td>
            <td class="t_2 day">
                <input name="c_day_y"
                        <c:if test="${cont.c_day.substring(0,4) != '0000'}">
                            value="${cont.c_day.substring(0,4)}"
                        </c:if>
                /><span>년</span>
                <input name="c_day_m"
                        <c:if test="${cont.c_day.substring(4,6) != '00'}">
                            value="${cont.c_day.substring(4,6)}"
                        </c:if>
                /><span>월</span>
                <input name="c_day_d"
                        <c:if test="${cont.c_day.substring(6,8) != '00'}">
                            value="${cont.c_day.substring(6,8)}"
                        </c:if>/><span>일</span>
            </td>
        </tr>

        <tr>
            <td colspan="4" rowspan="2">
                <div class="memo_frm">
                        <textarea name="c_et_memo">
                        <c:choose><c:when test="${empty et.c_et_memo}"></c:when>
                            <c:otherwise><c:out value="${et.c_et_memo}" /></c:otherwise></c:choose>
                        </textarea>
                </div>
            </td>
        </tr>
    </table>

    <div class="h_info_btn">
        <input type="button" value="수정" id="submitEt" />
        <input type="button" value="목록"  onclick="contList()"/>
    </div>
    </form>
</div>
</body>
</html>
