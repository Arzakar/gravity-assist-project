package com.klimashin.math.operation;

import lombok.experimental.UtilityClass;

import static com.klimashin.math.entity.Constant.GRAVITATIONAL_CONSTANT;

@UtilityClass
public class PhysicalOperation {

    public double gravitationForce(double massFirstObject, double massSecondObject, double range) {
        return GRAVITATIONAL_CONSTANT * massFirstObject * massSecondObject / Math.pow(range, 2);
    }

}
