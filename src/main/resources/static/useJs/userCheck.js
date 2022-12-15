// 유효성 검사 true false 리스트
var checkList = [false, false, false, false, false, false, false];
var checkVal = [$('#userId').val(), $("#userPw").val(), $("#userPw2").val(), $("#userName").val(), $("#userPh").val(), $("#userMail").val(), $(".userGen").val()]
var checkSpan = [$('.checkId'), $('.checkPw'), $('.checkPw2'), $('.checkName'), $('.checkPh'), $('.checkMail'), $('.checkGen')];

// 입력 방지 펑션
// 아이디
$(function () {
    $("#userId").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[ \{\}\[\]\/?.,;:|\)*~`!^\-+┼<>@\#$%&\'\"\\\(\=]/g, ''));
    });
});
// 전화번호
$(function () {
    $("#userPh").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, ""));
    });
});


// 체크 펑션
// 아이디 체크
function checkId() {
    var id = $('#userId').val();
    $.ajax({
        url: './idCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {userId: id},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (id == '') { // 빈값일때
                console.log(checkList[0]);
                checkList[0] = false;
                checkSpan[0].css("display", "inline-block");
                $('.id_already').css("display", "none");
                $('.id_ok').css("display", "none");
                $('.id_not').css("display", "none");
                $('#userId').focus();
            } else if (id.length < 5 || id.length > 20) { // 아이디 길이 5 ~ 20
                checkList[0] = false;
                checkSpan[0].css("display", "none");
                $('.id_already').css("display", "none");
                $('.id_ok').css("display", "none");
                $('.id_not').css("display", "inline-block");
                $('#userId').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 아이디
                checkList[0] = false;
                checkSpan[0].css("display", "none");
                $('.id_already').css("display", "inline-block");
                $('.id_ok').css("display", "none");
                $('.id_not').css("display", "none");
                $('#userId').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                checkList[0] = true;
                checkSpan[0].css("display", "none");
                $('.id_already').css("display", "none");
                $('.id_ok').css("display", "inline-block");
                $('.id_not').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

// 비밀번호 체크
function checkPw() {

    var pw = $("#userPw").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if (pw.length < 8 || pw.length > 16 || (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) { // 8 ~ 16자리이고, 영문, 숫자, 특수문자 조합
        checkList[1] = false;
        $('.pwd_not').css("display", "inline-block");
        $('.pwd_ok').css("display", "none");
        $('.pwd_space').css("display", "none");
        $('#userPw').focus();
    } else if (pw.search(/\s/) != -1) { // 공백이 있을때
        checkList[1] = false;
        $('.pwd_space').css("display", "inline-block");
        $('.pwd_ok').css("display", "none");
        $('.pwd_not').css("display", "none");
        $('#userPw').focus();
    } else { // 사용 가능한 비밀번호
        checkList[1] = true;
        $('.pwd_ok').css("display", "inline-block");
        $('.pwd_space').css("display", "none");
        $('.pwd_not').css("display", "none");
    }
}

// 비밀번호의 두 값 체크
function checkDoublePw() {
    var pw1 = $('#userPw').val();
    var pw2 = $('#userPw2').val();


    if (pw2 != '') { // pw2가 비었을때는 실행을 막기 위해 사용(pw1이 바꼈을때 바로 반영하기 위함.)
        if (pw1 == pw2) { // 비밀번호가 같은 경우
            checkList[2] = true;
            $('.pwd2_ok').css("display", "inline-block");
            $('.pwd2_not').css("display", "none");

        } else { // 비밀번호가 다를 경우
            checkList[2] = false;
            $('.pwd2_not').css("display", "inline-block");
            $('.pwd2_ok').css("display", "none");
            $('#userPw2').focus();
        }
    }
}

// 이름 체크
function checkName() { // 간단한 체크
    var name = $('#userName').val();
    if (name == '') {
        checkList[3] = false;
        $('.name_not').css("display", "inline-block");
    } else {
        checkList[3] = true;
        $('.name_not').css("display", "none");
    }
}

// 전화번호 체크
function checkPh() {
    var ph = $('#userPh').val(); // ph 저장
    var regExp = /^(010)[0-9]{3,4}[0-9]{4}$/;
    $.ajax({
        url: './phCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {userPh: ph},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (!regExp.test(ph)) { // 유효성 체크
                checkList[4] = false;
                $('.ph_not').css("display", "inline-block");
                $('.ph_already').css("display", "none");
                $('.ph_ok').css("display", "none");
                $('#userPh').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 전화번호
                checkList[4] = false;
                $('.ph_already').css("display", "inline-block");
                $('.ph_ok').css("display", "none");
                $('.ph_not').css("display", "none");
                $('#userPh').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 전화번호
                checkList[4] = true;
                $('.ph_ok').css("display", "inline-block");
                $('.ph_already').css("display", "none");
                $('.ph_not').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

// 이메일 체크
function checkMail() {
    var mail = $('#userMail').val();
    var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    $.ajax({
        url: './mailCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {userMail: mail},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (regExp.test(mail) == false) { // 유효성 체크
                checkList[5] = false;
                $('.mail_not').css("display", "inline-block");
                $('.mail_already').css("display", "none");
                $('.mail_ok').css("display", "none");
                $('#userMail').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 이메일
                checkList[5] = false;
                $('.mail_already').css("display", "inline-block");
                $('.mail_ok').css("display", "none");
                $('.mail_not').css("display", "none");
                $('#userMail').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
                checkList[5] = true;
                $('.mail_ok').css("display", "inline-block");
                $('.mail_already').css("display", "none");
                $('.mail_not').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

// 성별 체크
function checkGender() { // 간단한 체크
    var gen = $('.userGen').val();
    if (gen == '') {
        checkList[6] = false;
        $('.gen_not').css("display", "inline-block");
    } else {
        checkList[6] = true;
        $('.gen_not').css("display", "none");
    }
}

// 모두 유효성 검사를 통과했을때 로그인 허용 해주기 위함
function checkAll() {

}