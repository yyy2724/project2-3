package org.spring.dev.company.dto.weather;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WeatherInfo {

    private Long id;

    private String city;

    private String country;

    private String temp;

    private String temp_max;

    private String temp_min;

}
