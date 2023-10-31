package org.spring.dev.company.repository.weather;


import org.spring.dev.company.entity.weather.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    Optional<WeatherEntity> findByName(String name);

    Optional<WeatherEntity> findAllByName(String name);
}
