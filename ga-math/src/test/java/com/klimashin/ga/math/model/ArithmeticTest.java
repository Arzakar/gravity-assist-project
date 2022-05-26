package com.klimashin.ga.math.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.klimashin.ga.math.model.abstraction.Vector;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ArithmeticTest {

    static class TestVector extends Vector {
        public TestVector(float x, float y, float z) {
            super(x, y, z);
        }
    }

    private static Stream<Arguments> getModuleData() {
        return Stream.of(
                Arguments.of(new TestVector(1, 1, 1), 1.732050f),
                Arguments.of(new TestVector(2, 3, 4), 5.385164f),
                Arguments.of(new TestVector(1.41f, 15.12f, 100.124f), 101.269037f)
        );
    }

    @ParameterizedTest
    @MethodSource("getModuleData")
    void getModule(Vector vector, float expectedResult) {
        assertEquals(vector.getModule(), expectedResult, 0.000001);
    }
}