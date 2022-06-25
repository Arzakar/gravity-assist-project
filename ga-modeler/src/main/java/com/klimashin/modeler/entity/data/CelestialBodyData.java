package com.klimashin.modeler.entity.data;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

import com.klimashin.modeler.entity.CelestialBody;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBodyData {

    String name;
    double radius;
    double currentMass;
    double gravParameter;
    double gravRadius;

    OrbitData orbitdata;

    Point endPosition;
    Vector endSpeed;
    Vector endAcceleration;
    Vector endForce;

}
