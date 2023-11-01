package org.spring.dev.company.service.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.OpenApiUtil;
import org.spring.dev.company.dto.weather.Weather;
import org.spring.dev.company.dto.weather.WeatherApiDto;
import org.spring.dev.company.dto.weather.WeatherInfo;
import org.spring.dev.company.entity.weather.WeatherEntity;
import org.spring.dev.company.repository.weather.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public Map<String, String> weatherSave(WeatherApiDto weatherApiDto) throws Exception {
        WeatherEntity weather = WeatherEntity.builder()
                .lat(weatherApiDto.getCoord().getLat())
                .lon(weatherApiDto.getCoord().getLon())
                .name(weatherApiDto.getName())
                .temp_max(weatherApiDto.getMain().getTemp_max())
                .temp_min(weatherApiDto.getMain().getTemp_min())
                .country(weatherApiDto.getSys().getCountry())
                .build();

        Optional<WeatherEntity> optionalWeatherEntity = weatherRepository.findByName(weather.getName());
        if (!optionalWeatherEntity.isPresent()){
            weatherRepository.save(weather);
        }

        List<Weather> weatherList = weatherApiDto.getWeather()
                .stream()
                .collect(Collectors.toList());

        for(Weather el : weatherList){
            System.out.println("List" + el);
        }

        Map<String, String> weatherMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        String weatherList2 = mapper.writeValueAsString(weatherList);
        weatherMap.put("weather",weatherList2);

        return weatherMap;
    }


    public WeatherInfo weatherList(String city) {


        String appid = "b6616c0963212986998cdd8cf346c479";
        // 날씨
        String apiURL = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appid; // JSON 결과
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-type", "application/json");

        String responseBody = OpenApiUtil.get(apiURL, requestHeaders);
        System.out.println(" <<  return " + responseBody);

        // JSON -> DB
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(" <<  responseBody " + responseBody);

        WeatherApiDto response = null;
        try {
            // json 문자열데이터를 -> 클래스에 매핑
            response = objectMapper.readValue(responseBody, WeatherApiDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(" <<  WeatherApiDto " + response);

        WeatherEntity weatherEntity = WeatherEntity.builder()
                .lat(response.getCoord().getLat())
                .lon(response.getCoord().getLon())
                .name(response.getName())
                .temp_max(response.getMain().getTemp_max())
                .temp_min(response.getMain().getTemp_min())
                .country(response.getSys().getCountry())
                .build();

        Optional<WeatherEntity> optionalWeatherEntity
                = weatherRepository.findByName(response.getName());
        if (!optionalWeatherEntity.isPresent()) {
            weatherRepository.save(weatherEntity);

            WeatherInfo weatherInfo = WeatherInfo.builder()
                    .id(weatherEntity.getId())
                    .city(weatherEntity.getName())
                    .country(weatherEntity.getCountry())
                    .temp_min(weatherEntity.getTemp_min())
                    .temp_max(weatherEntity.getTemp_max())
                    .build();
            return weatherInfo;
        }else {
            WeatherInfo weatherInfo = WeatherInfo.builder()
                    .id(optionalWeatherEntity.get().getId())
                    .city(optionalWeatherEntity.get().getName())
                    .country(optionalWeatherEntity.get().getCountry())
                    .temp_min(optionalWeatherEntity.get().getTemp_min())
                    .temp_max(optionalWeatherEntity.get().getTemp_max())
                    .build();
            return weatherInfo;
        }

    }
}





