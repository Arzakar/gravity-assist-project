package com.klimashin.math.function;

import com.klimashin.math.dto.abstraction.Vector;

public interface ArithmeticApi {

    static float getModule(Vector vector) {
        return (float) Math.sqrt(Math.pow(vector.getX(), 2)
                + Math.pow(vector.getY(), 2)
                + Math.pow(vector.getZ(), 2));
    }
}
