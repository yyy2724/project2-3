
function disable() {
    const btn = document.getElementById('disabledBtn');
    const modal = document.getElementById('disabledModal');
    const closeBtn = document.getElementById('closeButton');
    btn.onclick = function () {
        modal.style.display = 'block';
    }
    closeBtn.onclick = function () {
        modal.style.display = 'none';
    }
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}
