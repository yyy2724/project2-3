$(document).ready(function(){
    const display = $('#is_display').val();
    let Btn = "";
    if(display==0){
        Btn += "<button type='button' id='deleteBtn'>계정삭제</button>";
        $('#deleteCon').append(Btn);
        deleteF();
    }else if(display!=0){
        Btn += "<button type='button' id='disabledBtn'>계정비활성화</button>";
        $('#disabledCon').append(Btn);
        disable();
    }
});

function deleteF(){
const btn = document.getElementById('deleteBtn');
const modal = document.getElementById('modalCon');
const closeBtn = document.getElementById('closeBtn');
btn.onclick = function(){
    modal.style.display = 'block';
}
closeBtn.onclick = function(){
    modal.style.display = 'none';
}
window.onclick = function(event){
    if(event.target == modal){
        modal.style.display = "none";
    }
}
}
function disable(){
const btn = document.getElementById('disabledBtn');
const modal = document.getElementById('disabledModal');
const closeBtn = document.getElementById('closeButton');
btn.onclick = function(){
    modal.style.display = 'block';
}
closeBtn.onclick = function(){
    modal.style.display = 'none';
}
window.onclick = function(event){
    if(event.target == modal){
        modal.style.display = "none";
    }
}
}
