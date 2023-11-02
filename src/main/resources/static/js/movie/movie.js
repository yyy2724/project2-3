var movieNm = document.querySelectorAll('.movieName');
        

movieNm.forEach(function(button){
    button.addEventListener('click', function(){
        $('.modal').fadeIn();

        var movieCd = this.getAttribute('value'); // name 속성을 이용
        console.log('Clicked on movieCode: ' + movieCd);

        $.ajax({
            type: "GET", // HTTP method type(GET, POST) 형식이다.
            url: "/movie/detail/"+movieCd,
            success: function (result) {

                str = '<h1>';
                    $.each(result, function(i){
                        str += result[i].movieNm;
                        str += '</h1>';
                    });
                    $('.name').append(str);
        
                str = '<TR>';
                    $.each(result, function(i){
                        str += '<TD>' + result[i].rank + '</TD>' +
                        '<TD>' + result[i].movieNm + '</TD>' +
                        '<TD>' + result[i].openDt + '</TD>' +
                        '<TD>' + result[i].audiAcc + '</TD>' +
                        '<TD>' + result[i].movieCd + '</TD>';
                        str += '</TR>';
                    });
                    $('.modal-table').append(str);

                // $('#').val('원하는 값');
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
                result.scrnCnt
                result.showCnt
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("통신 실패.")
            }
        
        })
    })
    $('btn-close').click(function(){
        $('.modal').fadeOut();
    })
});
