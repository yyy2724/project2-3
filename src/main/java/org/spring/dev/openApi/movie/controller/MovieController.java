package org.spring.dev.openApi.movie.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.movie.dto.MovieDto;
import org.spring.dev.openApi.movie.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    // 영화 목록 가져 와서 db에 저장
    @GetMapping("/list")
    public String getMovieList(MovieDto movieDto){
        movieService.getMovieList(movieDto);
        return null;
    }

}
