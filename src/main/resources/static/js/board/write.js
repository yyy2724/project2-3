function toggleDateFields() {
    const boardType = document.getElementById("boardType").value;
    const dateFields = document.getElementById("dateFields");
    const startDateInput = document.getElementById("startDate");
    const endDateInput = document.getElementById("endDate");

    if (boardType === "GENERAL") {
        dateFields.style.display = "block";
        const today = new Date().toISOString().split('T')[0]; // 매번 새로운 오늘 날짜를 계산
        startDateInput.value = today;
        endDateInput.value = today;

        startDateInput.setAttribute("min", today);
        startDateInput.setAttribute("max", today);
        endDateInput.setAttribute("min", today);
    } else {
        dateFields.style.display = "none";
        startDateInput.removeAttribute("min");
        startDateInput.removeAttribute("max");
        endDateInput.removeAttribute("min");
    }
}

function toggleCalendar() {
    const addEventRadio = document.getElementById("addEventCheckbox");
    const dateCalendar = document.getElementById("dateCalendar");

    if (addEventRadio.checked) {
        dateCalendar.style.display = "block";
    } else {
        dateCalendar.style.display = "none";
    }
}

window.onload = toggleDateFields;


// Quill 초기화
var quill = new Quill('#editor', {
  theme: 'snow' // 에디터 테마 설정 (예: 'snow' 또는 'bubble')
});

// 폼 제출 시 에디터 내용 저장
document.querySelector('form').addEventListener('submit', function() {
  var editorContent = document.querySelector('.ql-editor').innerHTML;
  document.querySelector('#content').value = editorContent;
});

