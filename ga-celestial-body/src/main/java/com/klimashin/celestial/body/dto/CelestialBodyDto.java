package com.klimashin.celestial.body.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBodyDto {

    String name;
    float radius;
    float mass;
    float gravParameter;
    float gravRadius;
    float orbitRadius;
    float rotationPeriod;
}
