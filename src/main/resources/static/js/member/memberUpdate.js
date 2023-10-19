$(document).ready(function () {
    // 초기에는 버튼 비활성화
    $('#submit-button').prop('disabled', true);

    var phoneValidated = true;
    var addressValidated = true;
    var nameValidated = true;

    var phoneInput = $('#phone');
    var addressInput = $('#address');
    var detailAddressInput = $('#detailAddress');
    var nameInput = $('#name');

    phoneInput.on('input', function () {
        if (phoneInput.val() !== "${memberDto.phone}") {
            phoneValidated = false;
            phoneCheck();
        } else {
            phoneValidated = true;
            checkAllFields();
        }
    });

    addressInput.on('input', function () {
        if (addressInput.val() !== "${memberDto.address}") {
            addressValidated = false;
            addressCheck();
        } else {
            addressValidated = true;
            checkAllFields();
        }
    });

    detailAddressInput.on('input', function () {
        if (detailAddressInput.val() !== "${memberDto.detailAddress}") {
            addressValidated = false;
            addressCheck();
        } else {
            addressValidated = true;
            checkAllFields();
        }
    });

    nameInput.on('input', function () {
    var originalValue = nameInput.data('membername');
        if (nameInput.val() !== originalValue) {
            nameValidated = false;
            nameCheck();
        } else {
            nameValidated = true;
            checkAllFields();
        }
    });

    function phoneCheck() {
        var phone = phoneInput.val();
                if (phone.trim() === "") {
                    phoneValidated = false; // 예제에서는 간단히 true로 설정
                    checkAllFields();
                }
                var validatePhone = /^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$/;
                if (!validatePhone.test(phone)) {
                            $('.phone_expression').css("display", "inline-block");
                            $('.phone_already').css("display", "none");
                            $('.phone_ok').css("display", "none");
                        } else {
                            $('.phone_expression').css("display", "none");
                            $.ajax({
                                url: "/join/phoneCheck",
                                type: 'post',
                                data: { "phone": phone },
                                success: function (cnt) {
                                    if (cnt != 0) {
                                        // 유효할 경우 emailValidated를 true로 설정, 그렇지 않으면 false로 설정
                                        //                        phoneValidated = false; // 예제에서는 간단히 true로 설정
                                        //                        checkAllFields();
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

    function addressCheck() {
        var address = addressInput.val();
                var detailAddress = detailAddressInput.val();

                $('.post_already').css("display", "none");
                        if (address === "") {
                            $('.address_already').css("display", "inline-block");
                                addressValidated = false; // 예제에서는 간단히 true로 설정
                                checkAllFields();
                        } else {
                            $('.address_already').css("display", "none");
                            if (detailAddress === "") {
                                $('.detail_already').css("display", "inline-block");
                                addressValidated = false; // 예제에서는 간단히 true로 설정
                                checkAllFields();
                            } else {
                                $('.detail_already').css("display", "none");
                                // 유효할 경우 emailValidated를 true로 설정, 그렇지 않으면 false로 설정
                                addressValidated = true; // 예제에서는 간단히 true로 설정
                                checkAllFields();
                            }
                        }
    }

    function nameCheck() {
        var name = nameInput.val();
                if(name === ""){
                    nameValidated = false; // 예제에서는 간단히 true로 설정
                }else {
                    nameValidated = true; // 예제에서는 간단히 true로 설정
                }
                checkAllFields();
    }

    function checkAllFields() {
        // 모든 필드가 true인 경우에만 버튼 활성화
        if (phoneValidated && addressValidated && nameValidated) {
            $('#submit-button').prop('disabled', false);
        } else {
            $('#submit-button').prop('disabled', true);
        }
    }
});

$(document).ready(function () {


    var phoneValidated = false;
    var addressValidated = false;
    var nameValidated = false;

    var phoneInput = $('#phone');
    var addressInput = $('#address');
    var detailAddressInput = $('#detailAddress');
    var nameInput = $('#name');

    // 이벤트 핸들러 수정
    phoneInput.on('input', function () {
        var currentValue = phoneInput.val();
        if (currentValue !== "${memberDto.phone}") {
            phoneValidated = false;
            phoneCheck();
        }else {
            phoneValidated = true;
            checkAllFields();
        }
    });

    addressInput.on('input', function () {
        var currentValue = addressInput.val();
        if (currentValue !== "${memberDto.address}") {
                     addressValidated = false;
            addressCheck();
        }else {
            addressValidated = true;
            checkAllFields();
        }
    });

    detailAddressInput.on('input', function () {
        var currentValue = detailAddressInput.val();
        if (currentValue !== "${memberDto.detailAddress}") {
                     addressValidated = false;
            addressCheck();
        }else {
                     addressValidated = true;
                     checkAllFields();
                 }
    });
    nameInput.on('input', function () {
        var nameValue = nameInput.val();
        if (currentValue !== "${memberDto.name}") {
            nameValidated = false;
            nameCheck();
        }else {
            nameValidated = true;
            checkAllFields();
        }
    });

    function phoneCheck() {

    }

    function addressCheck() {
        var address = addressInput.val();
        var detailAddress = detailAddressInput.val();

        $('.post_already').css("display", "none");
                if (address === "") {
                    $('.address_already').css("display", "inline-block");
                        addressValidated = false; // 예제에서는 간단히 true로 설정
                        checkAllFields();
                } else {
                    $('.address_already').css("display", "none");
                    if (detailAddress === "") {
                        $('.detail_already').css("display", "inline-block");
                        addressValidated = false; // 예제에서는 간단히 true로 설정
                        checkAllFields();
                    } else {
                        $('.detail_already').css("display", "none");
                        // 유효할 경우 emailValidated를 true로 설정, 그렇지 않으면 false로 설정
                        addressValidated = true; // 예제에서는 간단히 true로 설정
                        checkAllFields();
                    }
                }
    }

    function nameCheck(){

    }

    function checkAllFields() {
        if (phoneValidated && addressValidated && nameValidated) {
            $('#submit-button').prop('disabled', false);
        } else {
            $('#submit-button').prop('disabled', true);
        }
    }
});
