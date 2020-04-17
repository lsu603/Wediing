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
            $("#Hallsubmit").click(function () {

                let hall_target = document.getElementById("hall");
                const c_hall = hall_target.options[hall_target.selectedIndex].value;
                let h_tar = document.getElementById("Hour");
                let m_tar = document.getElementById("Min");
                const h = h_tar.options[h_tar.selectedIndex].value;
                const m = m_tar.options[m_tar.selectedIndex].value;

                document.HallFrm.c_time.value = String(h)+String(m); //예식시간
                const floor = document.HallFrm.c_floor.value;       //예식장
                const person = document.HallFrm.c_person.value;     //하객수
                const memo = document.HallFrm.c_hall_memo.value;    //메모
                const pay = document.HallFrm.c_pay.value;           //계약금
                const propay = document.HallFrm.c_propay.value;     //가계약금
                                                                     //           -hall
                document.HallFrm.c_hall.value = c_hall;             //계약진행  - con

                document.HallFrm.submit();
            });
        });
        function contList(){
            location.href = "${contextPath}/planner/contract/listCont.do";
        }
    </script>
</head>
<body>
<div id="hallInfo">
    <h1>홀 계약</h1>
    <form name="HallFrm" method="post" action="/planner/contract/modifyHall.do?c_mem_num=${cont.c_mem_num}">
    <table class="h_info_Tbl">
        <tr>
            <td class="t_1">담당 플래너</td>
            <td class="t_2">
                ${cont.c_p_name}
            </td>
            <td class="t_1">진행 상태</td>
            <td class="t_2">
                <input type="hidden" name="c_hall">
                <select id="hall">
                <c:forEach var="pro" items="${proList}">
                    <c:choose>
                        <c:when test="${cont.c_hall == pro}">
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
            <td class="t_1">웨딩홀</td>
            <td class="t_2 sh_name"><input type="text" name="c_floor" value="${hall.c_floor}" /></td>
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
                <input type="hidden" name="c_day">
            </td>
        </tr>
        <tr>
            <td class="t_1">보증인원</td>
            <td class="t_2 people"><input type="text" name="c_person" value="${hall.c_person}" />명</td>
            <td class="t_1">예식시간</td>
            <td class="t_2 time">
                <input type="hidden" name="c_time">
                <select id="Hour">
                    <c:forTokens var="Hour" items="10,11,12,01,02,03,04,05,06" delims=",">
                        <c:choose>
                            <c:when test="${Hour==hall.c_time.substring(0,2)}">
                                <option value="${Hour}" selected="true">${Hour}</option>
                            </c:when>
                            <c:otherwise><option value="${Hour}">${Hour}</option></c:otherwise>
                        </c:choose>
                    </c:forTokens></select>시
                <select id="Min">
                    <c:forTokens var="Min" items="00,10,20,30,40,50" delims=",">
                        <c:choose>
                            <c:when test="${Min==hall.c_time.substring(2,4)}">
                                <option value="${Min}" selected="true">${Min}</option>
                            </c:when>
                            <c:otherwise><option value="${Min}">${Min}</option></c:otherwise>
                        </c:choose>
                    </c:forTokens>
                    </select>분
            </td>
        </tr>
        <tr>
            <td colspan="4" rowspan="2">
                <div class="memo_frm">
                        <textarea name="c_hall_memo">
                        <c:choose><c:when test="${empty hall.c_hall_memo}"></c:when>
                            <c:when test="${not empty hall.c_hall_memo}"><c:out value="${hall.c_hall_memo}" /></c:when></c:choose>
                        </textarea>
                </div>
            </td>
        </tr>
    </table>
    <div class="payForm">
        <div>계약금<input type="text" name="c_pay" value="${hall.c_pay}"/></div>
        <div>가계약금<input type="text" name="c_propay"  value="${hall.c_propay}"/></div>
    </div>

    <div class="h_info_btn">
        <input type="button" id="Hallsubmit" value="수정" />
        <input type="button" value="목록"  onclick="contList()"/>
    </div>
    </form>
</div>
</body>
</html>
