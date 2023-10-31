package org.spring.dev.openApi.movie.service;

import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.movie.dto.MovieDto;
import org.spring.dev.openApi.movie.dto.response.DailyBoxOffice;
import org.spring.dev.openApi.movie.dto.response.MovieResponse;
import org.spring.dev.openApi.movie.entity.MovieEntity;
import org.spring.dev.openApi.movie.repository.MovieQueryDsl;
import org.spring.dev.openApi.movie.repository.MovieRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieQueryDsl movieQueryDsl;


    // 디비에서 단순 데이터를 가져옴
    public void linkMovieList() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.set("key", "103cdc548e41a2c19cad71203ccf15f8");
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString("http://www.kobis.or.kr")
                .path("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
                .queryParam("key", "103cdc548e41a2c19cad71203ccf15f8")
                .queryParam("targetDt", LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .encode()
                .build()
                .toUri();

        ResponseEntity<MovieResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity, MovieResponse.class);
        if (result.getStatusCode() == HttpStatus.OK) {
            MovieEntity movieEntity;
            for (DailyBoxOffice movie : result.getBody().getBoxOfficeResult().getDailyBoxOfficeList()) {
                movieEntity = toMovie(movie);
                movieRepository.save(movieEntity);
            }
        } else {
            System.out.println(result.getStatusCode());
        }
    }

    public MovieEntity toMovie(DailyBoxOffice movie) {
        return MovieEntity.builder()
                .rnum(movie.getRnum())
                .rank(movie.getRank())
                .rankInten(movie.getRankInten())
                .rankOldAndNew(movie.getRankOldAndNew())
                .movieCd(movie.getMovieCd())
                .movieNm(movie.getMovieNm())
                .openDt(movie.getOpenDt())
                .salesAmt(movie.getSalesAmt())
                .salesShare(movie.getSalesShare())
                .salesInten(movie.getSalesInten())
                .salesChange(movie.getSalesChange())
                .salesAcc(movie.getSalesAcc())
                .audiCnt(movie.getAudiCnt())
                .audiInten(movie.getAudiInten())
                .audiChange(movie.getAudiChange())
                .audiAcc(movie.getAudiAcc())
                .scrnCnt(movie.getScrnCnt())
                .showCnt(movie.getShowCnt())
                .build();

    }


    public MovieDto toDto(MovieEntity movieEntity) {
        return MovieDto.builder()
                .rnum(movieEntity.getRnum())
                .rank(movieEntity.getRank())
                .rankInten(movieEntity.getRankInten())
                .rankOldAndNew(movieEntity.getRankOldAndNew())
                .movieCd(movieEntity.getMovieCd())
                .movieNm(movieEntity.getMovieNm())
                .openDt(movieEntity.getOpenDt())
                .salesAmt(movieEntity.getSalesAmt())
                .salesShare(movieEntity.getSalesShare())
                .salesInten(movieEntity.getSalesInten())
                .salesChange(movieEntity.getSalesChange())
                .salesAcc(movieEntity.getSalesAcc())
                .audiCnt(movieEntity.getAudiCnt())
                .audiInten(movieEntity.getAudiInten())
                .audiChange(movieEntity.getAudiChange())
                .audiAcc(movieEntity.getAudiAcc())
                .scrnCnt(movieEntity.getScrnCnt())
                .showCnt(movieEntity.getShowCnt())
                .build();
    }


    //가져옴
    public List<MovieDto> getMovieList(MovieDto movieDto) {
        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();
        List<MovieEntity> movieEntityList = movieQueryDsl.findScheduleSearch(movieDto);

        for (MovieEntity movieEntity : movieEntityList) {
            movieDtoList.add(toDto(movieEntity));
        }
        return movieDtoList;
    }
}
