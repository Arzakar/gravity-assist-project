package com.klimashin.celestial.body.api;

import com.klimashin.celestial.body.dto.CelestialBodyDto;

public interface CelestialBodyApi {

    CelestialBodyDto getCelestialBodyByName(String name);
}
