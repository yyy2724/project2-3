package org.spring.dev.company.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Weather {
    private Long id;
    private String main;
    private String description;
    private String icon;

}
