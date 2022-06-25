package com.klimashin.modeler.model.control.implementation;

import com.klimashin.math.entity.abstraction.Vector;
import com.klimashin.modeler.model.control.ControlType;
import com.klimashin.modeler.model.control.ControlUnit;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BodyTargetControl implements ControlUnit {

    ControlType controlType;

    public BodyTargetControl() {
        this.controlType = ControlType.BODY_TARGET;
    }

    @Override
    public Vector getForceDirection() {
        return null;
    }
}
