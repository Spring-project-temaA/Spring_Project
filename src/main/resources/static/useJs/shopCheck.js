// 유효성 검사 true false 리스트
var checkList = [false, false, false, false, false, false, false, false, false, false];
var adrrCnt = 0;

// 입력 방지 펑션
// 아이디
$(function () {
    $("#shopId").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|[ \{\}\[\]\/?.,;:|\)*~`!^\-+┼<>@\#$%&\'\"\\\(\=]/g, ''));
    });
});

// Shop Tel
$(function () {
    $("#shopTel").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, ""));
    });
});

// Owner Ph
$(function () {
    $("#ownerPh").on("blur keyup", function () {
        $(this).val($(this).val().replace(/[^0-9]/g, ""));
    });
});


// 체크 펑션
// 아이디 체크
function checkId() {
    var id = $('#shopId').val();
    $.ajax({
        url: './shopIdCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {shopId: id},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (id == '') { // 빈값일때
                checkList[0] = false;
                $('.checkId').css("display", "inline-block");
                $('.id_already').css("display", "none");
                $('.id_ok').css("display", "none");
                $('.id_not').css("display", "none");
                $('#shopId').focus();
            } else if (id.length < 5 || id.length > 20) { // 아이디 길이 5 ~ 20
                checkList[0] = false;
                $('.checkId').css("display", "none");
                $('.id_already').css("display", "none");
                $('.id_ok').css("display", "none");
                $('.id_not').css("display", "inline-block");
                $('#shopId').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 아이디
                checkList[0] = false;
                $('.checkId').css("display", "none");
                $('.id_already').css("display", "inline-block");
                $('.id_ok').css("display", "none");
                $('.id_not').css("display", "none");
                $('#shopId').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                checkList[0] = true;
                $('.checkId').css("display", "none");
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

    var pw = $("#shopPw").val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    if (pw == '') { // 빈값일때
        checkList[1] = false;
        $('.checkPw').css("display", "inline-block");
        $('.pwd_not').css("display", "none");
        $('.pwd_ok').css("display", "none");
        $('.pwd_space').css("display", "none");
        $('#shopPw').focus();
    } else if (pw.length < 8 || pw.length > 16 || (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) { // 8 ~ 16자리이고, 영문, 숫자, 특수문자 조합
        checkList[1] = false;
        $('.checkPw').css("display", "none");
        $('.pwd_not').css("display", "inline-block");
        $('.pwd_ok').css("display", "none");
        $('.pwd_space').css("display", "none");
        $('#shopPw').focus();
    } else if (pw.search(/\s/) != -1) { // 공백이 있을때
        checkList[1] = false;
        $('.checkPw').css("display", "none");
        $('.pwd_space').css("display", "inline-block");
        $('.pwd_ok').css("display", "none");
        $('.pwd_not').css("display", "none");
        $('#shopPw').focus();
    } else { // 사용 가능한 비밀번호
        checkList[1] = true;
        $('.checkPw').css("display", "none");
        $('.pwd_ok').css("display", "inline-block");
        $('.pwd_space').css("display", "none");
        $('.pwd_not').css("display", "none");
    }
}

// 비밀번호의 두 값 체크
function checkDoublePw() {
    var pw1 = $('#shopPw').val();
    var pw2 = $('#shopPw2').val();


    if (pw2 != '') { // pw2가 비었을때는 실행을 막기 위해 사용(pw1이 바꼈을때 바로 반영하기 위함.)
        if (pw1 == pw2) { // 비밀번호가 같은 경우
            checkList[2] = true;
            $('.checkPw2').css("display", "none");
            $('.pwd2_ok').css("display", "inline-block");
            $('.pwd2_not').css("display", "none");

        } else { // 비밀번호가 다를 경우
            checkList[2] = false;
            $('.checkPw2').css("display", "none");
            $('.pwd2_not').css("display", "inline-block");
            $('.pwd2_ok').css("display", "none");
            $('#shopPw2').focus();
        }
    }
}

// 비밀번호 확인이 비어있을때
function checkPw2() {
    var pw2 = $('#shopPw2').val();

    if (pw2 == '') { // 값이 비어있을 때
        checkList[2] = false;
        $('.checkPw2').css("display", "inline-block");
        $('.pwd2_ok').css("display", "none");
        $('.pwd2_not').css("display", "none");
    }
}

// 상호명 체크
function checkShopName() { // 간단한 체크
    var name = $('#shopName').val();
    if (name == '') {
        checkList[3] = false;
        $('.checkShopName').css("display", "inline-block");
    } else {
        checkList[3] = true;
        $('.checkShopName').css("display", "none");
    }
}

// 주소
function addressGet() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            automapauautoMappingJibun: false;

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addrRoad = ''; // 도로명 변수
            var addrJibun = ''; // 지번 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R' || data.userSelectedType == 'J') { // 사용자가 도로명 주소나 지번주소 선택시
                addrRoad = data.roadAddress;
                addrJibun = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R' || data.userSelectedType === 'J') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }

                // 값이 여러개일때
                if (data.autoRoadAddress) {
                    var addrRoad = data.autoRoadAddress + extraRoadAddr;
                } else if (data.autoJibunAddress) {
                    var addrJibun = data.autoJibunAddress;
                }


                addrJibun += extraAddr;
                addrRoad += extraAddr;
                // 주소 변수 문자열 + 참고항목 문자열을 합친다.


                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('shopAddrNum').value = data.zonecode;
                document.getElementById("shopAddrJibun").value = addrJibun;
                document.getElementById("shopAddrRoad").value = addrRoad;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("shopAddrDetail").focus();
                // 값 있나 없나 체크
                checkAddress();

            }
        }
    }).open();
}

// 주소 체크
function checkAddress() { // 간단한 체크
    var addr = $('#shopAddrNum').val();

    if (addr == '') {
        checkList[9] = false;
        $('.checkAddr').css("display", "inline-block");
    } else {
        checkList[9] = true;
        $('.checkAddr').css("display", "none");
    }
}

// Shop Tel 체크
function checkShopTel() {
    var tel = $('#shopTel').val(); // ph 저장
    var regExp = /^(02|0[3-6]{1}[1-5]{1})-?([0-9]{3,4})-?[0-9]{4}$/;

    $.ajax({
        url: './shopTelCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {shopTel: tel},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (tel == '') { // 값이 비어있을때
                checkList[4] = false;
                $('.checkTel').css("display", "inline-block");
                $('.tel_not').css("display", "none");
                $('.tel_already').css("display", "none");
                $('.tel_ok').css("display", "none");
                $('#shopTel').focus();
            } else if (!regExp.test(tel)) { // 유효성 체크
                checkList[4] = false;
                $('.checkTel').css("display", "none");
                $('.tel_not').css("display", "inline-block");
                $('.tel_already').css("display", "none");
                $('.tel_ok').css("display", "none");
                $('#shopTel').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 전화번호
                checkList[4] = false;
                $('.checkTel').css("display", "none");
                $('.tel_not').css("display", "none");
                $('.tel_already').css("display", "inline-block");
                $('.tel_ok').css("display", "none");
                $('#shopTel').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 전화번호
                checkList[4] = true;
                $('.checkTel').css("display", "none");
                $('.tel_not').css("display", "none");
                $('.tel_already').css("display", "none");
                $('.tel_ok').css("display", "inline-block");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

// Owner 이름 체크
function checkName() { // 간단한 체크
    var name = $('#ownerName').val();
    if (name == '') {
        checkList[5] = false;
        $('.checkName').css("display", "inline-block");
    } else {
        checkList[5] = true;
        $('.checkName').css("display", "none");
    }
}

// 전화번호 체크
function checkPh() {
    var ph = $('#ownerPh').val(); // ph 저장
    var regExp = /^(010)[0-9]{3,4}[0-9]{4}$/;
    $.ajax({
        url: './ownerPhCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {ownerPh: ph},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (ph == '') { // 값이 비어있을때
                checkList[6] = false;
                $('.checkPh').css("display", "inline-block");
                $('.ph_not').css("display", "none");
                $('.ph_already').css("display", "none");
                $('.ph_ok').css("display", "none");
                $('#ownerPh').focus();
            } else if (!regExp.test(ph)) { // 유효성 체크
                checkList[6] = false;
                $('.checkPh').css("display", "none");
                $('.ph_not').css("display", "inline-block");
                $('.ph_already').css("display", "none");
                $('.ph_ok').css("display", "none");
                $('#ownerPh').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 전화번호
                checkList[6] = false;
                $('.checkPh').css("display", "none");
                $('.ph_already').css("display", "inline-block");
                $('.ph_ok').css("display", "none");
                $('.ph_not').css("display", "none");
                $('#ownerPh').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 전화번호
                checkList[6] = true;
                $('.checkPh').css("display", "none");
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
    var mail = $('#ownerMail').val();
    var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    $.ajax({
        url: './ownerMailCheck', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: {ownerMail: mail},
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (mail == '') { // 값이 비어있을때
                checkList[7] = false;
                $('.checkMail').css("display", "inline-block");
                $('.mail_not').css("display", "none");
                $('.mail_already').css("display", "none");
                $('.mail_ok').css("display", "none");
                $('#ownerMail').focus();
            } else if (regExp.test(mail) == false) { // 유효성 체크
                checkList[7] = false;
                $('.checkMail').css("display", "none");
                $('.mail_not').css("display", "inline-block");
                $('.mail_already').css("display", "none");
                $('.mail_ok').css("display", "none");
                $('#ownerMail').focus();
            } else if (cnt != 0) { // cnt가 1일 경우 -> 이미 존재하는 이메일
                checkList[7] = false;
                $('.checkMail').css("display", "none");
                $('.mail_already').css("display", "inline-block");
                $('.mail_ok').css("display", "none");
                $('.mail_not').css("display", "none");
                $('#ownerMail').focus();
            } else if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
                checkList[7] = true;
                $('.checkMail').css("display", "none");
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
    var gen = $('.ownerGen').val();

    if (gen == '') {
        $('.checkGen').css('display', 'inline-block');
        checkList[8] = false;
    } else {
        $('.checkGen').css('display', 'none');
        checkList[8] = true;
    }
}

// 모두 유효성 검사를 통과했을때 로그인 허용 해주기 위함
function checkAll() {
    // 회원가입 버튼
    let signUp = $('#btn-signUp');
    // 값이 비었는지 확인하기 위한 리스트
    let checkVal = [$('#shopId').val(), $("#shopPw").val(), $("#shopPw2").val(), $('#shopName').val(), $('#shopTel').val(), $("#ownerName").val(), $("#ownerPh").val(), $("#ownerMail").val(), $(".ownerGen").val(), $('#shopAddrNum').val()]

    // 값이 없을때를 알리기 위한 리스트
    let checkSpan = [$('.checkId'), $('.checkPw'), $('.checkPw2'), $('.checkShopName'), $('.checkTel'), $('.checkName'), $('.checkPh'), $('.checkMail'), $('.checkGen'), $('.checkAddr')];

    let true_cnt = 0;

    for (let i = 0; i < checkList.length; i++) {
        if (checkList[i] == false && checkVal[i] == '') {
            checkSpan[i].css('display', 'inline-block')
        } else if (checkList[i] == true) {
            true_cnt++
        }
    }
    // userGen 체크 여부 확인
    if ($('.ownerGen').is(":checked") == false) {
        checkSpan[8].css('display', 'inline-block');
    }

    if (true_cnt == 10) {
        signUp.attr("type", "submit");
        alert("가입을 환영합니다.");
        signUp.click(true);
    }
}