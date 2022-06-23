package com.klimashin.math.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.klimashin.math.entity.Force;
import com.klimashin.math.entity.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ForceTest {

    @ParameterizedTest(name = "[{index}] Начальные значения XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен создать вектор силы с пустыми начальной и конечной точками")
    void shouldCreateForceByAbsoluteValues(double x, double y, double z) {
        Force actualResult = new Force(x, y, z);
        Force expectedResult = new Force().setX(x).setY(y).setZ(z);

        assertNull(actualResult.getFirstPoint());
        assertNull(actualResult.getSecondPoint());
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "[{index}] Начальная точка = {0}, Конечная точка = {1}")
    @MethodSource("firstAndSecondPoints")
    @DisplayName("Должен создать вектор силы и заполнить абсолютные значения XYZ")
    void shouldCreateForceByPoints(Position firstPoint, Position secondPoint) {
        Force actualResult = new Force(firstPoint, secondPoint);
        Force expectedResult = new Force(secondPoint.getX() - firstPoint.getX(),
                secondPoint.getY() - firstPoint.getY(),
                secondPoint.getZ() - firstPoint.getZ(),
                firstPoint,
                secondPoint);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> valuesXYZ() {
        return Stream.of(
                Arguments.of(5, 10, 15),
                Arguments.of(-5, 10, 0),
                Arguments.of(0, -7, 29)
        );
    }

    private static Stream<Arguments> firstAndSecondPoints() {
        return Stream.of(
                Arguments.of(new Position(0, 0, 0), new Position(5, 10, 15)),
                Arguments.of(new Position(2, 5, 3), new Position(-3, 15, 3)),
                Arguments.of(new Position(0, 1, -10), new Position(0, -6, 19))
        );
    }

}