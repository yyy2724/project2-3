const passwordInput = document.getElementById('certificationNumber');
const confirmPasswordInput = document.getElementById('certificationNumber1');
const pwRightMsg = document.querySelector('.pw_right');
const pwStringMsg = document.querySelector('.pw_string');
const pwNotMsg = document.querySelector('.pw_not');





confirmPasswordInput.addEventListener('input', () => {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    // 비밀번호가 일치하면 pw_right 표시, 아니면 숨김
    if (password === confirmPassword) {
        pwRightMsg.style.display = 'inline';
        pwNotMsg.style.display = 'none';
    } else {
        pwRightMsg.style.display = 'none';
        pwNotMsg.style.display = 'inline';
    }

    // 비밀번호 규칙에 맞으면 pw_string 표시, 아니면 숨김
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;
    if (passwordPattern.test(confirmPassword)) {
        pwStringMsg.style.display = 'none';
    } else {

        pwStringMsg.style.display = 'inline';
    }
});

