let data = {};
let webSocket;
let userId = document.querySelector('#userId');
let connectBtn = document.querySelector('#connectBtn');
let sendBtn = document.querySelector('#sendBtn');
let chatWindowCon = document.querySelector('.window-con');
let msg = document.querySelector('#message');
let url = "localhost:8023";

connectBtn.addEventListener('click', ()=>{

    // 웹소켓 접속
    webSocket = new WebSocket("ws://"+url+"/messenger");

    if(userId.value.length <= 0 || userId.value == ""){
        alert("접속자명을 입력하세요.")
        userId.focus();
        return false;
    }

    alert(userId.value + "님 접속했습니다.");

    // 메세지
    webSocket.onmessage = function(msg){
        // 웹소켓 메세지 수신
        let data = JSON.parse(msg.data);
        let divTag = document.createElement('div');
        let className;

        if(data.userId == userId.value){
            className = "chat-main"
        } else {
            className = "chat-sub"
        }

        // 템플릿 리터럴
        let item=`
            <div class='${className}'>
                 <p>
                    <span>[USER]:${data.userId}</span> <span>[작성시간]: ${data.date}</span>
                  </p>
                  <p>
                    [MESSAGE]: ${data.msg}
                  </p>
            </div>
        `;

        divTag.innerHTML = item;
        chatWindowCon.append(divTag);

    };
});

// 메세지 전송
sendBtn.addEventListener('click', sendMessageFn);
function sendMessageFn(){

    if(msg.value.length>0){

        data.userId = userId.value;
        data.msg = msg.value;
        data.date = new Date().toLocaleDateString();

        let sendData = JSON.stringify(data);
        webSocket.send(sendData);
    }

}