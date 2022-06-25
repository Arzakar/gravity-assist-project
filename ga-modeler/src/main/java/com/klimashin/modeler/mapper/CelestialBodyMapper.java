package com.klimashin.modeler.mapper;

import com.klimashin.modeler.entity.CelestialBody;
import com.klimashin.modeler.entity.Orbit;
import com.klimashin.modeler.entity.data.CelestialBodyData;
import com.klimashin.modeler.entity.data.OrbitData;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CelestialBodyMapper {

    OrbitMapper ORBIT_MAPPER = Mappers.getMapper(OrbitMapper.class);

    @Mapping(target = "orbitData", source = "orbit", qualifiedByName = "orbitData")
    @Mapping(target = "endPosition", source = "currentPosition")
    @Mapping(target = "endSpeed", source = "currentSpeed")
    @Mapping(target = "endAcceleration", source = "currentAcceleration")
    @Mapping(target = "endForce", source = "currentForce")
    CelestialBodyData toData(CelestialBody celestialBody);

    @Named("orbitData")
    private OrbitData toData(Orbit orbit) {
        return ORBIT_MAPPER.toData(orbit);
    }
}
