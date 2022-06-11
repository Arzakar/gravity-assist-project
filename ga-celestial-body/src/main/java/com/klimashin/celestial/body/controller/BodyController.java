package com.klimashin.celestial.body.controller;

import com.klimashin.celestial.body.api.BodyApi;
import com.klimashin.celestial.body.dto.BodyDto;
import com.klimashin.celestial.body.service.BodyService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BodyController implements BodyApi {

    private final BodyService bodyService;

    @Override
    public BodyDto getCelestialBodyByName(String name) {
        return bodyService.getCelestialBodyByName(name);
    }

    @Override
    public String saveBody(BodyDto bodyDto) {
        return bodyService.saveBody(bodyDto);
    }
}
