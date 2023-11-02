package org.spring.dev.openApi.bus.busStation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusDetailRepository extends JpaRepository<BusDetailEntity,Long> {
  Optional<BusDetailEntity> findByBusRouteId(String busRouteId);

  Optional<BusDetailEntity> findByStationNo(String stationNo);
}
