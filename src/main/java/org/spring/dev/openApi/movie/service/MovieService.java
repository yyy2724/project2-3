package org.spring.dev.openApi.movie.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.spring.dev.openApi.movie.dto.MovieDto;
import org.spring.dev.openApi.movie.dto.response.MovieResponse;
import org.spring.dev.openApi.movie.dto.response.movieList;
import org.spring.dev.openApi.movie.entity.MovieEntity;
import org.spring.dev.openApi.movie.repository.MovieQueryDsl;
import org.spring.dev.openApi.movie.repository.MovieRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
                .path("/kobisopenapi/webservice/rest/movie/searchMovieList.json")
                .queryParam("key", "103cdc548e41a2c19cad71203ccf15f8")
                .encode()
                .build()
                .toUri();

        ResponseEntity<MovieResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity, MovieResponse.class);
        if (result.getStatusCode() == HttpStatus.OK) {
            MovieEntity movieEntity;
            for (movieList movie : result.getBody().getMovieListResult().getMovieList()) {
                movieEntity = toMovie(movie);
                movieRepository.save(movieEntity);
            }
        } else {
            System.out.println(result.getStatusCode());
        }
    }

    public MovieEntity toMovie(movieList movie) {
        return MovieEntity.builder()
                .movieCd(movie.getMovieCd())
                .movieNm(movie.getMovieNm())
                .movieNmEn(movie.getMovieNmEn())
                .prdtYear(movie.getPrdtYear())
                .openDt(movie.getOpenDt())
                .nationAlt(movie.getNationAlt())
                .genreAlt(movie.getGenreAlt())
                .repNationNm(movie.getRepNationNm())
                .repGenreNm(movie.getRepGenreNm())
                .build();
    }


    public MovieDto toDto(MovieEntity movieEntity) {
        return MovieDto.builder()
                .movieCd(movieEntity.getMovieCd())
                .movieNm(movieEntity.getMovieNm())
                .openDt(movieEntity.getOpenDt())
                .prdtYear(movieEntity.getPrdtYear())
                .nationAlt(movieEntity.getNationAlt())
                .genreAlt(movieEntity.getGenreAlt())
                .build();
    }


    //가져옴
    public List<MovieDto> getMovieList(MovieDto movieDto) {
        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();
        List<MovieEntity> movieEntityList = movieQueryDsl.findScheduleSearch(movieDto);

        for (MovieEntity movieEntity: movieEntityList) {
            movieDtoList.add(toDto(movieEntity));
        }
        return movieDtoList;
    }
}
