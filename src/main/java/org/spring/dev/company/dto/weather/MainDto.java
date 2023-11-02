package org.spring.dev.company.dto.weather;

import lombok.Data;

@Data
public class MainDto {
    private double feels_like;
    private int humidity;
    private int pressure;
    private String temp;
    private String temp_max;
    private String temp_min;

}
