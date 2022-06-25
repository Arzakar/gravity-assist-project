package com.klimashin.math.operation;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

import lombok.experimental.UtilityClass;

/**
 * TODO: Доработать методы с переменным числом аргументов, чтобы ускорить работу (когда передаётся всего 2, 3, 4 вектора)
 * TODO: Метод sum можно реализовать при помощи add
 */
@UtilityClass
public class VectorOperation {

    public Vector toUnit(Vector vector) {
        double scalar = getScalar(vector);

        return vector.setX(vector.getX() / scalar)
                .setY(vector.getY() / scalar)
                .setZ(vector.getZ() / scalar);
    }

    public Vector getUnit(Vector vector) {
        double scalar = getScalar(vector);

        return new Vector(vector.getX() / scalar, vector.getY() / scalar, vector.getZ() / scalar);
    }

    public double getScalar(Vector vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
    }

    public Vector internalAdd(Vector originVector, Vector addedVector) {
        return originVector.setX(originVector.getX() + addedVector.getX())
                .setY(originVector.getY() + addedVector.getY())
                .setZ(originVector.getZ() + addedVector.getZ());
    }

    public Vector externalAdd(Vector originVector, Vector addedVector) {
        return new Vector().setX(originVector.getX() + addedVector.getX())
                .setY(originVector.getY() + addedVector.getY())
                .setZ(originVector.getZ() + addedVector.getZ());
    }

    public Vector internalSub(Vector originVector, Vector subtractedVector) {
        return originVector.setX(originVector.getX() - subtractedVector.getX())
                .setY(originVector.getY() - subtractedVector.getY())
                .setZ(originVector.getZ() - subtractedVector.getZ());
    }

    public Vector externalSub(Vector originVector, Vector subtractedVector) {
        return new Vector().setX(originVector.getX() - subtractedVector.getX())
                .setY(originVector.getY() - subtractedVector.getY())
                .setZ(originVector.getZ() - subtractedVector.getZ());
    }

    public Vector sum(Vector... vectors) {
        /**
         * От данной реализации пришлось отказаться, т.к. она работает в 2 раза медленнее обычного цикла
         * double x = Arrays.stream(vectors).map(Vector::getX).mapToDouble(Double::doubleValue).sum();
         * double y = Arrays.stream(vectors).map(Vector::getY).mapToDouble(Double::doubleValue).sum();
         * double z = Arrays.stream(vectors).map(Vector::getZ).mapToDouble(Double::doubleValue).sum();
         */
        double x = 0;
        double y = 0;
        double z = 0;

        for (Vector vector : vectors) {
            x += vector.getX();
            y += vector.getY();
            z += vector.getZ();
        }

        return new Vector(x, y, z);
    }

    public Vector internalMult(Vector vector, double ratio) {
        return vector.setX(vector.getX() * ratio)
                .setY(vector.getY() * ratio)
                .setZ(vector.getZ() * ratio);
    }

    public Vector externalMult(Vector vector, double ratio) {
        return new Vector().setX(vector.getX() * ratio)
                .setY(vector.getY() * ratio)
                .setZ(vector.getZ() * ratio);
    }

    public Vector internalDiv(Vector vector, double ratio) {
        return internalMult(vector, 1 / ratio);
    }

    public Vector externalDiv(Vector vector, double ratio) {
        return externalMult(vector, 1 / ratio);
    }

    public Vector crossProduct(Vector firstVector, Vector secondVector) {
        return new Vector().setX(firstVector.getY() * secondVector.getZ() - firstVector.getZ() * secondVector.getY())
                .setY(firstVector.getZ() * secondVector.getX() - firstVector.getX() * secondVector.getZ())
                .setZ(firstVector.getX() * secondVector.getY() - firstVector.getY() * secondVector.getX());
    }

    public Vector internalRotateByZ(Vector vector, double angleInRadian) {
        double x = (Math.cos(angleInRadian) * vector.getX()) + (-Math.sin(angleInRadian) * vector.getY());
        double y = (Math.sin(angleInRadian) * vector.getX()) + (Math.cos(angleInRadian) * vector.getY());
        double z = vector.getZ();

        return vector.setX(x).setY(y).setZ(z);
    }

    public Vector externalRotateByZ(Vector vector, double angleInRadian) {
        double x = (Math.cos(angleInRadian) * vector.getX()) + (-Math.sin(angleInRadian) * vector.getY());
        double y = (Math.sin(angleInRadian) * vector.getX()) + (Math.cos(angleInRadian) * vector.getY());
        double z = vector.getZ();

        return new Vector(x, y, z);
    }

    public Vector vectorBetween(Point fromPoint, Point toPoint) {
        return new Vector(fromPoint, toPoint);
    }
}
