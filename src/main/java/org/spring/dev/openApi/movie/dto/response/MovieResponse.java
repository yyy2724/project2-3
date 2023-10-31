package org.spring.dev.openApi.movie.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {

    private movieListResult movieListResult;
}
