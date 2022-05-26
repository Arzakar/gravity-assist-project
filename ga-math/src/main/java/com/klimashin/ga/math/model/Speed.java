package com.klimashin.ga.math.model;

import com.klimashin.ga.math.model.abstraction.Vector;
import lombok.Builder;

public class Speed extends Vector {

    public Speed() {
        super();
    }

    public Speed(Vector vector) {
        super(vector);
    }

    @Builder
    public Speed(float x, float y, float z) {
        super(x, y, z);
    }

}
