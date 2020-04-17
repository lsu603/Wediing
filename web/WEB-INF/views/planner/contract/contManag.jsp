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
    function Addplanner(){
        window.open('${contextPath}/planner/contract/searchCouple.do','coupleName',"width=400, height=300, left=100, top=50")
    }

    function fn_plannerSch(){
        const _name = document.getElementById('p_name').value;

        console.log(p_name);
        location.href="${contextPath}/planner/contract/searchPlannerCon.do?name="+_name;
    }

    function fn_contSch(){
        const _name = document.getElementById('name').value;

        console.log(_name);
        location.href="${contextPath}/planner/contract/searchCont.do?name="+_name;
    }
</script>
<body>
<div id="Cont_Manag">
    <div class="cont_input">
        <input class="cont_add" type="button" value="계약추가" onclick="Addplanner()"/>
        <div class="cont_p_search">
            <select name="" id="p_name">
                <c:forEach items="${plannerList}" var="planner">
                    <c:choose>
                        <c:when test="${planner == user}">
                            <option name="p_name" value="${planner}" selected="true">${planner}</option>
                        </c:when>
                        <c:otherwise>
                            <option name="p_name" value="${planner}">${planner}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="button" value="검색" onclick="fn_plannerSch()"/>
        </div>
        <div class="cont_input_search">
            <input type="text" placeholder="이름 검색" id="name" />
            <input type="button" value="검색" onclick="fn_contSch()"
        />
        </div>
    </div>
    <table class="cont_tbl">
        <tr>
            <td class="tbl_1">플래너</td>
            <td class="tbl_1">예식일</td>
            <td class="tbl_1">신랑<br />신부</td>
            <td class="tbl_2">홀</td>
            <td class="tbl_2">스드메</td>
            <td class="tbl_2">기타</td>
        </tr>
        <c:forEach items="${listCont}" var="cont">
            <tr>
                <td class="tbl_1" rowspan="2">${cont.c_p_name}</td>
                <td class="tbl_1" rowspan="2">
                        ${cont.c_day.substring(0,4)}-${cont.c_day.substring(4,6)}-${cont.c_day.substring(6,8)}
                </td>
                <td class="tbl_1">${cont.c_man}</td>
                <c:choose>
                    <c:when test="${cont.c_hall=='가계약'}"><td class="tbl_2 prov" rowspan="2"><a href="${contextPath}/planner/contract/conHall.do?c_mem_num=${cont.c_mem_num}">가계약</a></td></c:when>
                    <c:when test="${cont.c_hall=='정계약'}"><td class="tbl_2 reg" rowspan="2"><a href="${contextPath}/planner/contract/conHall.do?c_mem_num=${cont.c_mem_num}">정계약</a></td></c:when>
                    <c:when test="${cont.c_hall=='취소'}"><td class="tbl_2 cancel" rowspan="2"><a href="${contextPath}/planner/contract/conHall.do?c_mem_num=${cont.c_mem_num}">취소</a></td></c:when>
                    <c:otherwise><td class="tbl_2" rowspan="2"><a href="${contextPath}/planner/contract/conHall.do?c_mem_num=${cont.c_mem_num}">추가</a></td></c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${cont.c_stu_shop=='가계약'}"><td class="tbl_2 prov" rowspan="2"><a href="${contextPath}/planner/contract/conShop.do?c_mem_num=${cont.c_mem_num}">가계약</a></td></c:when>
                    <c:when test="${cont.c_stu_shop=='정계약'}"><td class="tbl_2 reg" rowspan="2"><a href="${contextPath}/planner/contract/conShop.do?c_mem_num=${cont.c_mem_num}">정계약</a></td></c:when>
                    <c:when test="${cont.c_stu_shop=='취소'}"><td class="tbl_2 cancel" rowspan="2"><a href="${contextPath}/planner/contract/conShop.do?c_mem_num=${cont.c_mem_num}">취소</a></td></c:when>
                    <c:otherwise><td class="tbl_2" rowspan="2"><a href="${contextPath}/planner/contract/conShop.do?c_mem_num=${cont.c_mem_num}">추가</a></td></c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${cont.c_et=='가계약'}"><td class="tbl_2 prov" rowspan="2"><a href="${contextPath}/planner/contract/conEt.do?c_mem_num=${cont.c_mem_num}">가계약</a></td></c:when>
                    <c:when test="${cont.c_et=='정계약'}"><td class="tbl_2 reg" rowspan="2"><a href="${contextPath}/planner/contract/conEt.do?c_mem_num=${cont.c_mem_num}">정계약</a></td></c:when>
                    <c:when test="${cont.c_et=='취소'}"><td class="tbl_2 cancel" rowspan="2"><a href="${contextPath}/planner/contract/conEt.do?c_mem_num=${cont.c_mem_num}">취소</a></td></c:when>
                    <c:otherwise><td class="tbl_2" rowspan="2"><a href="${contextPath}/planner/contract/conEt.do?c_mem_num=${cont.c_mem_num}">추가</a></td></c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td class="tbl_1">${cont.c_woman}</td>
            </tr>

        </c:forEach>

    </table>

    <div class="pagingNum">
        <ul>
            <c:if test="${paging.startPage != 1 }">
                <li>
                    <a href="${contextPath}/planner/contract/listCont.do?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">이전</a>
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
                            <a href="/planner/contract/listCont.do?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${paging.endPage != paging.lastPage}">
                <li>
                    <a href="/planner/contract/listCont.do?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">다음</a>
                </li>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
