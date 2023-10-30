package org.spring.dev.openApi.bus.service;

import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.bus.entity.Bus;
import org.spring.dev.openApi.bus.repository.BusRepository;
import org.spring.dev.openApi.bus.request.BusCreate;
import org.spring.dev.openApi.bus.response.BusResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public BusResponse create(BusCreate busCreate) {
        Optional<Bus> bus = busRepository.findByBusNumber(busCreate.getBusNumber());

        if (bus.isPresent()) {
            Bus save = busRepository.save(Bus.toEntity(busCreate));

            return BusResponse.of(save);
        } else {
            return BusResponse.of(bus.get());
        }
    }
}
