package org.spring.dev.openApi.bus.controller;

import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.bus.request.BusCreate;
import org.spring.dev.openApi.bus.response.BusResponse;
import org.spring.dev.openApi.bus.service.BusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @PostMapping("/create")
    public BusResponse create(@RequestBody BusCreate busCreate) {

       return busService.create(busCreate);

    }

}
