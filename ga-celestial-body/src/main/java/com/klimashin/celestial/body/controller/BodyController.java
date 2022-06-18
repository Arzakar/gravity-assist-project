package com.klimashin.celestial.body.controller;

import com.klimashin.celestial.body.api.BodyApi;
import com.klimashin.celestial.body.dto.BodyDto;
import com.klimashin.celestial.body.dto.BodyRequestDto;
import com.klimashin.celestial.body.service.BodyService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BodyController implements BodyApi {

    private final BodyService bodyService;

    @Override
    public List<BodyDto> getAllCelestialBody() {
        return bodyService.getAllBodies();
    }

    @Override
    public BodyDto getCelestialBodyByName(String name) {
        return bodyService.getBodyByName(name);
    }

    @Override
    public BodyDto saveBody(BodyRequestDto bodyRequestDto) {
        return bodyService.saveBody(bodyRequestDto);
    }

    @Override
    public List<BodyDto> updateGravitationRadiusInAllBody() {
        return bodyService.updateGravRadiusInAllBodies();
    }
}
