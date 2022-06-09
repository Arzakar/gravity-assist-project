package com.klimashin.orbit.model;

import com.klimashin.orbit.validation.OrbitConstraintValidator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orbit {

    OrbitType orbitType;
    float semiMajorAxis;
    float eccentricity;
    float inclination;
    float longitudeAscNode;
    float periapsisArgument;
    float trueAnomaly;

    @Builder
    public Orbit(OrbitType orbitType,
                 float semiMajorAxis,
                 float eccentricity,
                 float inclination,
                 float longitudeAscNode,
                 float periapsisArgument,
                 float trueAnomaly) {

        this.orbitType = orbitType;
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.longitudeAscNode = longitudeAscNode;
        this.periapsisArgument = periapsisArgument;
        this.trueAnomaly = trueAnomaly;

        List<String> errorMessages = new OrbitConstraintValidator().validate(this);

        if(!errorMessages.isEmpty()) {
            throw new RuntimeException(errorMessages.toString());
        }
    }

}
