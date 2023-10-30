package org.spring.dev.openApi.bus.response;

import lombok.Builder;
import lombok.Getter;
import org.spring.dev.openApi.bus.entity.Bus;

import java.util.Optional;

@Getter
public class BusResponse {

    private String busNumber;

    private String firstCar;

    private String lastCar;

    private String station;

    @Builder
    private BusResponse(String busNumber, String firstCar, String lastCar, String station) {
        this.busNumber = busNumber;
        this.firstCar = firstCar;
        this.lastCar = lastCar;
        this.station = station;
    }

    public static BusResponse of(Bus bus) {
        return BusResponse.builder()
                .busNumber(bus.getBusNumber())
                .firstCar(bus.getFirstCar())
                .lastCar(bus.getLastCar())
                .station(bus.getStation())
                .build();
    }
}
