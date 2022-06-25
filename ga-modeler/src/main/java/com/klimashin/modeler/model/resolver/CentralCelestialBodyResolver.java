package com.klimashin.modeler.model.resolver;

import com.klimashin.math.entity.abstraction.Vector;
import com.klimashin.math.operation.PhysicalOperation;
import com.klimashin.math.operation.PointOperation;
import com.klimashin.math.operation.VectorOperation;
import com.klimashin.modeler.entity.CelestialBody;
import com.klimashin.modeler.entity.Spacecraft;
import com.klimashin.modeler.model.environment.implementation.CentralCelestialBodyModelEnvironment;
import com.klimashin.modeler.model.control.SpacecraftControlLaw;
import com.klimashin.modeler.model.solution.message.SolutionMessage;
import com.klimashin.modeler.model.solution.purpose.SolutionPurpose;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CentralCelestialBodyResolver implements ModelResolver {

    final CentralCelestialBodyModelEnvironment modelEnvironment;
    final CelestialBody centralCelestialBody;
    final List<CelestialBody> celestialBodies;
    final Spacecraft spacecraft;
    final SpacecraftControlLaw spacecraftControlLaw;
    final SolutionPurpose solutionPurpose;

    TerminationReason terminationReason;

    long currentTimestamp = 0L;
    long deltaTime;

    public CentralCelestialBodyResolver(CentralCelestialBodyModelEnvironment modelEnvironment,
                                        SpacecraftControlLaw spacecraftControlLaw,
                                        SolutionPurpose solutionPurpose) {
        this.modelEnvironment = modelEnvironment;
        this.centralCelestialBody = modelEnvironment.getCentralCelestialBody();
        this.celestialBodies = modelEnvironment.getCelestialBodies();
        this.spacecraft = modelEnvironment.getSpacecraft();
        this.spacecraftControlLaw = spacecraftControlLaw;
        this.solutionPurpose = solutionPurpose;
    }

    public SolutionMessage getSolution() {
        TerminationReason terminationReason = startResolveCycle();

        return null;
    }

    private TerminationReason startResolveCycle() {
        while(true) {
            if (solutionPurpose.taskIsSolved()) {
                terminationReason = TerminationReason.FIND_SOLUTION;
                break;
            }

            if (solutionPurpose.taskNeverSolved()) {
                terminationReason = TerminationReason.NO_SOLUTION;
                break;
            }

            stepModel();
        }

        return terminationReason;
    }

    private void stepModel() {
        spacecraft.calculateMoving(VectorOperation.sum(getForceByCentralBody(), getForceByEngine(currentTimestamp)), deltaTime);
        celestialBodies.forEach(celestialBody -> celestialBody.calculateMoving(deltaTime));
    }

    private Vector getForceByCentralBody() {
        double forceByCentralCelestialBody = PhysicalOperation.gravitationForce(centralCelestialBody.getCurrentMass(), spacecraft.getCurrentMass(),
                PointOperation.distanceBetween(spacecraft.getCurrentPosition(), centralCelestialBody.getCurrentPosition())
        );

        return VectorOperation.vectorBetween(spacecraft.getCurrentPosition(), centralCelestialBody.getCurrentPosition())
                .toUnit()
                .mult(forceByCentralCelestialBody);
    }

    private Vector getForceByEngine(long currentTimestamp) {
        return spacecraftControlLaw.getCurrentControlUnit(currentTimestamp)
                .getForceDirection()
                .mult(spacecraft.getSpacecraftThrust());
    }
}
