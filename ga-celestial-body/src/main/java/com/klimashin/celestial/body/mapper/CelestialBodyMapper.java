package com.klimashin.celestial.body.mapper;

import com.klimashin.celestial.body.dto.CelestialBodyDto;
import com.klimashin.celestial.body.entity.CelestialBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CelestialBodyMapper {

    CelestialBodyDto toDto(CelestialBody celestialBody);
}
