package com.klimashin.modeler.model;

import com.klimashin.modeler.model.environment.ModelEnvironment;
import com.klimashin.modeler.model.control.SpacecraftControlLaw;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionProcessor {

    ModelEnvironment environment;
    List<Double> spacecraftStartAngularPositions;
    List<Double> earthStartAngularPositions;
    SpacecraftControlLaw spacecraftControlLaw;

    Queue<Double> earthStartPositionsQueue;
    Queue<Double> spacecraftStartPositionsQueue;

    void prepare() {
        earthStartPositionsQueue = new LinkedList<>(earthStartAngularPositions.stream().sorted(Double::compareTo).toList());
        spacecraftStartPositionsQueue = new LinkedList<>(spacecraftStartAngularPositions.stream().sorted(Double::compareTo).toList());
        while(!earthStartPositionsQueue.isEmpty()) {
            while (!spacecraftStartPositionsQueue.isEmpty()) {

            }
        }
    }
}
