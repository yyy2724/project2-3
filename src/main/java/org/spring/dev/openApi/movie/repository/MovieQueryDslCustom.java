package org.spring.dev.openApi.movie.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.dto.schedule.ScheduleDto;
import org.spring.dev.company.entity.schedule.QScheduleEntity;
import org.spring.dev.company.entity.schedule.ScheduleEntity;
import org.spring.dev.openApi.movie.dto.MovieDto;
import org.spring.dev.openApi.movie.entity.MovieEntity;
import org.spring.dev.openApi.movie.entity.QMovieEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class MovieQueryDslCustom implements MovieQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    QMovieEntity qMovieEntity = QMovieEntity.movieEntity;

    @Override
    public List<MovieEntity> findScheduleSearch(MovieDto movieDto) {

        return jpaQueryFactory
                .select(qMovieEntity)
                .from(qMovieEntity)
                .where(
                        movieCodeEq(movieDto.getMovieCd()),
                        movieNmEnEq(movieDto.getMovieNmEn()),
                        movieNmEq(movieDto.getMovieNm()),
                        nationAltEq(movieDto.getNationAlt()),
                        openDtEq(movieDto.getOpenDt()),
                        prdtYearEq(movieDto.getPrdtYear())
                )
                .fetch();

    }

    private BooleanExpression movieCodeEq(String movieCd) {
        return movieCd != null ? qMovieEntity.movieCd.eq(movieCd) : null;

    }

    private BooleanExpression movieNmEnEq(String color) {
        return hasText(color) ? qMovieEntity.movieNmEn.eq(color) : null;
    }

    private BooleanExpression movieNmEq(String content) {
        return hasText(content) ? qMovieEntity.movieNm.eq(content) : null;
    }

    private BooleanExpression nationAltEq(String content) {
        return hasText(content) ? qMovieEntity.nationAlt.eq(content) : null;
    }

    private BooleanExpression openDtEq(String content) {
        return hasText(content) ? qMovieEntity.openDt.eq(content) : null;
    }

    private BooleanExpression prdtYearEq(String content) {
        return hasText(content) ? qMovieEntity.prdtYear.eq(content) : null;
    }
}

//    private BooleanExpression colorEq(String color) {
//        return hasText(color) ? qScheduleEntity.color.eq(color) : null;
//    }
//
//    private BooleanExpression ContentEq(String content) {
//        return hasText(content) ? qScheduleEntity.content.eq(content) : null;
//    }
//
//    private BooleanExpression EndDateTimeEq(LocalDateTime endDateTime) {
//        return endDateTime != null ? qScheduleEntity.endDateTime.eq(endDateTime) : null;
//    }
//
//    private BooleanExpression StartDateTimeEq(LocalDateTime startDateTime) {
//        return startDateTime != null ? qScheduleEntity.startDateTime.eq(startDateTime) : null;
//    }
//
//    private BooleanExpression typeEq(String type) {
//        return hasText(type) ? qScheduleEntity.type.eq(type) : null;
//    }
//
//    private BooleanExpression memberIdEq(Long memberId) {
//        return memberId != null ? qScheduleEntity.memberId.eq(memberId) : null;
//    }
