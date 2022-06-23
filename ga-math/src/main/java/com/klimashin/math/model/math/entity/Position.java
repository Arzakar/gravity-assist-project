package com.klimashin.math.model.math.entity;

import com.klimashin.math.model.math.entity.abstraction.Point;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Position extends Point<Position> {

    @Builder
    public Position(double x, double y, double z) {
        super(x, y, z);
    }
}
