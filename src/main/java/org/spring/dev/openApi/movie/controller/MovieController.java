package org.spring.dev.openApi.movie.controller;

import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.movie.dto.MovieDto;
import org.spring.dev.openApi.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    // 영화 목록 가져 와서 db에 저장
    @GetMapping("/link")
    @ResponseBody
    public String linkMovieList() {
        movieService.linkMovieList();
        return null;
    }

    // 연동한 데이터를 가져옴
    @GetMapping("/list")
    public String getMovieList(MovieDto movieDto) {
        movieService.getMovieList(movieDto);
        return "movie/movie";
    }

}
