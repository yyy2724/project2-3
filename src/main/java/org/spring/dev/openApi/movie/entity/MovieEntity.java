package org.spring.dev.openApi.movie.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Table(name = "movie_tb")
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String movieCd;

    private String movieNm;

    private String movieNmEn;

    private String prdtYear;

    private String openDt;

    private String nationAlt;

    private String genreAlt;

    private String repNationNm;

    private String repGenreNm;

    private String peopleNm;


}
