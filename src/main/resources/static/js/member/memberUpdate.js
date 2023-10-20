$(document).ready(function () {
    // 초기에는 버튼 비활성화
    $('#submit-button').prop('disabled', true);

    var emailValidated = false;
    var phoneValidated = false;
    var addressValidated = false;
    var emailCheckValidated = false;
    var nameValidated = false;

    var emailInput = $('#mail');
    var phoneInput = $('#phone');
    var addressInput = $('#address');
    var detailAddressInput = $('#detailAddress');
    var emailCheck = $('#emailCheck');
    var certification = $('#certificationBtn');
    var emailCheckNum = $('#certificationNumber');
    var nameInput = $('#name');

    // 초기 값 저장
    var initialValues = {
        email: emailInput.val(),
        phone: phoneInput.val(),
        address: addressInput.val(),
        detailAddress: detailAddressInput.val(),
        name: nameInput.val()
    };

    emailInput.on('input', function () {
        if (emailInput.val() !== initialValues.email) {
            emailValidated = false;
            emailCheckValidated = false;
            emailCertification();
        }
        checkAllFields();
    });

    phoneInput.on('input', function () {
        if (phoneInput.val() !== initialValues.phone) {
            phoneValidated = false;
            checkAllFields();
        }
    });

    addressInput.on('input', function () {
        if (addressInput.val() !== initialValues.address) {
            addressValidated = false;
            checkAllFields();
        }
    });

    detailAddressInput.on('input', function () {
        if (detailAddressInput.val() !== initialValues.detailAddress) {
            addressValidated = false;
            checkAllFields();
        }
    });

    emailCheck.on('click', function () {
        emailCheckValidated = false;
        emailSend2();
    });

    certification.on('click', function () {
        emailCertification();
    });

    nameInput.on('input', function () {
        if (nameInput.val() !== initialValues.name) {
            nameValidated = false;
            checkAllFields();
        }
    });

    function memberEmail() {
        var email = emailInput.val();
        const regExpEm = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zAZ])*\.[a-zA-Z]{2,3}$/i;
        // 이메일 유효성 검사
        if (!regExpEm.test(email)) {
            $('.email_expression').css("display", "inline-block");
            $('.member_ok').css("display", "none");
            $('.member_already').css("display", "none");
            checkAllFields();
        } else {
            $('.email_expression').css("display", "none");
            $.ajax({
                url: "/join/emailCheck",
                type: 'post',
                data: { "email": email },
                success: function (cnt) {
                    if (cnt != 0) {
                        $('.member_already').css("display", "inline-block");
                        $('.member_ok').css("display", "none");
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

    function phoneCheck() {
        var phone = phoneInput.val();
        var validatePone = /^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;
        if (!validatePone.test(phone)) {
            $('.phone_expression').css("display", "inline-block");
            $('.phone_already').css("display", "none");
            $('.phone_ok').css("display", "none");
            checkAllFields();
        } else {
            $('.phone_expression').css("display", "none");
            $.ajax({
                url: "/join/phoneCheck",
                type: 'post',
                data: { "phone": phone },
                success: function (cnt) {
                    if (cnt != 0) {
                        $('.phone_already').css("display", "inline-block");
                        $('.phone_ok').css("display", "none");
                        checkAllFields();
                    } else {
                        phoneValidated = true;
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

    function addressCheck() {
        var address = addressInput.val();
        var detailAddress = detailAddressInput.val();

        $('.post_already').css("display", "none");
        if (address === "") {
            $('.address_already').css("display", "inline-block");
            addressValidated = false;
            checkAllFields();
        } else {
            $('.address_already').css("display", "none");
            if (detailAddress === "") {
                $('.detail_already').css("display", "inline-block");
                addressValidated = false;
                checkAllFields();
            } else {
                $('.detail_already').css("display", "none");
                addressValidated = true;
                checkAllFields();
            }
        }
    }

    function emailSend2() {
        const clientEmail = emailInput.val();

        $.ajax({
            type: "post",
            url: "/member/check",
            data: { email: clientEmail },
            success: function (data) {
                alert('인증번호를 보냈습니다.');
            }, error: function (e) {
                alert('오류입니다. 잠시 후 다시 시도해주세요.');
            }
        });
    }

    function emailCertification() {
        let clientEmail = emailInput.val();
        let inputCode = emailCheckNum.val();

        $.ajax({
            type: "post",
            url: "/member/emailCheck",
            data: { email: clientEmail, inputCode: inputCode },
            success: function (result) {
                console.log(result);
                if (result == true) {
                    alert("인증완료");
                    emailCheckValidated = true;
                    checkAllFields();
                } else {
                    alert('인증번호가 틀립니다.');
                    emailCheckValidated = false;
                    checkAllFields();
                }
            }
        });
    }

    function checkAllFields() {
        if (emailValidated && phoneValidated && addressValidated && emailCheckValidated && nameValidated) {
            $('#submit-button').prop('disabled', false);
        } else {
            $('#submit-button').prop('disabled', true);
        }
    }
});
