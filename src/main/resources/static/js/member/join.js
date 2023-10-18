$(document).ready(function () {
    var now_utc = Date.now()
    var timeOff = new Date().getTimezoneOffset() * 60000;
    var today = new Date(now_utc - timeOff).toISOString().split("T")[0];
    document.getElementById("birth").setAttribute("max", today);
    function validatePassword(password) {
        // 최소 8자리 이상, 알파벳, 특수문자, 숫자가 각각 하나 이상 포함되어야 함
        var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$/;
        return regex.test(password);
    }
    $('#adminJoinForm').submit(function (event) {
        $('#adminJoinForm').off('submit');
        var email = $('#mail').val();
        var password = $('#password').val();
        var confirmPassword = $('#checkPw').val();
        var phoneInput = $('#phone').val();
        var postCode = $('#postcode').val();
        var detailAddress = $('#detailAddress').val();
        var regExpEm = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;


        $.ajax({
            url: "/join/emailCheck",
            type: 'post',
            data: { "email": email },
            async: false, // 동기적으로 호출
            success: function (cnt) {
                // 이메일 중복 체크 로직
                checkEmail();
            },
            error: function () {
                alert("에러입니다");
            }
        });


        checkPhone();

        if (!regExpEm.test(email)) {
            alert("올바른 이메일이 아닙니다.");
            $('#mail').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (!validatePassword(password)) {
            alert("비밀번호는 알파벳, 특수문자, 숫자가 최소 하나씩 포함되어야하고 8자리 이상이어야합니다");
            // 폼 전송 중단
            $('#password').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (password !== confirmPassword) {
            alert("비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
            $('#password').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (!/^[0-9]{10,11}$/.test(phoneInput)) {
            alert("휴대전화번호는 10~11자리의 숫자만 입력 가능합니다.");
            $('#phone').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (postCode === "") {
            alert("우편번호를 입력해주세요.");
            $('#postcode').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (detailAddress === "") {
            alert("상세주소를 입력해주세요.");
            $('#detailAddress').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (nickName == "") {
            alert("닉네임을 입력해주세요.");
            $('#nickName').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        } else if (gender == "") {
            alert("성별을 입력해주세요.")
            $('#male').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        }
    });
});
function checkEmail() {
var email = $('#mail').val();
        $.ajax({
            url: "/join/emailCheck",
            type: 'post',
            data: { "email": email },
            success: function (cnt) {
                if (cnt != 0) {
                    alert("사용중인 이메일입니다.");
                    $('#mail').focus();
                    event.preventDefault();
                    return false;
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });
    }
    function checkPhone() {
    var phoneInput = $('#phone').val();
            $.ajax({
                url: "/join/phoneCheck",
                type: 'post',
                data: { "phone": phoneInput },
                success: function (cnt) {
                    if (cnt != 0) {
                        alert("사용중인 전화번호입니다.");
                        $('#phone').focus();
                        event.preventDefault();
                        return false;
                    }
                },
                error: function () {
                    alert("에러입니다");
                }
            });
        }
