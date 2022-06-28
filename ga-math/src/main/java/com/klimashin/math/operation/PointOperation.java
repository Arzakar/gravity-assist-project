package com.klimashin.math.operation;

import com.klimashin.math.entity.abstraction.Point;

public interface PointOperation {

    static double distanceBetween(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2)
                + Math.pow(firstPoint.getZ() - secondPoint.getZ(), 2));
    }
}
