package com.klimashin.modeler.model.environment.implementation;

import com.klimashin.modeler.entity.CelestialBody;
import com.klimashin.modeler.entity.Spacecraft;

import com.klimashin.modeler.model.environment.ModelEnvironment;
import com.klimashin.modeler.model.environment.ModelType;
import com.klimashin.modeler.model.environment.data.CentralCelestialBodyModelEnvironmentData;
import com.klimashin.modeler.util.Stored;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CentralCelestialBodyModelEnvironment implements ModelEnvironment, Stored<CentralCelestialBodyModelEnvironmentData> {

    final ModelType modelType;

    final CelestialBody centralCelestialBody;
    final List<CelestialBody> celestialBodies;

    final Spacecraft spacecraft;

    final LocalDateTime startTimestamp;

    public CentralCelestialBodyModelEnvironment(CelestialBody centralCelestialBody,
                                                List<CelestialBody> celestialBodies,
                                                Spacecraft spacecraft) {

        /** TODO: добавить валидацию входных параметров
         * все тела из списка имеют различные имена
         * тела не пересекаются друг с другом в начальном положении (радиус одного тела + радиус другого тела < дистанция между центрами масс)
         * космический аппарат находится за пределами радиусов любого небесного тела
         */
        this.modelType = ModelType.CENTRAL_CELESTIAL_BODY;
        this.centralCelestialBody = centralCelestialBody;
        this.celestialBodies = celestialBodies;
        this.spacecraft = spacecraft;
        this.startTimestamp = LocalDateTime.now();
    }


    @Override
    public CentralCelestialBodyModelEnvironmentData getData() {
        return null;
    }
}
