package com.klimashin.math.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.klimashin.math.entity.Position;
import com.klimashin.math.entity.UtilPoint;
import com.klimashin.math.entity.abstraction.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PointOperationTest {

    @ParameterizedTest(name = "[{index}] Первая точка = {0}, вторая точка = {1}, ожидаемое расстояние между ними = {2} м")
    @MethodSource("pointPairs")
    @DisplayName("Должен вернуть расстояние между двумя точками")
    void shouldReturnDistanceBetween(Point<?> firstPoint, Point<?> secondPoint, double expectedResult) {
        assertEquals(expectedResult, PointOperation.distanceBetween(firstPoint, secondPoint), 0.000001);
    }

    private static Stream<Arguments> pointPairs() {
        return Stream.of(
                Arguments.of(new Position(1, 2, 3), new Position(3, 2 ,1), 2.828427),
                Arguments.of(new UtilPoint(5, 3, 9), new UtilPoint(-2, 4, 6.2), 7.605261),
                Arguments.of(new Position(0, 1, 0), new Position(0, 0, 1), 1.414214)
        );
    }
}