package com.klimashin.math.entity;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Speed extends Vector<Speed> {

    @Builder(builderMethodName = "buildByAbsoluteValues")
    public Speed(double x, double y, double z) {
        super(x, y, z);
    }

    @Builder(builderMethodName = "builderByPoints")
    public Speed(Point<?> firstPoint, Point<?> secondPoint) {
        super(firstPoint, secondPoint);
    }

    public Speed(double x, double y, double z, Point<?> firstPoint, Point<?> secondPoint) {
        super(x, y, z, firstPoint, secondPoint);
    }
}
