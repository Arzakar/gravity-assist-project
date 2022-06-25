package com.klimashin.modeler.entity;

import com.klimashin.modeler.entity.data.OrbitData;
import com.klimashin.modeler.mapper.OrbitMapper;
import com.klimashin.modeler.util.Stored;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orbit implements Stored<OrbitData> {

    final OrbitMapper mapper = Mappers.getMapper(OrbitMapper.class);

    double semiMajorAxis;
    double semiMinorAxis;
    double eccentricity;
    double inclination;
    double longitudeAscNode;
    double periapsisArgument;
    double focalParameter;
    double rotationPeriod;

    double currentTrueAnomaly;
    double currentRadius;

    public double changeCurrentTrueAnomalyByDeltaTime(long deltaTime) {
        return currentTrueAnomaly += deltaTime / rotationPeriod;
    }

    public double calculateFocalParameter() {
        return focalParameter = semiMinorAxis * (1 + eccentricity);
    }

    public double calculateCurrentRadius() {
        calculateFocalParameter();
        return currentRadius = focalParameter / (1 + eccentricity * Math.cos(currentTrueAnomaly));
    }

    @Override
    public OrbitData getData() {
        return mapper.toData(this);
    }
}
