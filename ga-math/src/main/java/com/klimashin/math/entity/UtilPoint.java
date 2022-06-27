package com.klimashin.math.entity;

import com.klimashin.math.entity.abstraction.Point;
import lombok.Builder;

public class UtilPoint extends Point<UtilPoint> {

    @Builder
    public UtilPoint(double x, double y, double z) {
        super(x, y, z);
    }
}
