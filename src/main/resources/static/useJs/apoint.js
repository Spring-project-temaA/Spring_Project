// 모든 값 확인 위함
var checkList = [false, false, false, false, false, false];

// Date 체크
function checkDate() {
    var date = $('#dp1').val();

    if (date == '') {
        checkList[0] = false;
        $('.date').css('display', 'inline-block');
    } else {
        checkList[0] = true;
        $('.date').css('display', 'none');
    }
}

// Time 체크
function checkTime() {
    var time = $('#dp2').val();

    if (time == '') {
        checkList[1] = false;
        $('.time').css('display', 'inline-block');
    } else {
        checkList[1] = true;
        $('.time').css('display', 'none');
    }
}

// Cut 체크
function checkCut() {
    var cut = $('#apointCut').val();

    if (cut == ' ') {
        checkList[2] = false;
        $('.cut').css('display', 'inline-block');
    } else {
        checkList[2] = true;
        $('.cut').css('display', 'none');
    }
}

// Perm 체크
function checkPerm() {
    var perm = $('#apointPerm').val();

    if (perm == ' ') {
        checkList[3] = false;
        $('.perm').css('display', 'inline-block');
    } else {
        checkList[3] = true;
        $('.perm').css('display', 'none');
    }
}

// Color 체크
function checkColor() {
    var color = $('#apointColor').val();

    if (color == ' ') {
        checkList[4] = false;
        $('.color').css('display', 'inline-block');
    } else {
        checkList[4] = true;
        $('.color').css('display', 'none');
    }
}

// Clinic 체크
function checkClinic() {
    var clinic = $('#apointClinic').val();

    if (clinic == ' ') {
        checkList[5] = false;
        $('.clinic').css('display', 'inline-block');
    } else {
        checkList[5] = true;
        $('.clinic').css('display', 'none');
    }
}

// 모든 값이 체크 되면 실행
function checkAll() {
    // 회원가입 버튼
    let signUp = $('#btn-apoint');
    // 값이 비었는지 확인하기 위한 리스트
    let checkVal = [$('#dp1').val(), $("#dp2").val(), $("#apointCut").val(), $("#apointPerm").val(), $("#apointColor").val(), $("#apointClinic").val()];
    // 값이 없을때를 알리기 위한 리스트
    let checkSpan = [$('.date'), $('.time'), $('.cut'), $('.perm'), $('.color'), $('.clinic')];

    let true_cnt = 0;

    for (let i = 0; i < checkList.length; i++) {
        if (i >= 2) {
            if (checkList[i] == false && checkVal[i] == ' ') {
                checkSpan[i].css('display', 'inline-block')
            } else if (checkList[i] == true) {
                true_cnt++;
            }
        } else if (checkList[i] == false && checkVal[i] == '') {
            checkSpan[i].css('display', 'inline-block')
        } else if (checkList[i] == true) {
            true_cnt++
        }
    }
    console.log(true_cnt);

    if (true_cnt == 6) {
        $('#dp2').disable = false;
        $('#apointTime').val($('#dp2').val());
        signUp.attr("type", "submit");
        alert("가입을 환영합니다.");
        signUp.click(true);
    }
}