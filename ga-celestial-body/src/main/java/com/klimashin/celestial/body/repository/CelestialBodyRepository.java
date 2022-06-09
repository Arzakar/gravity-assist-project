package com.klimashin.celestial.body.repository;

import com.klimashin.celestial.body.entity.CelestialBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CelestialBodyRepository extends JpaRepository<CelestialBody, Integer> {

    Optional<CelestialBody> findFirstByName(String name);
}
