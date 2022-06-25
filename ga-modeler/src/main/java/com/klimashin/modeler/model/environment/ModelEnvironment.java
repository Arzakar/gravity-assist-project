package com.klimashin.modeler.model.environment;

import com.klimashin.modeler.entity.CelestialBody;
import com.klimashin.modeler.entity.Spacecraft;
import com.klimashin.modeler.util.Stored;

import java.util.List;

public interface ModelEnvironment {

    ModelType getModelType();
    CelestialBody getCentralCelestialBody();
    List<CelestialBody> getCelestialBodies();
    Spacecraft getSpacecraft();

}
