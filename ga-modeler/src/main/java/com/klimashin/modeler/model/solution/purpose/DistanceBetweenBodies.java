package com.klimashin.modeler.model.solution.purpose;

import com.klimashin.math.operation.PointOperation;
import com.klimashin.modeler.entity.MaterialPoint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistanceBetweenBodies implements SolutionPurpose {

    MaterialPoint firstBody;
    MaterialPoint secondBody;
    double requiredDistance;

    @Override
    public boolean taskIsSolved() {
        return PointOperation.distanceBetween(firstBody.getCurrentPosition(), secondBody.getCurrentPosition()) <= requiredDistance;
    }

    @Override
    public boolean taskNeverSolved() {
        return false;
    }
}
