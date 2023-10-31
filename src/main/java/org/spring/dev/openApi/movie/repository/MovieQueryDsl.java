package org.spring.dev.openApi.movie.repository;

import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.spring.dev.openApi.movie.dto.MovieDto;
import org.spring.dev.openApi.movie.entity.MovieEntity;

import java.util.List;


public interface MovieQueryDsl {

    List<MovieEntity> findScheduleSearch(MovieDto movieDto);

}
