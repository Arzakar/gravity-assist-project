package com.klimashin.modeler.entity.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrbitData {

    double semiMajorAxis;
    double semiMinorAxis;
    double eccentricity;
    double inclination;
    double longitudeAscNode;
    double periapsisArgument;
    double focalParameter;
    double rotationPeriod;

}
