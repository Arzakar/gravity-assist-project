package com.klimashin.math.dto;

import com.klimashin.math.dto.abstraction.Vector;

import lombok.Builder;

public class Acceleration extends Vector {

    public Acceleration() {
        super();
    }

    public Acceleration(Vector vector) {
        super(vector);
    }

    @Builder
    public Acceleration(float x, float y, float z) {
        super(x, y, z);
    }

}
