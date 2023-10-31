package org.spring.dev.openApi.movie.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class movieListResult {

    private Integer totCnt;
    private String source;
    private List<movieList> movieList;

}
