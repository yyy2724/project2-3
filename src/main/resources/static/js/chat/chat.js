
let webSocket;
let connectBtn = document.querySelector('#connectBtn');

let url = "localhost:8095";


connectBtn.addEventListener('click', ()=>{
    // 웹소켓 접속
    webSocket = new WebSocket("ws://"+url+"/messenger");

       webSocket.onopen = function(event) {

           // "게시글 작성 완료" 메시지를 서버로 전송
           const message = "게시글 작성 완료";
           webSocket.send(message);

       };


});

function requestNotificationPermission() {
    if (Notification.permission !== 'granted') {
        Notification.requestPermission().then(function (permission) {
            if (permission === 'granted') {
                // 알림 권한이 부여됨
            } else if (permission === 'denied') {
                // 알림 권한이 거부됨
                console.log("알림 권한이 거부되었습니다. 알림을 표시할 수 없습니다.");
            } else if (permission === 'default') {
                // 사용자가 알림 권한을 설정하지 않음
                console.log("알림 권한이 설정되지 않았습니다. 사용자에게 물어보세요.");
            }
        });
    }
}

function showNotification(title, message2) {
    if (Notification.permission === 'granted') {
        // 알림 권한이 부여된 경우 알림 생성
        const notification = new Notification(title, {body: message2});
    }
}

// 호출 시 알림 권한 확인
requestNotificationPermission();
