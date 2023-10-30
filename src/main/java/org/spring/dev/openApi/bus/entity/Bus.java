package org.spring.dev.openApi.bus.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.dev.openApi.bus.request.BusCreate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    private String firstCar;

    private String lastCar;

    private String station;

    @Builder
    private Bus(Long id, String busNumber, String firstCar, String lastCar, String station) {
        this.id = id;
        this.busNumber = busNumber;
        this.firstCar = firstCar;
        this.lastCar = lastCar;
        this.station = station;
    }

    public static Bus toEntity(BusCreate busCreate) {
        return Bus.builder()
                .busNumber(busCreate.getBusNumber())
                .firstCar(busCreate.getFirstCar())
                .lastCar(busCreate.getLastCar())
                .station(busCreate.getStation())
                .build();
    }
}
