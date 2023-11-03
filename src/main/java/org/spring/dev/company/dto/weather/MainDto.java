package org.spring.dev.company.dto.weather;

import lombok.Data;

@Data
public class MainDto {
    private double feels_like;
    private int humidity;
    private int pressure;
    private double temp;
    private double temp_max;
    private double temp_min;
    private double sea_level;
    private double grnd_level;

}
