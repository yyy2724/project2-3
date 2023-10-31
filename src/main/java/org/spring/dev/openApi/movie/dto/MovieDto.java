package org.spring.dev.openApi.movie.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDto {

    private String movieCd; //영화 코드
    private String movieNm; //영화 이름
    private String movieNmEn; //영화 영어이름
    private String genreAlt; //장르
    private String nationAlt; //제작국가
    private String openDt; //개봉일
    private String prdtYear; //개봉연도
}
