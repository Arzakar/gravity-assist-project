package com.klimashin.math.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.klimashin.math.entity.abstraction.Point;
import com.klimashin.math.entity.abstraction.Vector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class VectorOperationTest {

    @ParameterizedTest(name = "[{index}] Вектор = {0}, единичный вектор = {1}")
    @MethodSource("vectorsForUnit")
    @DisplayName("Должен вернуть единичный вектор от переданного")
    void shouldReturnUnit(Vector originVector, Vector expectedResult) {
        Vector actualResult = VectorOperation.getUnit(originVector);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Скалярная величина единичного вектора должна быть равно 1
        assertEquals(1, VectorOperation.getScalar(actualResult), 0.000001);
    }

    private static Stream<Arguments> vectorsForUnit() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), new Vector(0.267261, 0.534522, 0.801784)),
                Arguments.of(new Vector(5, 3, 9), new Vector(0.466252, 0.279751, 0.839254)),
                Arguments.of(new Vector(0, 1, 0), new Vector(0, 1, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, скалярное значение вектора = {1}")
    @MethodSource("vectorsForScalar")
    @DisplayName("Должен вернуть скалярное значение вектора")
    void shouldReturnScalar(Vector vector, double expectedResult) {
        assertEquals(expectedResult, VectorOperation.getScalar(vector), 0.000001);
    }

    private static Stream<Arguments> vectorsForScalar() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), 3.741657),
                Arguments.of(new Vector(5, 3, 9), 10.723805),
                Arguments.of(new Vector(0, 1, 0), 1)
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, прибавляемый вектор = {1}, результат сложения = {2}")
    @MethodSource("vectorsForAdd")
    @DisplayName("Должен вернуть результат сложения векторов, изменив переданный вектор")
    void shouldInternalAdd(Vector originVector, Vector addedVector, Vector expectedResult) {
        Vector actualResult = VectorOperation.internalAdd(originVector, addedVector);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор тоже изменился
        assertEquals(actualResult, originVector);
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, прибавляемый вектор = {1}, результат сложения = {2}")
    @MethodSource("vectorsForAdd")
    @DisplayName("Должен вернуть результат сложения векторов, не изменяя переданный вектор")
    void shouldExternalAdd(Vector originVector, Vector addedVector, Vector expectedResult) {
        Vector actualResult = VectorOperation.externalAdd(originVector, addedVector);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор НЕ изменился
        assertNotEquals(actualResult, originVector);
    }

    private static Stream<Arguments> vectorsForAdd() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), new Vector(3, 2, 1), new Vector(4, 4, 4)),
                Arguments.of(new Vector(5, 3, 9), new Vector(-2, 4, 6.2), new Vector(3, 7, 15.2)),
                Arguments.of(new Vector(0, 1, 0), new Vector(0, 0, 1), new Vector(0, 1, 1))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, вычитаемый вектор = {1}, результат вычитания = {2}")
    @MethodSource("vectorsForSub")
    @DisplayName("Должен вернуть результат вычитания векторов, изменив переданный вектор")
    void shouldInternalSub(Vector originVector, Vector subtractedVector, Vector expectedResult) {
        Vector actualResult = VectorOperation.internalSub(originVector, subtractedVector);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор тоже изменился
        assertEquals(actualResult, originVector);
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, вычитаемый вектор = {1}, результат вычитания = {2}")
    @MethodSource("vectorsForSub")
    @DisplayName("Должен вернуть результат вычитания векторов, не изменяя переданный вектор")
    void shouldExternalSub(Vector originVector, Vector subtractedVector, Vector expectedResult) {
        Vector actualResult = VectorOperation.externalSub(originVector, subtractedVector);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор НЕ изменился
        assertNotEquals(actualResult, originVector);
    }

    private static Stream<Arguments> vectorsForSub() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), new Vector(3, 2, 1), new Vector(-2, 0, 2)),
                Arguments.of(new Vector(5, 3, 9), new Vector(-2, 4, 6.2), new Vector(7, -1, 2.8)),
                Arguments.of(new Vector(0, 1, 0), new Vector(0, 0, 1), new Vector(0, 1, -1))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, множитель = {1}, результат умножения = {2}")
    @MethodSource("vectorsForMult")
    @DisplayName("Должен вернуть результат умножения вектора на число, изменив переданный вектор")
    void shouldInternalMult(Vector originVector, double ratio, Vector expectedResult) {
        Vector actualResult = VectorOperation.internalMult(originVector, ratio);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор тоже изменился
        assertEquals(actualResult, originVector);
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, множитель= {1}, результат умножения = {2}")
    @MethodSource("vectorsForMult")
    @DisplayName("Должен вернуть результат умножения вектора на число, не изменяя переданный вектор")
    void shouldExternalMult(Vector originVector, double ratio, Vector expectedResult) {
        Vector actualResult = VectorOperation.externalMult(originVector, ratio);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор НЕ изменился
        assertNotEquals(actualResult, originVector);
    }

    private static Stream<Arguments> vectorsForMult() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), 2, new Vector(2, 4, 6)),
                Arguments.of(new Vector(5, 3, 9), -3, new Vector(-15, -9, -27)),
                Arguments.of(new Vector(0, 1, 0), 2.5, new Vector(0, 2.5, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, делитель = {1}, результат деления = {2}")
    @MethodSource("vectorsForDiv")
    @DisplayName("Должен вернуть результат деления вектора на число, изменив переданный вектор")
    void shouldInternalDiv(Vector originVector, double ratio, Vector expectedResult) {
        Vector actualResult = VectorOperation.internalDiv(originVector, ratio);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор тоже изменился
        assertEquals(actualResult, originVector);
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, делитель= {1}, результат деления = {2}")
    @MethodSource("vectorsForDiv")
    @DisplayName("Должен вернуть результат деления вектора на число, не изменяя переданный вектор")
    void shouldExternalDiv(Vector originVector, double ratio, Vector expectedResult) {
        Vector actualResult = VectorOperation.externalDiv(originVector, ratio);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор НЕ изменился
        assertNotEquals(actualResult, originVector);
    }

    private static Stream<Arguments> vectorsForDiv() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), 2, new Vector(0.5, 1, 1.5)),
                Arguments.of(new Vector(5, 3, 9), -3, new Vector(-1.666667, -1, -3)),
                Arguments.of(new Vector(0, 1, 0), 2.5, new Vector(0, 0.4, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Массив векторов = [{0}, {1}] , сумма векторов = {2}")
    @MethodSource("vectorsForSum")
    @DisplayName("Должен вернуть сумму векторов")
    void shouldReturnSum(Vector firstVector, Vector secondVector, Vector expectedResult) {
        Vector actualResult = VectorOperation.sum(firstVector, secondVector);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> vectorsForSum() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), new Vector(3, 2, 1), new Vector(4, 4, 4)),
                Arguments.of(new Vector(5, 3, 9), new Vector(-2, 4, 6.2), new Vector(3, 7, 15.2)),
                Arguments.of(new Vector(0, 1, 0), new Vector(0, 0, 1), new Vector(0, 1, 1))
        );
    }

    @ParameterizedTest(name = "[{index}] Первый вектор = {0}, второй вектор = {1}, результат = {2}")
    @MethodSource("vectorsForCrossProduct")
    @DisplayName("Должен вернуть векторное произведение")
    void shouldReturnCrossProduct(Vector firstVector, Vector secondVector, Vector expectedResult) {
        assertEquals(expectedResult, VectorOperation.crossProduct(firstVector, secondVector));
    }

    private static Stream<Arguments> vectorsForCrossProduct() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), new Vector(3, 2, 1), new Vector(-4, 8, -4)),
                Arguments.of(new Vector(5, 3, 9), new Vector(-2, 4, 6.2), new Vector(-17.4, -49, 26)),
                Arguments.of(new Vector(0, 1, 0), new Vector(0, 0, 1), new Vector(1, 0, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, угол поворота = {1}, результат поворота = {2}")
    @MethodSource("vectorsForRotateByZ")
    @DisplayName("Должен вернуть повёрнутый вектор, изменив переданный вектор")
    void shouldInternalRotateByZ(Vector originVector, double angleInRadian, Vector expectedResult) {
        Vector actualResult = VectorOperation.internalRotateByZ(originVector, angleInRadian);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор тоже изменился
        assertEquals(actualResult, originVector);
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, угол поворота = {1}, результат поворота = {2}")
    @MethodSource("vectorsForRotateByZ")
    @DisplayName("Должен вернуть повёрнутый вектор, не изменяя переданный вектор")
    void shouldExternalRotateByZ(Vector originVector, double angleInRadian, Vector expectedResult) {
        Vector actualResult = VectorOperation.externalRotateByZ(originVector, angleInRadian);

        assertionVectorAbsoluteValues(expectedResult, actualResult);

        // Проверка, что переданный вектор НЕ изменился
        assertNotEquals(actualResult, originVector);
    }

    private static Stream<Arguments> vectorsForRotateByZ() {
        return Stream.of(
                Arguments.of(new Vector(1, 2, 3), 2, new Vector(-2.234742, 0.077004, 3)),
                Arguments.of(new Vector(5, 3, 9), 1.2 * Math.PI, new Vector(-2.281729, -5.365977, 9)),
                Arguments.of(new Vector(0, 1, 0), -1 * Math.PI, new Vector(0, -1, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Начальная точка = {0}, конечная точка = {1}, ожидаемый вектор = {2}")
    @MethodSource("pointsPairs")
    @DisplayName("Должен создать вектор между двумя точками")
    void shouldReturnVectorBetween(Point pointFrom, Point pointTo, Vector expectedResult) {
        Vector actualResult = VectorOperation.vectorBetween(pointFrom, pointTo);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> pointsPairs() {
        return Stream.of(
                Arguments.of(new Point(0, 0, 0), new Point(5, 10, 15),
                        new Vector(5, 10, 15, new Point(0, 0, 0), new Point(5, 10, 15))),
                Arguments.of(new Point(2, 5, 3), new Point(-3, 15, 3),
                        new Vector(-5, 10, 0, new Point(2, 5, 3), new Point(-3, 15, 3))),
                Arguments.of(new Point(0, 1, -10), new Point(0, -6, 19),
                        new Vector(0, -7, 29, new Point(0, 1, -10), new Point(0, -6, 19)))
        );
    }

    private void assertionVectorAbsoluteValues(Vector expectedResult, Vector actualResult) {
        assertEquals(expectedResult.getX(), actualResult.getX(), 0.000001);
        assertEquals(expectedResult.getY(), actualResult.getY(), 0.000001);
        assertEquals(expectedResult.getZ(), actualResult.getZ(), 0.000001);
    }
}