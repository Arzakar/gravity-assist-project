package com.klimashin.celestial.body.api;

import static com.klimashin.celestial.body.api.ApiConstant.API_V0;
import static com.klimashin.celestial.body.api.ApiConstant.BODIES;

import com.klimashin.celestial.body.dto.BodyDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(API_V0 + BODIES)
public interface BodyApi {

    @GetMapping(path = "/{name}")
    BodyDto getCelestialBodyByName(@PathVariable("name") String name);

    @PostMapping
    String saveBody(@RequestBody BodyDto bodyDto);
}
