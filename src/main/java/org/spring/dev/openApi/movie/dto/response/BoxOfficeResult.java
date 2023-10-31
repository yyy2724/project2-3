package org.spring.dev.openApi.movie.dto.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoxOfficeResult {

    private String boxofficeType;
    private String showRange;
    private List<DailyBoxOffice> dailyBoxOfficeList;


}
