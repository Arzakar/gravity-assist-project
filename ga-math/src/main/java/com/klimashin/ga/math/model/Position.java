package com.klimashin.ga.math.model;

import com.klimashin.ga.math.model.abstraction.Point;
import lombok.Builder;

public class Position extends Point {

    public Position() {
        super();
    }

    public Position(Point point) {
        super(point);
    }

    @Builder
    public Position(float x, float y, float z) {
        super(x, y, z);
    }
}
