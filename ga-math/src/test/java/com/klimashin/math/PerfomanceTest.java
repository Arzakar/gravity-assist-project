package com.klimashin.math;

import com.klimashin.math.model.math.entity.Acceleration;
import com.klimashin.math.model.math.entity.Speed;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerfomanceTest {

    void test() {
        Speed speed = new Speed();
    }

    void speedPerf() {
        List<Duration> speedDurations = new ArrayList<>();
        List<Duration> accelerationDurations = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            System.out.println("Цикл " + i + " запущен");
            testCycle(speedDurations, accelerationDurations);
        }

        System.out.println("Тест Speed: " + speedDurations);
        System.out.println("Тест Acceleration: " + accelerationDurations);

        double avgSpeed = speedDurations.stream().map(Duration::toMillis).mapToInt(Long::intValue).sum() / speedDurations.size();
        double avgAcceleration = accelerationDurations.stream().map(Duration::toMillis).mapToInt(Long::intValue).sum() / accelerationDurations.size();

        System.out.println("Среднее по Speed: " + avgSpeed);
        System.out.println("Среднее по Acceleration: " + avgAcceleration);

        assertEquals(2+2, 4);
    }

    private void testCycle(List<Duration> speedDurations, List<Duration> accelerationDurations) {
        Speed speed = new Speed(0, 0, 0);
        Acceleration acceleration = new Acceleration(0, 0, 0);

        int maxRepeat = 10000000;
        int fireStart = 10000;

        List<Double> listX = new ArrayList<>();
        List<Double> listY = new ArrayList<>();
        List<Double> listZ = new ArrayList<>();
        for(int i = 0; i < maxRepeat; i++) {
            int signX = Math.random() > 0.5 ? 1 : -1;
            listX.add(Math.random() * 10 * signX);
            int signY = Math.random() > 0.5 ? 1 : -1;
            listY.add(Math.random() * 10 * signY);
            int signZ = Math.random() > 0.5 ? 1 : -1;
            listZ.add(Math.random() * 10 * signZ);
        }

        for(int i = 0; i < fireStart; i++) {
            speed.change(listX.get(i), listY.get(i), listZ.get(i));
        }

        LocalTime startSpeedTestStart = LocalTime.now();
        for(int i = fireStart; i < maxRepeat; i++) {
            speed.change(listX.get(i), listY.get(i), listZ.get(i));
        }
        LocalTime finishTestStart = LocalTime.now();
        Duration speedTestDuration = Duration.between(startSpeedTestStart, finishTestStart);
        speedDurations.add(speedTestDuration);

        for(int i = 0; i < fireStart; i++) {
            acceleration.change(listX.get(i), listY.get(i), listZ.get(i));
        }

        LocalTime accelerationStartTestStart = LocalTime.now();
        for(int i = fireStart; i < maxRepeat; i++) {
            acceleration.change(listX.get(i), listY.get(i), listZ.get(i));
        }
        LocalTime accelerationFinishTestStart = LocalTime.now();
        Duration accelerationTestDuration = Duration.between(accelerationStartTestStart, accelerationFinishTestStart);
        accelerationDurations.add(accelerationTestDuration);
    }
}
