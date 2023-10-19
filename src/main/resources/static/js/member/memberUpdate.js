$(document).ready(function () {
    $('#submit-button').prop('disabled', true);
    // 인증 체크
    var phoneValidated = false;
    var addressValidated = false;

    var phoneInput = $('#phone');
    var addressInput = $('#address');
    var detailAddressInput = $('#detailAddress');

    // 다른 필드에 대한 oninput 이벤트 핸들러
    phoneInput.on('input', function () {
        phoneCheck();
    });
    addressInput.on('input', function () {
        addressCheck();
    });
    detailAddressInput.on('input', function () {
        addressCheck();
    });

    function phoneCheck() {
        var phone = phoneInput.val();
        var validatePone = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-?\d{3,4}-?\d{4}$/;
        if (!validatePone.test(phone)) {
            $('.phone_expression').css("display", "inline-block");
            $('.phone_already').css("display", "none");
            $('.phone_ok').css("display", "none");
            phoneValidated = false; // 예제에서는 간단히 true로 설정
            checkAllFields();
        } else {
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
    // 주소 유효성 검사
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
                // 유효할 경우 emailValidated를 true로 설정, 그렇지 않으면 false로 설정
                addressValidated = true; // 예제에서는 간단히 true로 설정
                checkAllFields();
            }
        }
    }
    function checkAllFields() {
            if (phoneValidated && addressValidated) {
                $('#submit-button').prop('disabled', false);
            } else {
                $('#submit-button').prop('disabled', true);
            }
    }
});