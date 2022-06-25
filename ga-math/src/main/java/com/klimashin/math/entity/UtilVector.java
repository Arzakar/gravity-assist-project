package com.klimashin.math.entity;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UtilVector extends Vector<UtilVector> {

    @Builder(builderMethodName = "buildByAbsoluteValues")
    public UtilVector(double x, double y, double z) {
        super(x, y, z);
    }

    @Builder(builderMethodName = "builderByPoints")
    public UtilVector(Point<?> firstPoint, Point<?> secondPoint) {
        super(firstPoint, secondPoint);
    }

    public UtilVector(double x, double y, double z, Point<?> firstPoint, Point<?> secondPoint) {
        super(x, y, z, firstPoint, secondPoint);
    }
}
