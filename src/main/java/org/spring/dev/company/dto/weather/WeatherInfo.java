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

    private double temp;

    private double temp_max;

    private double temp_min;

}
