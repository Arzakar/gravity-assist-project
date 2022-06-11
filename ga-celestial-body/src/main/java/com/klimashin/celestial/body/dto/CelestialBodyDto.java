package com.klimashin.celestial.body.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
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
