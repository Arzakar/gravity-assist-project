package com.klimashin.modeler.model.control.implementation;

import com.klimashin.math.entity.abstraction.Vector;
import com.klimashin.math.operation.VectorOperation;
import com.klimashin.modeler.entity.MaterialPoint;

import com.klimashin.modeler.model.control.ControlType;
import com.klimashin.modeler.model.control.ControlUnit;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConstantDeviationControl implements ControlUnit {

    ControlType controlType;
    MaterialPoint bodyForFromPoint;
    MaterialPoint bodyForToPoint;
    double deviationAngleInRad;

    public ConstantDeviationControl() {
        this.controlType = ControlType.CONSTANT_DEVIATION;
    }

    public ConstantDeviationControl(MaterialPoint bodyForFromPoint,
                                    MaterialPoint bodyForToPoint,
                                    double deviationAngleInRad) {
        this.bodyForFromPoint = bodyForFromPoint;
        this.bodyForToPoint = bodyForToPoint;
        this.deviationAngleInRad = deviationAngleInRad;
        this.controlType = ControlType.CONSTANT_DEVIATION;
    }

    @Override
    public Vector getForceDirection() {
        return VectorOperation.vectorBetween(bodyForFromPoint.getCurrentPosition(), bodyForToPoint.getCurrentPosition())
                .toUnit()
                .rotateByZ(deviationAngleInRad);
    }
}
