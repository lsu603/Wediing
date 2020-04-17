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
            $("#submitShop").click(function () {
                let y = document.ShopFrm.c_day_y.value;
                let m = document.ShopFrm.c_day_m.value;
                let d = document.ShopFrm.c_day_d.value;

                if (y !="" && y.length!=4)
                    return  alert("날짜를 정확하게 입력하거나 비워주세요");
                else {
                    if (m.length > 2 && m!="") {
                        return alert("날짜를 정확하게 입력하거나 비워주세요");
                    }else if(m.length <2 && m!="") {
                        m = "0" + String(m);
                    }else
                    if (d.length > 2&& d!="") {
                        return alert("날짜를 정확하게 입력하거나 비워주세요");
                    }else if(d.length<2 && m!="") {
                        d = "0" + String(d);
                    }
                }
                if (y=="") {
                    y = "0000"
                }
                if (m=="") {
                    m = "00"
                }
                if (d=="") {
                    d = "00"
                }
                let day = String(y)+String(m)+String(d);

                let h_tar = document.getElementById("Hour");
                let m_tar = document.getElementById("Min");
                const h = h_tar.options[h_tar.selectedIndex].value;
                const min = m_tar.options[m_tar.selectedIndex].value;

                let shop_tar = document.getElementById("shop");
                const c_shop = shop_tar.options[shop_tar.selectedIndex].value;

                document.ShopFrm.c_studio_time.value = String(h)+String(m); //촬영시간
                document.ShopFrm.c_studio_day.value = day;  //촬영날짜
                                                                     //           -shop
                document.ShopFrm.c_stu_shop.value = c_shop;             //계약진행  - con

                document.ShopFrm.submit();
            });
        });
        function contList(){
            location.href = "${contextPath}/planner/contract/listCont.do";
        }
    </script>
</head>
<body>
<div id="hallInfo">
    <h1>스드메 계약</h1>
    <form name="ShopFrm" method="post" action="/planner/contract/modifyShop.do?c_mem_num=${cont.c_mem_num}">
    <table class="h_info_Tbl">
        <tr>
            <td class="t_1">담당 플래너</td>
            <td class="t_2">
                ${cont.c_p_name}
            </td>
            <td class="t_1">진행 상태</td>
            <td class="t_2">
                <input type="hidden" name="c_stu_shop">
                <select id="shop">
                    <c:forEach var="pro" items="${proList}">
                        <c:choose>
                            <c:when test="${cont.c_stu_shop == pro}">
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
            <td class="t_1">스튜디오</td>
            <td class="t_2 sh_name"><input type="text" name="c_studio_name" value="${shop.c_studio_name}" /></td>
            <td class="t_1">샵</td>
            <td class="t_2 sh_name"><input type="text" name="c_shop_name" value="${shop.c_shop_name}" /></td>
        </tr>
        <tr>
            <td class="t_1">촬영날짜</td>
            <td class="t_2 day">
                <input name="c_day_y"
                        <c:if test="${shop.c_studio_day.substring(0,4) != '0000'}">
                            value="${shop.c_studio_day.substring(0,4)}"
                        </c:if>
                /><span>년</span>
                <input name="c_day_m"
                        <c:if test="${shop.c_studio_day.substring(4,6) != '00'}">
                            value="${shop.c_studio_day.substring(4,6)}"
                        </c:if>
                /><span>월</span>
                <input name="c_day_d"
                        <c:if test="${shop.c_studio_day.substring(6,8) != '00'}">
                            value="${shop.c_studio_day.substring(6,8)}"
                        </c:if>/><span>일</span>
                <input type="hidden" name="c_studio_day">
            </td>
            <td class="t_1">촬영시간</td>
            <td class="t_2 time">
                <input type="hidden" name="c_studio_time">
                <select id="Hour">
                    <c:forTokens var="Hour" items="10,11,12,01,02,03,04,05,06" delims=",">
                        <c:choose>
                            <c:when test="${Hour==shop.c_studio_time.substring(0,2)}">
                                <option value="${Hour}" selected="true">${Hour}</option>
                            </c:when>
                            <c:otherwise><option value="${Hour}">${Hour}</option></c:otherwise>
                        </c:choose>
                    </c:forTokens></select>시
                <select id="Min">
                    <c:forTokens var="Min" items="00,10,20,30,40,50" delims=",">
                        <c:choose>
                            <c:when test="${Min==shop.c_studio_time.substring(2,4)}">
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
                        <textarea name="c_shop_memo">
                        <c:choose><c:when test="${empty shop.c_shop_memo}"></c:when>
                            <c:when test="${not empty shop.c_shop_memo}"><c:out value="${shop.c_shop_memo}" /></c:when></c:choose>
                        </textarea>
                </div>
            </td>
        </tr>
    </table>
    <div class="payForm">
        <div>계약금<input type="text" name="c_pay" value="${shop.c_pay}"/></div>
        <div>가계약금<input type="text" name="c_propay" value="${shop.c_propay}" /></div>
    </div>
    <div class="h_info_btn">
        <input type="button" id="submitShop" value="수정" />
        <input type="button" value="목록"  onclick="contList()"/>
    </div>
    </form>
</div>
</body>
</html>
