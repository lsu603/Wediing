function inputCheck() {
    if (document.frm.id.value == "") {
        alert("아이디를 입력해주세요")
        document.frm.id.focus();
        return;
    }
    if (document.frm.pwd.value == "") {
        alert("비밀번호를 입력해주세요");
        document.frm.pwd.focus();
        return;
    }
    if (document.frm.repwd.value == "") {
        alert("비밀번호를 확인해주세요");
        document.frm.repwd.focus();
        return;
    }
    if (document.frm.pwd.value !=document.regFrm.repwd.value) {
        alert("비밀번호가 일치하지 않습니다");
        document.frm.repwd.value ="";
        document.frm.repwd.focus();
        return;
    }
    if (document.frm.name.value == "") {
        alert("이름를 입력해주세요");
        document.frm.name.focus();
        return;
    }

    document.frm.submit();
}

function checkNum(e) {
    var keyVal = event.keyCode;

    if(((keyVal >= 48) && (keyVal <= 57))){
        return true;
    }
    else{
        alert("숫자만 입력가능합니다");
        return false;
    }
}
