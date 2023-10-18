
$(document).ready(function () {
    $('#submit-button').prop('disabled', true);
    var now_utc = Date.now()
    var timeOff = new Date().getTimezoneOffset() * 60000;
    var today = new Date(now_utc - timeOff).toISOString().split("T")[0];
    document.getElementById("birth").setAttribute("max", today);

    var emailValidated = false;
    var phoneValidated = false;
    var passwordValidated = false;

    var emailInput = $('#mail');
    var phoneInput = $('#phone');
    var passwordInput = $('#password');
    var passCheckInput = $('#checkPw');
    // 다른 필드에 대한 변수들 추가

    emailInput.on('input', function () {
        memberEmail();
    });

    // 다른 필드에 대한 oninput 이벤트 핸들러 추가
    phoneInput.on('input', function () {
            phoneCheck();
    });
    passwordInput.on('input', function () {
            phoneCheck();
    });
    passCheckInput.on('input', function () {
            phoneCheck();
    });


    // 이메일 유효성 검사
    function memberEmail() {
        var email = emailInput.val();
        const regExpEm = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        // 이메일 유효성 검사 로직을 추가
         if (!regExpEm.test(email)) {
                $('.email_expression').css("display", "inline-block");
         }else {
        $.ajax({
            url: "/join/emailCheck",
            type: 'post',
            data: { "email": email },
            success: function (cnt) {
                if (cnt != 0) {
                    $('.member_already').css("display", "inline-block");
                    $('.member_ok').css("display", "none");
                    emailValidated = false;
                    checkAllFields();
                } else {
                    $('.member_ok').css("display", "inline-block");
                    $('.member_already').css("display", "none");
                    emailValidated = true;
                    checkAllFields();
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });
        }
    }

    // 다른 필드에 대한 유효성 검사 함수 추가
    // 전화번호 유효성 검사 (이하 생략)
        function phoneCheck() {
        var phone = phoneInput.val();
        var validatePone = /^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;
        if (!validatePone.test(phone)) {
            $('.phone_expression').css("display", "inline-block");
            $('.phone_already').css("display", "none");
            $('.phone_ok').css("display", "none");
        }else {
            $('.phone_expression').css("display", "none");
            $.ajax({
                url: "/join/phoneCheck",
                type: 'post',
                data: { "phone": phone },
                success: function (cnt) {
                    if (cnt != 0) {
                        // 유효할 경우 emailValidated를 true로 설정, 그렇지 않으면 false로 설정
                        phoneValidated = false; // 예제에서는 간단히 true로 설정
                        checkAllFields();
                        $('.phone_already').css("display", "inline-block");
                        $('.phone_ok').css("display", "none");
                    } else {
                        // 유효할 경우 emailValidated를 true로 설정, 그렇지 않으면 false로 설정
                        phoneValidated = true; // 예제에서는 간단히 true로 설정
                        checkAllFields();
                        $('.phone_ok').css("display", "inline-block");
                        $('.phone_already').css("display", "none");
                    }

                },
                error: function () {
                    alert("에러입니다");
                }
            });
           }
        }
    function checkAllFields() {
        if (emailValidated && phoneValidated) {
            $('#submit-button').prop('disabled', false);
        } else {
            $('#submit-button').prop('disabled', true);
        }
    }
});