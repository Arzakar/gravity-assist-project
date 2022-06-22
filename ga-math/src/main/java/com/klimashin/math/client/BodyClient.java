package com.klimashin.math.client;

import com.klimashin.math.dto.BodyDto;
import com.klimashin.math.dto.BodyRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ga-celestial-body", path = "/v0/bodies")
public interface BodyClient {

    @GetMapping
    List<BodyDto> getAllCelestialBody();

    @GetMapping(path = "/{name}")
    BodyDto getCelestialBodyByName(@PathVariable("name") String name);

    @PostMapping
    BodyDto saveBody(@RequestBody BodyRequestDto bodyDto);

    @PostMapping(path = "/update/gravitation-radius")
    List<BodyDto> updateGravitationRadiusInAllBody();
}
