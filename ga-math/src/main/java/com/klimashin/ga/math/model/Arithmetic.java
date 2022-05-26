package com.klimashin.ga.math.model;

import com.klimashin.ga.math.model.abstraction.Vector;

public class Arithmetic {

    public static float getModule(Vector vector) {
        return (float) Math.sqrt(Math.pow(vector.getX(), 2)
                + Math.pow(vector.getY(), 2)
                + Math.pow(vector.getZ(), 2));
    }
}
