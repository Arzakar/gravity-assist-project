package com.klimashin.modeler.model.control.implementation;

import com.klimashin.math.entity.abstraction.Vector;
import com.klimashin.modeler.model.control.ControlType;
import com.klimashin.modeler.model.control.ControlUnit;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DynamicDeviationControl implements ControlUnit {

    ControlType controlType;

    public DynamicDeviationControl() {
        this.controlType = ControlType.DYNAMIC_DEVIATION;
    }

    @Override
    public Vector getForceDirection() {
        return null;
    }
}
