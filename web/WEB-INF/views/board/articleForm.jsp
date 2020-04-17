
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>Title</title>
    <script>
        function contList(){
            location.href = "${contextPath}/board/listArticles.do";
        }

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#preview').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        var cnt=1;
        function fn_addFile(){
            $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
            cnt++;
        }
    </script>
</head>
<body>
<div class="add_Article">
    <h1>글 작성</h1>
    <form name="articleForm" method="post"   action="${contextPath}/board/addNewArticle.do"   enctype="multipart/form-data">
    <table class="add_article_tbl">
        <tr>
            <td class="t1">제목</td>
            <td class="t2 title" colspan="2"><input type="text" name="title" /></td>
        </tr>
        <tr>
            <td colspan="3" class="content">
                <textarea name="content" >  </textarea>
            </td>
        </tr>
        <tr>
            <td class="t1">이미지첨부</td>
            <td class="t2">
                <input type="file" name="imageFileName" onchange="readURL(this);" />
            </td>
            <td><input type="button" class="addFile" value="파일 추가" onClick="fn_addFile()"/></td>
        </tr>
        <tr>
            <td class="t1"></td>
        <td class="t2" colspan="3"><div id="d_file"></div></td>
        </tr>

    </table>
    <div>
        <input type="submit" value="완료" />
        <input type="button" value="목록" onclick="contList()"/>
    </div>
    </form>
</div>
</body>
</html>
