package com.klimashin.math.model.math.entity;

import com.klimashin.math.model.math.entity.abstraction.Point;
import com.klimashin.math.model.math.entity.abstraction.Vector;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Acceleration extends Vector<Acceleration> {

    @Builder(builderMethodName = "buildByAbsoluteValues")
    public Acceleration(double x, double y, double z) {
        super(x, y, z);
    }

    @Builder(builderMethodName = "builderByPoints")
    public Acceleration(Point<? extends Point<?>> firstPoint, Point<? extends Point<?>> secondPoint) {
        super(firstPoint, secondPoint);
    }

    public Acceleration(double x, double y, double z, Point<? extends Point<?>> firstPoint, Point<? extends Point<?>> secondPoint) {
        super(x, y, z, firstPoint, secondPoint);
    }
}
