$(document).ready(function () {
    var now_utc = Date.now()
    var timeOff = new Date().getTimezoneOffset()*60000;
    var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
    document.getElementById("birth").setAttribute("max", today);
    function validatePassword(password) {
        // 최소 8자리 이상, 알파벳, 특수문자, 숫자가 각각 하나 이상 포함되어야 함
        var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$/;
        return regex.test(password);
    }
    $('#adminJoinForm').submit(function (event) {

        var email = $('#mail').val();
        var password = $('#password').val();
        var confirmPassword = $('#checkPw').val();
        var phoneInput = $('#phone').val();
        var postCode = $('#postcode').val();
        var detailAddress = $('#detailAddress').val();
        var regExpEm = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        var nickName = $('#nickName').val();

        if (!regExpEm.test(email)) {
            alert("올바른 이메일이 아닙니다.");
            $('#mail').focus();
            // 폼 전송 중단
            return false;
        }
        $.ajax({
            url: "/join/emailCheck", //Controller에서 요청 받을 주소
            type: 'post', //POST 방식으로 전달
            data: { "email": email },
            success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다
                if (cnt != 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                    alert("사용중인 이메일입니다.");
                    $('#mail').focus();
                    // 폼 전송 중단
                    event.preventDefault();
                    return false;
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });
        if (!validatePassword(password)) {
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
        }else if (nickName == ""){
            alert("닉네임을 입력해주세요.");
            $('#nickName').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        }
        $.ajax({
                type: "post",
                url: "/member/emailCheck",
                data: {email: clientEmail, inputCode: inputCode},
                success: function (result) {
                    console.log(result);
                    if (result != true) {
                        alert('인증번호가 틀립니다.');
                        $('#mail').focus();
                        // 폼 전송 중단
                        event.preventDefault();
                        return false;
                        }

                    }
                });

        $('#adminJoinForm').submit();
    });
});

