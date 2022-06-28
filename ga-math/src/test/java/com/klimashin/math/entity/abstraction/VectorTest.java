package com.klimashin.math.entity.abstraction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VectorTest {

    @ParameterizedTest(name = "[{index}] Начальные значения XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен создать вектор с пустыми начальной и конечной точками")
    void shouldCreateVectorByAbsoluteValues(double x, double y, double z) {
        Vector actualResult = new Vector(x, y, z);
        Vector expectedResult = new Vector().setX(x).setY(y).setZ(z);

        assertNull(actualResult.getFirstPoint());
        assertNull(actualResult.getSecondPoint());
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "[{index}] Начальная точка = {0}, Конечная точка = {1}")
    @MethodSource("pointsPairsForConstructor")
    @DisplayName("Должен создать вектор и заполнить абсолютные значения XYZ")
    void shouldCreateVectorByPoints(Point firstPoint, Point secondPoint, Vector expectedResult) {
        Vector actualResult = new Vector(firstPoint, secondPoint);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> pointsPairsForConstructor() {
        return Stream.of(
                Arguments.of(new Point(0, 0, 0), new Point(5, 10, 15),
                        new Vector(5, 10, 15, new Point(0, 0, 0), new Point(5, 10, 15))),
                Arguments.of(new Point(2, 5, 3), new Point(-3, 15, 3),
                        new Vector(-5, 10, 0, new Point(2, 5, 3), new Point(-3, 15, 3))),
                Arguments.of(new Point(0, 1, -10), new Point(0, -6, 19),
                        new Vector(0, -7, 29, new Point(0, 1, -10), new Point(0, -6, 19)))
        );
    }

    @ParameterizedTest(name = "[{index}] Начальная точка = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен задать значение начальной точки, и если есть конечная точка, то рассчитать координаты вектора")
    void shouldSetFirstPoint(double x, double y, double z) {
        Point firstPoint = new Point(x, y, z);
        Point secondPoint = new Point(x + 10, y + 10, z + 10);

        Vector originVectorWithoutSecondPoint = new Vector();
        Vector expectedVectorWithoutSecondPoint = new Vector(0, 0, 0, firstPoint, null);

        assertEquals(expectedVectorWithoutSecondPoint, originVectorWithoutSecondPoint.setFirstPoint(firstPoint));

        Vector originSpeedWithSecondPoint = new Vector().setSecondPoint(secondPoint);
        Vector expectedSpeedWithSecondPoint = new Vector(10, 10, 10, firstPoint, secondPoint);

        assertEquals(expectedSpeedWithSecondPoint, originSpeedWithSecondPoint.setFirstPoint(firstPoint));
    }

    @ParameterizedTest(name = "[{index}] Конечная точка = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен задать значение конечной точки, и если есть начальная точка, то рассчитать координаты вектора")
    void shouldSetSecondPoint(double x, double y, double z) {
        Point secondPoint = new Point(x, y, z);
        Point firstPoint = new Point(x - 10, y - 10, z - 10);

        Vector originSpeedWithoutFirstPoint = new Vector();
        Vector expectedSpeedWithoutFirstPoint = new Vector(0, 0, 0, null, secondPoint);

        assertEquals(expectedSpeedWithoutFirstPoint, originSpeedWithoutFirstPoint.setSecondPoint(secondPoint));

        Vector originSpeedWithFirstPoint = new Vector().setFirstPoint(firstPoint);
        Vector expectedSpeedWithFirstPoint = new Vector(10, 10, 10, firstPoint, secondPoint);

        assertEquals(expectedSpeedWithFirstPoint, originSpeedWithFirstPoint.setSecondPoint(secondPoint));
    }

    @ParameterizedTest(name = "[{index}] Дельты XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен изменить все координаты скорости, без изменения начальной и конечной точек, т.к. их нет")
    void shouldChangeOnlyAbsoluteValues(double deltaX, double deltaY, double deltaZ) {
        Vector originSpeedWithoutPoints = new Vector(10, 5, 25);
        Vector expectedResultWithoutPoints = new Vector(10 + deltaX, 5 + deltaY, 25 + deltaZ);
        assertEquals(expectedResultWithoutPoints, originSpeedWithoutPoints.change(deltaX, deltaY, deltaZ));

        Vector originSpeedWithFirstPoint = new Vector(10, 5, 25, new Point(0, 0, 0), null);
        Vector expectedResultWithFirstPoint = new Vector(10 + deltaX, 5 + deltaY, 25 + deltaZ, new Point(0, 0, 0), null);
        assertEquals(expectedResultWithFirstPoint, originSpeedWithFirstPoint.change(deltaX, deltaY, deltaZ));

        Vector originSpeedWithSecondPoint = new Vector(10, 5, 25, null, new Point(0, 0, 0));
        Vector expectedResultWithSecondPoint = new Vector(10 + deltaX, 5 + deltaY, 25 + deltaZ, null, new Point(0, 0, 0));
        assertEquals(expectedResultWithSecondPoint, originSpeedWithSecondPoint.change(deltaX, deltaY, deltaZ));
    }

    @ParameterizedTest(name = "[{index}] Дельты XYZ = [{0}, {1}, {2}]")
    @MethodSource("valuesXYZ")
    @DisplayName("Должен изменить все координаты скорости и скорректировать вторую точку")
    void shouldChangeWithSecondPoint(double deltaX, double deltaY, double deltaZ) {
        Vector originSpeed = new Vector(10, 5, 25,
                new Point(0, 0, 0),
                new Point(10, 5, 25));

        Vector expectedResult = new Vector(10 + deltaX, 5 + deltaY, 25 + deltaZ,
                new Point(0, 0, 0),
                new Point(10 + deltaX, 5 + deltaY, 25 + deltaZ));

        assertEquals(expectedResult, originSpeed.change(deltaX, deltaY, deltaZ));
    }

    private static Stream<Arguments> valuesXYZ() {
        return Stream.of(
                Arguments.of(5, 10, 15),
                Arguments.of(-5, 10, 0),
                Arguments.of(0, -7, 29)
        );
    }

}