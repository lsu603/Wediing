<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    if (${msg != null}) {
        alert(${msg});
    }


    $(document).ready(function () {
        $("#submit").click(function () {
            let y = document.InfoFrm.c_day_y.value;
            let m = document.InfoFrm.c_day_m.value;
            let d = document.InfoFrm.c_day_d.value;

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


            //날짜 가져오기
            let pro_target = document.getElementById("pro");
            const c_pro = pro_target.options[pro_target.selectedIndex].value;
            let c_man = document.InfoFrm.c_man.value;
            let c_woman = document.InfoFrm.c_woman.value;
            let c_m_tel = document.InfoFrm.c_m_tel.value;
            let c_w_tel = document.InfoFrm.c_w_tel.value;
            document.InfoFrm.c_day.value = day;
            document.InfoFrm.c_pro.value = c_pro;
            let c_day = document.InfoFrm.c_day.value;

            document.InfoFrm.submit();
        });
    });

    $(document).ready(function () {
        $("#submitM").click(function () {
            let c_memo = document.MemoFrm.c_memo.value;
            console.log(c_memo);
            document.MemoFrm.submit();
        });
    });

    function Addplanner(){
        window.open('${contextPath}/planner/couple/searchPlanner.do?c_mem_num=${couple.c_mem_num}','plannerName',"width=400, height=300, left=100, top=50")
    }

    function plannerList(){
        location.href = "${contextPath}/planner/couple/listCouple.do"
    }
</script>
<body>
<div id="coupleInfo">
    <h1>회원 정보</h1>
    <table class="c_info_Tbl">
        <form name="InfoFrm" method="post" action="/planner/couple/modifyInfo.do?c_num=${couple.c_mem_num}">

        <tr>
            <td class="t_1">담당 플래너</td>
            <td class="t_2">
                    <input class="mod_p" type="button" value="플래너 변경" onclick="Addplanner()" />
            </td>
            <td class="t_1">진행 상태</td>
            <td class="t_2">
                <input type="hidden" name="c_pro">
                <select id="pro">
                    <c:forEach var="pro" items="${proList}">
                        <c:choose>
                            <c:when test="${couple.c_pro == pro}">
                                <option name="pro" value="${pro}" selected="true">
                                    <c:choose>
                                        <c:when test="${pro == '가'}">△</c:when>
                                        <c:when test="${pro == '정'}">⭕</c:when>
                                        <c:when test="${pro == '완료'}">★</c:when>
                                        <c:when test="${pro == '취소'}">X</c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option name="pro" value="${pro}">
                                    <c:choose>
                                        <c:when test="${pro == '가'}">△</c:when>
                                        <c:when test="${pro == '정'}">⭕</c:when>
                                        <c:when test="${pro == '완료'}">★</c:when>
                                        <c:when test="${pro == '취소'}">X</c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="t_1">신랑</td>
            <td class="t_2 m_name"><input type="text" name="c_man" value="${couple.c_man}" /></td>
            <td class="t_1">신부</td>
            <td class="t_2 w_name"><input type="text" name="c_woman" value="${couple.c_woman}" /></td>
        </tr>
        <tr>
            <td class="t_1">연락처</td>
            <td class="t_2 m_tel">
                <input type="text" name="c_m_tel" value="${couple.c_m_tel}" />
            </td>
            <td class="t_1">연락처</td>
            <td class="t_2 w_tel">
                <input type="text" name="c_w_tel" value="${couple.c_w_tel}" />
            </td>
        </tr>
        <tr>
            <td class="t_1">예식날짜</td>
            <td class="t_2 w_day">
                <input name="c_day_y"
                            <c:if test="${couple.c_day.substring(0,4) != '0000'}">
                                value="${couple.c_day.substring(0,4)}"
                            </c:if>
                        /><span>년</span>
                <input name="c_day_m"
                        <c:if test="${couple.c_day.substring(4,6) != '00'}">
                            value="${couple.c_day.substring(4,6)}"
                            </c:if>
                        /><span>월</span>
                <input name="c_day_d"
                        <c:if test="${couple.c_day.substring(6,8) != '00'}">
                            value="${couple.c_day.substring(6,8)}"
                        </c:if>/><span>일</span>
                <input type="hidden" name="c_day">
            </td>
            <td class="t_1">관심사</td>
            <td class="t_2">${couple.c_con}</td>
        </tr>

    </form>
    <tr>
            <td class="t_1"></td>
            <td colspan="3" rowspan="2">
                <div class="memo_frm">
                <form name="MemoFrm" method="post" action="/planner/couple/addMemo.do?c_num=${couple.c_mem_num}">
                <textarea name="c_memo"></textarea>
                </form>
                <input class="text_btn" type="button" id="submitM" value="등록"/>
                </div>
            </td>
        </tr>
    </table>

    <div class="c_info_Memo">
        <c:forEach items="${memoList}" var="memo">
        <div class="c_memo_form">
            <div class="day">[${memo.c_memo_num}]<br />${memo.c_date}</div>
            <div class="memo">
                ${memo.c_memo}
            </div>
        </div>
        </c:forEach>
    </div>

    <div class="c_info_btn">
        <input class="text_btn" type="button" id="submit" value="수정" />
        <input type="button" value="목록" onclick="plannerList()" />
    </div>
</div>
</body>
</html>
