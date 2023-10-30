package org.spring.dev.openApi.bus.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BusCreate {

    private String busNumber;

    private String firstCar;

    private String lastCar;

    private String station;

    @Builder
    private BusCreate(String busNumber, String firstCar, String lastCar, String station) {
        this.busNumber = busNumber;
        this.firstCar = firstCar;
        this.lastCar = lastCar;
        this.station = station;
    }
}
