package org.spring.dev.company.dto.weather;

import lombok.Data;

@Data
public class Sys {
    private String country;
    private Long id;
    private Long sunrise;
    private Long sunset;
    private int type;

}
