package org.spring.dev.company.dto.chatbot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.dev.company.dto.weather.WeatherInfo;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerDto {

    private Long id;

    private String content;

    private String keyword;

    private MemberInfo info;
    private WeatherInfo weatherInfo;

    private List<MemberInfo> memberInfoList;
    private List<WeatherInfo> weatherInfoList;

    public AnswerDto info(MemberInfo info){
        this.info=info;
        return this;
    }

    public AnswerDto weatherInfo(WeatherInfo weatherInfo){
        this.weatherInfo=weatherInfo;
        return this;
    }


}
