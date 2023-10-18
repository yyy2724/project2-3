$(document).ready(function () {

    function validatePassword(password) {
        // 최소 8자리 이상, 알파벳, 특수문자, 숫자가 각각 하나 이상 포함되어야 함
        var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$/;
        return regex.test(password);
    }
    $('#comJoin').submit(function (event) {

        var email = $('#mail').val();
        var password = $('#password').val();
        var confirmPassword = $('#checkPassword').val();
        var phoneInput = $('#phone').val();
        var postCode = $('#postcode').val();
        var detailAddress = $('#detailAddress').val();
        var regExpEm = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        var gender = $('.gender').val();
        var company = $('#companyName').val();
        var businessNumber = $('#businessNumber').val();
        var businessNumberEx = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
        var validatePone = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
        var email_check = document.getElementById('certificationYN').value;

        let inputCode = document.getElementById('certificationNumber').value;
        if (!regExpEm.test(email)) {
            alert("올바른 이메일이 아닙니다.");
            $('#mail').focus();
            // 폼 전송 중단
            return false;
        }
        const memberEmail = 0;
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

        $.ajax({
            url: "/join/phoneCheck",
            type: 'post',
            data: { "phone": phone },
            success: function (cnt) {
                if (cnt != 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                    alert("사용중인 전화번호입니다.");
                    $('#phone').focus();
                    // 폼 전송 중단
                    event.preventDefault();
                    return false;
                }
            },
            error: function () {
                alert("에러입니다");
            }
        });

        $.ajax({
            url: "/join/companyCheck",
            type: 'post',
            data: { "companyName": company },
            success: function (cnt) {
                if (cnt != 0) {
                    alert("사용중인 회사명입니다.");
                    $('#companyName').focus();
                    // 폼 전송 중단
                    event.preventDefault();
                    return false;
                }
            }
        });


        if (company === "") {
            alert("상호명을 입력해주세요.");
            $('#companyName').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false
        } else if (businessNumber === "") {
            alert("사업자번호를 입력해주세요.");
            $('#businessNumber').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false
        } else if (Number.isInteger(businessNumber) && !businessNumberEx.test(businessNumber)){
            alert("올바른 사업자번호를 입력해주세요.");
            $('#businessNumber').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false
        } else if (document.getElementById('certificationYN').value != true) {
            alert("이메일 인증을 완료해주세요.");
            $('#mail').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        }else if (!validatePassword(password)) {
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
        } else if (validatePone.test(phoneInput)) {
            alert("회사 대표 전화번호를 입력해주세요");
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
        } else if (gender == "") {
            alert("성별을 입력해주세요.")
            $('#male').focus();
            // 폼 전송 중단
            event.preventDefault();
            return false;
        }
        $('#comJoin').submit();
    });
});