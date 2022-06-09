package com.klimashin.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MathApplicationTest {

    @Test
    void test() {
        assertEquals(2+2, 4);
    }

}