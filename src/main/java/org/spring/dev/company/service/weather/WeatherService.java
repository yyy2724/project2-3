package org.spring.dev.company.service.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
        Optional<WeatherEntity> optionalWeatherEntity = weatherRepository.findByName(city);
        if (optionalWeatherEntity.isPresent()){
            WeatherInfo weatherInfo = WeatherInfo.builder()
                    .id(optionalWeatherEntity.get().getId())
                    .city(optionalWeatherEntity.get().getName())
                    .country(optionalWeatherEntity.get().getCountry())
                    .temp_min(optionalWeatherEntity.get().getTemp_min())
                    .temp_max(optionalWeatherEntity.get().getTemp_max())
                    .build();
            return weatherInfo;
        }
        return null;
    }
}





