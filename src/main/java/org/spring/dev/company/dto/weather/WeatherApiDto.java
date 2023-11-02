package org.spring.dev.company.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WeatherApiDto {

    private String base;
    private Clouds clouds;
    private int cod;
    private Coord coord;
    private Long dt;
    private Long id;
    private MainDto main;
    private String name;
    private Sys sys;
    private Long timezone;
    private Long visibility;
    private List<Weather> weather;
    private Wind wind;

}
