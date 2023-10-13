//// #ajaxBtn(아이디가 ajaxBtn)버튼을 클릭하면 ajaxFm함수를 실행
//$('#replyBtn').on('click',replyFn);
//
//function replyFn(){
//
//    const data={
//     'boardId': $('#boardId').val(),
//     'content': $('#content').val(),
//     'writer': $('#writer').val()
//    }
//
//    console.log(data);
//
//    $.ajax({
//        type:'POST',
//        url: "/reply/write",
//        data: data,
//        success: function(res){
//            location.reload();
//        },
//        error: ()=>{
//            alert("Fail");
//        }
//
//    });
//
//}
//
// function replyDelete(replyId) {
//         if (confirm("댓글을 삭제하시겠습니까?")) {
//             $.ajax({
//                 type: "DELETE",
//                 url: "/reply/delete/"+replyId,
//                 data: { id: replyId },
//                 success: function (data) {
//                     if (data === "success") {
//                         // 삭제가 성공하면 페이지를 다시 로드하거나, 댓글을 화면에서 제거합니다.
//                         // 여기서는 페이지 리로드를 예시로 사용하겠습니다.
//                         location.reload();
//                     } else {
//                         alert("댓글 삭제에 실패했습니다.");
//                     }
//                 },
//                 error: function () {
//                     alert("댓글 삭제 요청 중 오류가 발생했습니다. 다시 시도해 주세요.");
//                 }
//             });
//         }
// }
//
//
//function replyUpdate(replyId) {
//
//    const content = $('#updateContent').val();
//    const writer = $('#updateWriter').val();
//
//    console.log("댓글 수정 데이터: ", {
//        id: replyId,
//        content: content,
//        writer: writer
//    });
//
//    if (confirm("댓글을 수정하시겠습니까?")) {
//        // 업데이트 요청 보내기
//        $.ajax({
//            type: "PATCH",
//            url: "/reply/update/"+replyId,
//            data: JSON.stringify({
//                id: replyId,
//                content: content,
//                writer: writer
//            }),
//            contentType: "application/json", // Content-Type을 JSON으로 설정
//            success: function (data) {
//                if (data === "success") {
//                    alert("댓글 수정 완료했습니다.");
//                    location.reload();
//                } else {
//                    alert("댓글 수정에 실패했습니다.");
//                }
//            },
//            error: function () {
//                alert("댓글 수정 요청 중 오류가 발생했습니다. 다시 시도해 주세요.");
//            }
//        });
//    }
//}
//
//// 수정 버튼 클릭 시 호출되는 함수
//function showUpdate(event, replyId) {
//    // replyId를 사용하여 list 클래스를 숨기고 update 클래스를 보이게 합니다.
//    const replyClass=document.querySelector('.reply'+replyId);
//    const replyRead=replyClass.querySelector('.list');
//    const replyUpdate=replyClass.querySelector('.update');
//    replyRead.style.display='none';
//    replyUpdate.style.display='block';
//}
//
//// 취소 버튼 클릭 시 호출되는 함수
//function hideUpdate(event, replyId) {
//    // replyId를 사용하여 update 클래스를 숨기고 list 클래스를 보이게 합니다.
//    const replyClass=document.querySelector('.reply'+replyId);
//    const replyRead=replyClass.querySelector('.list');
//    const replyUpdate=replyClass.querySelector('.update');
//    replyRead.style.display='block';
//    replyUpdate.style.display='none';
//}
//
//
//
//
//
