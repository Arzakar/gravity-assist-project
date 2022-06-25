package com.klimashin.modeler.entity;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

public interface MaterialPoint {

    double getCurrentMass();
    Point getCurrentPosition();
    Vector getCurrentSpeed();
    Vector getCurrentAcceleration();
    Vector getCurrentForce();
}
