
function email_check() {
    const email = $('#mail').val(); //id값이 "id"인 입력란의 값을 저장
    const regExpEm = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    let memberEmail;
    let freeEmail;
    if (!regExpEm.test(email)) {
        $('.email_expression').css("display", "inline-block");
    } else {
        $('.email_expression').css("display", "none");
        $.ajax({
            url: "/join/emailCheck", //Controller에서 요청 받을 주소
            type: 'post', //POST 방식으로 전달
            data: { "email": email },
            success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
                memberEmail = cnt;
                if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                    $('.member_ok').css("display", "inline-block");
                    $('.member_already').css("display", "none");
                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                    $('.member_already').css("display", "inline-block");
                    $('.member_ok').css("display", "none");
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });
        $.ajax({
            url: "/free/emailCheck", //Controller에서 요청 받을 주소
            type: 'post', //POST 방식으로 전달
            data: { "email": email },
            success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
                freeEmail = cnt;
                if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                    $('.member_ok').css("display", "inline-block");
                    $('.member_already').css("display", "none");
                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                    $('.member_already').css("display", "inline-block");
                    $('.member_ok').css("display", "none");
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });
        // 이메일 둘중 하나라도 1일경우 true -> 사용중인 이메일표시
        if (memberEmail || freeEmail) {
            $('.member_already').css("display", "inline-block");
            $('.member_ok').css("display", "none");
        } else {
            $('.member_ok').css("display", "inline-block");
            $('.member_already').css("display", "none");
        }
    }
}
function nickNameCheck() {
    const nickname = $('#nickName').val(); //id값이 "id"인 입력란의 값을 저장
    $.ajax({
        url: "/join/nickCheck", //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: { "nickName": nickname },
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                $('.nick_ok').css("display", "inline-block");
                $('.nick_already').css("display", "none");
            } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                $('.nick_already').css("display", "inline-block");
                $('.nick_ok').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
}
function phoneCheck() {
    const phone = $('#phone').val();
    let memberPhone;
    let freePhone;
    $.ajax({
        url: "/join/phoneCheck",
        type: 'post',
        data: { "phone": phone },
        success: function (cnt) {
            memberPhone = cnt;
            if (cnt == 0) {
                $('.phone_ok').css("display", "inline-block");
                $('.phone_already').css("display", "none");
            } else {
                $('.phone_already').css("display", "inline-block");
                $('.phone_ok').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
    $.ajax({
        url: "/free/phoneCheck",
        type: 'post',
        data: { "phone": phone },
        success: function (cnt) {
            freePhone = cnt;
            if (cnt == 0) {
                $('.phone_ok').css("display", "inline-block");
                $('.phone_already').css("display", "none");
            } else {
                $('.phone_already').css("display", "inline-block");
                $('.phone_ok').css("display", "none");
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
    if (memberPhone || freePhone) {
        $('.phone_already').css("display", "inline-block");
        $('.phone_ok').css("display", "none");
    } else {
        $('.phone_ok').css("display", "inline-block");
        $('.phone_already').css("display", "none");
    }

}
function checkPws() {
    const pw = $('#password').val();
    const email = $('#email').val();
    const pw_check = $('#checkPw').val();
    var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    if (!passwordRules.test(pw)) {
        $('.pw_string').css("display", "inline-block");
    } else {
        $('.pw_string').css("display", "none");
    }
    const checkNumber = pw.search(/[0-9]/g);
    const checkEnglish = pw.search(/[a-z]/ig);

    if (checkNumber < 0 || checkEnglish < 0) {
        $('.pw_length').css("display", "inline-block");
    } else {
        $('.pw_length').css("display", "none");
    }
    if (/(\w)\1\1/.test(pw)) {
        $('.pw_true').css("display", "inline-block");
    } else {
        $('.pw_true').css("display", "none");
    }
    if (pw.search(email) > 0) {
        $('.pw_email').css("display", "inline-block");

    } else {
        $('.pw_email').css("display", "none");
    }
    if (pw_check != "") {
        pw_check();
    }

}

function pw_check() {
    const pw = $('#password').val();
    const pw_check = $('#checkPw').val();

    if (pw != pw_check) {
        $('.password_already').css("display", "inline-block");
    } else {
        $('.password_already').css("display", "none");
    }

}
function check_pw() {
    pw_check();
}

