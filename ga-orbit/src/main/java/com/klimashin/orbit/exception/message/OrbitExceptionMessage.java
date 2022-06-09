package com.klimashin.orbit.exception.message;

public interface OrbitExceptionMessage {

    String CIRCULAR_ORBIT_EXCEPTION_MESSAGE = "Для круговой орбиты эксцентриситет должен быть равным 0. " +
            "Актуальное значение: %s";
    String ELLIPTIC_ORBIT_EXCEPTION_MESSAGE = "Для эллиптической орбиты эксцентриситет должен быть больше 0 и меньше 1. " +
            "Актуальное значение :%s";
    String PARABOLIC_ORBIT_EXCEPTION_MESSAGE = "Для параболической орбиты эксцентриситет должен быть равным 1. " +
            "Актуальное значение :%s";
    String HYPERBOLIC_ORBIT_EXCEPTION_MESSAGE = "Для гиперболической орбиты эксцентриситет должен быть больше 1. " +
            "Актуальное значение :%s";
}
