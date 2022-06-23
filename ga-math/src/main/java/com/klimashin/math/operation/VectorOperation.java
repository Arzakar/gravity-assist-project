package com.klimashin.math.operation;

import com.klimashin.math.entity.UtilVector;
import com.klimashin.math.entity.abstraction.Vector;
import com.klimashin.math.exception.VectorOperationException;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class VectorOperation {

    public Vector<?> getUnit(Vector<?> vector) {
        double scalar = getScalar(vector);

        return createVectorOfSameClass(vector, vector.getX() / scalar, vector.getY() / scalar, vector.getZ() / scalar);
    }

    public double getScalar(Vector<?> vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
    }

    public Vector<?> sum(Vector<?>... vectors) {
        Vector<?> firstElementClass = Arrays.stream(vectors).toList().get(0);

        for (Vector<?> vector : vectors) {
            if (!vector.getClass().equals(firstElementClass.getClass())) {
                throw new VectorOperationException("Векторы в массиве имеют разный тип");
            }
        }

        double x = Arrays.stream(vectors).map(Vector::getX).mapToDouble(Double::doubleValue).sum();
        double y = Arrays.stream(vectors).map(Vector::getY).mapToDouble(Double::doubleValue).sum();
        double z = Arrays.stream(vectors).map(Vector::getZ).mapToDouble(Double::doubleValue).sum();

        return createVectorOfSameClass(firstElementClass, x, y, z);
    }

    public UtilVector crossProduct(Vector<?> firstVector, Vector<?> secondVector) {
        return new UtilVector().setX(firstVector.getY() * secondVector.getZ() - firstVector.getZ() * secondVector.getY())
                .setY(firstVector.getZ() * secondVector.getX() - firstVector.getX() * secondVector.getZ())
                .setZ(firstVector.getX() * secondVector.getY() - firstVector.getY() * secondVector.getX());
    }

    public Vector<?> internalRotateByZ(Vector<?> vector, double angleInRadian) {
        double x = (Math.cos(angleInRadian) * vector.getX()) + (-Math.sin(angleInRadian) * vector.getY());
        double y = (Math.sin(angleInRadian) * vector.getX()) + (Math.cos(angleInRadian) * vector.getY());
        double z = vector.getZ();

        return vector.setX(x).setY(y).setZ(z);
    }

    public Vector<?> externalRotateByZ(Vector<?> vector, double angleInRadian) {
        double x = (Math.cos(angleInRadian) * vector.getX()) + (-Math.sin(angleInRadian) * vector.getY());
        double y = (Math.sin(angleInRadian) * vector.getX()) + (Math.cos(angleInRadian) * vector.getY());
        double z = vector.getZ();

        return createVectorOfSameClass(vector, x, y, z);
    }

    private Vector<?> createVectorOfSameClass(Vector<?> originVector, double x, double y, double z) {
        try {
            return originVector.getClass().getConstructor(double.class, double.class, double.class).newInstance(x, y, z);
        } catch (ReflectiveOperationException e) {
            throw new VectorOperationException(e);
        }
    }
}
