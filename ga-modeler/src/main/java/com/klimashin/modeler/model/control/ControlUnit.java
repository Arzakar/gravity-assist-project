package com.klimashin.modeler.model.control;

import com.klimashin.math.entity.abstraction.Vector;

public interface ControlUnit {

    ControlType getControlType();

    Vector getForceDirection();

}
