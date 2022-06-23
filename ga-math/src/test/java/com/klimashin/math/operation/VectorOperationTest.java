package com.klimashin.math.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.klimashin.math.entity.Acceleration;
import com.klimashin.math.entity.Force;
import com.klimashin.math.entity.Speed;
import com.klimashin.math.entity.UtilVector;
import com.klimashin.math.entity.abstraction.Vector;

import com.klimashin.math.exception.VectorOperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class VectorOperationTest {

    @ParameterizedTest(name = "[{index}] Вектор = {0}, единичный вектор = {1}")
    @MethodSource("vectorsForUnit")
    @DisplayName("Должен вернуть единичный вектор от переданного")
    void shouldReturnUnit(Vector<?> originVector, Vector<?> expectedResult) {
        Vector<?> actualResult = VectorOperation.getUnit(originVector);

        assertEquals(originVector.getClass(), actualResult.getClass());
        assertEquals(expectedResult.getX(), actualResult.getX(), 0.000001);
        assertEquals(expectedResult.getY(), actualResult.getY(), 0.000001);
        assertEquals(expectedResult.getZ(), actualResult.getZ(), 0.000001);

        assertEquals(1, VectorOperation.getScalar(actualResult), 0.000001);
    }

    private static Stream<Arguments> vectorsForUnit() {
        return Stream.of(
                Arguments.of(new Speed(1, 2, 3), new Speed(0.267261, 0.534522, 0.801784)),
                Arguments.of(new Acceleration(5, 3, 9), new Acceleration(0.466252, 0.279751, 0.839254)),
                Arguments.of(new Force(0, 1, 0), new Force(0, 1, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, скалярное значение вектора = {1}")
    @MethodSource("vectorsForScalar")
    @DisplayName("Должен вернуть скалярное значение вектора")
    void shouldReturnScalar(Vector<?> vector, double expectedResult) {
        assertEquals(expectedResult, VectorOperation.getScalar(vector), 0.000001);
    }

    private static Stream<Arguments> vectorsForScalar() {
        return Stream.of(
                Arguments.of(new Speed(1, 2, 3), 3.741657),
                Arguments.of(new Acceleration(5, 3, 9), 10.723805),
                Arguments.of(new Force(0, 1, 0), 1)
        );
    }

    @ParameterizedTest(name = "[{index}] Массив векторов = [{0}, {1}] , сумма векторов = {2}")
    @MethodSource("vectorsForSum")
    @DisplayName("Должен вернуть сумму векторов")
    void shouldReturnSum(Vector<?> firstVector, Vector<?> secondVector, Vector<?> expectedResult) {
        Vector<?> actualResult = VectorOperation.sum(firstVector, secondVector);

        assertEquals(expectedResult.getClass(), actualResult.getClass());
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> vectorsForSum() {
        return Stream.of(
                Arguments.of(new Speed(1, 2, 3), new Speed(3, 2, 1), new Speed(4, 4, 4)),
                Arguments.of(new Acceleration(5, 3, 9), new Acceleration(-2, 4, 6.2), new Acceleration(3, 7, 15.2)),
                Arguments.of(new Force(0, 1, 0), new Force(0, 0, 1), new Force(0, 1, 1))
        );
    }

    @ParameterizedTest(name = "[{index}] Первый вектор = {0}, второй вектор = {1}, результат = {2}")
    @MethodSource("vectorsForCrossProduct")
    @DisplayName("Должен вернуть векторное произведение")
    void shouldReturnCrossProduct(Vector<?> firstVector, Vector<?> secondVector, UtilVector expectedResult) {
        assertEquals(expectedResult, VectorOperation.crossProduct(firstVector, secondVector));
    }

    private static Stream<Arguments> vectorsForCrossProduct() {
        return Stream.of(
                Arguments.of(new Speed(1, 2, 3), new Speed(3, 2, 1), new UtilVector(-4, 8, -4)),
                Arguments.of(new Acceleration(5, 3, 9), new Acceleration(-2, 4, 6.2), new UtilVector(-17.4, -49, 26)),
                Arguments.of(new Force(0, 1, 0), new Force(0, 0, 1), new UtilVector(1, 0, 0))
        );
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, угол поворота = {1}, результат поворота = {2}")
    @MethodSource("vectorsForRotateByZ")
    @DisplayName("Должен вернуть повёрнутый вектор, изменив переданный вектор")
    void shouldInternalRotateByZ(Vector<?> vector, double angleInRadian, Vector<?> expectedResult) {
        Vector<?> actualResult = VectorOperation.internalRotateByZ(vector, angleInRadian);

        assertEquals(vector.getClass(), actualResult.getClass());
        assertEquals(expectedResult.getX(), actualResult.getX(), 0.000001);
        assertEquals(expectedResult.getY(), actualResult.getY(), 0.000001);
        assertEquals(expectedResult.getZ(), actualResult.getZ(), 0.000001);

        assertEquals(actualResult.getX(), vector.getX());
        assertEquals(actualResult.getY(), vector.getY());
        assertEquals(actualResult.getZ(), vector.getZ());
    }

    @ParameterizedTest(name = "[{index}] Вектор = {0}, угол поворота = {1}, результат поворота = {2}")
    @MethodSource("vectorsForRotateByZ")
    @DisplayName("Должен вернуть повёрнутый вектор, не изменяя переданный вектор")
    void shouldExternalRotateByZ(Vector<?> vector, double angleInRadian, Vector<?> expectedResult) {
        Vector<?> actualResult = VectorOperation.externalRotateByZ(vector, angleInRadian);

        assertEquals(vector.getClass(), actualResult.getClass());
        assertEquals(expectedResult.getX(), actualResult.getX(), 0.000001);
        assertEquals(expectedResult.getY(), actualResult.getY(), 0.000001);
        assertEquals(expectedResult.getZ(), actualResult.getZ(), 0.000001);

        assertNotEquals(actualResult, vector);
    }

    private static Stream<Arguments> vectorsForRotateByZ() {
        return Stream.of(
                Arguments.of(new Speed(1, 2, 3), 2, new Speed(-2.234742, 0.077004, 3)),
                Arguments.of(new Acceleration(5, 3, 9), 1.2 * Math.PI, new Acceleration(-2.281729, -5.365977, 9)),
                Arguments.of(new Force(0, 1, 0), -1 * Math.PI, new Force(0, -1, 0))
        );
    }

    @Test
    @DisplayName("Должен выбросить ошибку, т.к. в массиве на суммирование разные типы предков класса Vector")
    void shouldThrowVectorOperationExceptionWhileSum() {
        VectorOperationException thrown = assertThrows(VectorOperationException.class, () ->
                VectorOperation.sum(new Speed(1, 1, 1), new Acceleration(1, 1, 1)));

        assertEquals("Векторы в массиве имеют разный тип", thrown.getMessage());
    }

    @Test
    @DisplayName("Должен выбросить ошибку, т.к. в классе наследнике отсутствует необходимый конструктор")
    void shouldThrowVectorOperationExceptionCauseGenerateReflectiveOperationException() {
        class TestVector extends Vector<TestVector> {
        }

        assertThrows(VectorOperationException.class, () -> VectorOperation.getUnit(new TestVector().setX(1).setY(1).setZ(1)));
        assertThrows(VectorOperationException.class, () -> VectorOperation.sum(new TestVector().setX(1).setY(1).setZ(1)));
        assertThrows(VectorOperationException.class, () -> VectorOperation.externalRotateByZ(new TestVector().setX(1).setY(1).setZ(1), 2));
    }
}