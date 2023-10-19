$(document).ready(function() {
    // 필요한 조건을 확인하여 버튼 활성화/비활성화
    checkAndUpdateButtonState();

    // 필드가 변경될 때마다 버튼 상태 업데이트
    $('input.input-field').on('input', function() {
        checkAndUpdateButtonState();
    });
});

// 버튼 상태를 확인하고 업데이트하는 함수
function checkAndUpdateButtonState() {
    var allFieldsValid = true;

    // 필드 유효성 검사 또는 다른 조건을 확인
    // 예: 필드가 비어 있거나 올바른 형식인지 확인

    // 필드가 올바르지 않다면:
    // allFieldsValid = false;

    // 버튼 상태 업데이트
    if (allFieldsValid) {
        $('#submit-button').prop('disabled', false); // 버튼 활성화
    } else {
        $('#submit-button').prop('disabled', true); // 버튼 비활성화
    }
}