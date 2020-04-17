
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%--    <link href="${pageContext.request.contextPath}/resources/css/sytles.css?ver=2" rel="stylesheet"/>--%>

    <title>회원가입</title>
    <script>
        function fn_overlapped(){
            const _id=$("#id").val();
            const regExpId = /^[a-zA-Z0-9]$/;

            console.log(_id);

            if(_id==''){
                alert("ID를 입력하세요");
                return;
            }

            if (_id.length < 6 || _id.length > 14) {
                alert("ID는 6자 이상 14자 이하로 입력하세요")
            }

            if (regExpId.test(_id)) {
                alert("id는 숫자와 영문으로만 사용가능합니다");
                return false;
            }

            $.ajax({
                type:"post",
                async:false,
                url:"${contextPath}/member/overlapped.do",
                dataType:"text",
                data: {id:_id},
                success:function (data,textStatus){
                    if(data=='false'){
                        alert("사용할 수 있는 ID입니다.");
                    }else{
                        alert("사용할 수 없는 ID입니다.");
                    }
                },
                error:function(data,textStatus){
                    alert("에러가 발생했습니다.");ㅣ
                },
                complete:function(data,textStatus){
                    //alert("작업을완료 했습니다");
                }
            });  //end ajax
        }
    </script>
</head>
<body>


<form action="${contextPath}/member/addMember.do" method="post" name="frm">
<div class="loginForms">
    <h1 class="loginForms_h1">회원가입</h1>
    <div class="loginForms_loginForm">
        <div class="loginForm loginForm_id">
            <span class="loginForm_1">아이디</span>
            <div class="loginForm_2">
                <input type="text" name="b_id"  id="id"/>
                <input type="button" value="중복확인" id="btnOverlapped" onClick="fn_overlapped()"/>
            </div>
        </div>
        <div class="loginForm loginForm_pw">
            <span class="loginForm_1">비밀번호</span>
            <input class="loginForm_2" type="password" name="b_pwd" />
        </div>
        <div class="loginForm loginForm_pw_ch">
            <span class="loginForm_1">비밀번호 확인</span>
            <input class="loginForm_2" type="password"name="repwd"  />
        </div>
        <div class="loginForm loginForm_name">
            <span class="loginForm_1">이름</span>
            <input class="loginForm_2" type="text" name="b_name" />
        </div>
        <div class="loginForm loginForm_tel">
            <span class="loginForm_1">연락처</span>
            <input
                    class="loginForm_2"
                    type="text"
                    placeholder=" '-' 제외 후 입력"
                    name="b_tel"
                    onKeyPress="return checkNum(event)"

            />
        </div>
        <div class="loginForm loginForm_gender">
            <span class="loginForm_1">성별</span>
            <div class="loginForm_2">
                <input type="radio" name="b_gender" value="0" checked />남
                <input type="radio" name="b_gender" value="1" />여
            </div>
        </div>
        <div class="loginForm loginForm_con">
            <span class="loginForm_1">관심사</span>
            <div class="loginForm_2">
                <input type="checkbox" name="b_hall" value="1" />웨딩홀
                <input type="checkbox" name="b_shop" value="1" />스드메
                <input type="checkbox" name="b_et" value="1" />기타
            </div>
        </div>
    </div>
    <div class="loginForms_subform">
        <input class="loginForm_sub" type="submit" value="회원가입" onclick="inputCheck()"/>
        <input class="loginForm_res" type="reset" value="다시쓰기" />
    </div>
</div>
</form>
</body>
</html>
