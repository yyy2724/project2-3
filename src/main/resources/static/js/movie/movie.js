$.ajax({

    type: "GET", // HTTP method type(GET, POST) 형식이다.
    url: "/movie/detail/" + "20234664",
    success: function (result) {

        $('#').val('원하는 값');
        result.audiAcc
        result.audiChange
        result.audiCnt
        result.audiInten
        result.movieCd
        result.movieNm
        result.openDt
        result.rank
        result.rankInten
        result.rankOldAndNew
        result.rnum
        result.salesAcc
        result.salesAmt
        result.salesChange
        result.salesInten
        result.salesShare
<<<<<<< Updated upstream
        result.scrnCnt
        result.showCnt
=======
>>>>>>> Stashed changes
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        alert("통신 실패.")
    }

})