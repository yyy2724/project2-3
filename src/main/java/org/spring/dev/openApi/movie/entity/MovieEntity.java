package org.spring.dev.openApi.movie.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movie_tb")
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String rnum;
    private String rank;
    private String rankInten;
    private String rankOldAndNew;
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String salesAmt;
    private String salesShare;
    private String salesInten;
    private String salesChange;
    private String salesAcc;
    private String audiCnt;
    private String audiInten;
    private String audiChange;
    private String audiAcc;
    private String scrnCnt;
    private String showCnt;


}
