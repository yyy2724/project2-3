function emailSend2() {

    const clientEmail = $("#mail").val();
    const emailYN = isEmail(clientEmail);


    console.log('입력 이메일' + clientEmail);

    if (emailYN == true) {


        $.ajax({
            type: "post",
            url: "/member/check",
            data: {email: clientEmail},
            success: function (data) {
                alert('인증번호를 보냈습니다.');
            }, error: function (e) {
                alert('오류입니다. 잠시 후 다시 시도해주세요.');
            }

        });


    } else {

    }

}

function isEmail(asValue) {
    const regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regExp.test(asValue); // 형식에 맞는 경우 true 리턴
}

function emailCertification() {

    let clientEmail = document.getElementById('mail').value;

    let inputCode = document.getElementById('certificationNumber').value;

    console.log('이메일' + clientEmail);
    console.log('인증코드' + inputCode);

    $.ajax({
            type: "post",
            url: "/member/emailCheck",
            data: {email: clientEmail, inputCode: inputCode},
            success: function (result) {
                console.log(result);
                if (result == true) {
                    alert("인증완료");
                    document.getElementById('certificationYN').value = "true";

                    clientEmail.onchange = function () {
                        document.getElementById('certificationYN').value = "false";
                    }

                } else {
                    alert('인증번호가 틀립니다.');
                }
            }
        }
    );
}



