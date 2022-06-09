package com.klimashin.celestial.body.controller;

import com.klimashin.celestial.body.api.CelestialBodyApi;
import com.klimashin.celestial.body.dto.CelestialBodyDto;
import com.klimashin.celestial.body.service.CelestialBodyService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
@RestController
public class CelestialBodyController implements CelestialBodyApi {

    private final CelestialBodyService celestialBodyService;

    @Override
    @GetMapping(path = "/{name}")
    public CelestialBodyDto getCelestialBodyByName(@PathVariable("name") String name) {
        return celestialBodyService.getCelestialBodyByName(name);
    }
}
