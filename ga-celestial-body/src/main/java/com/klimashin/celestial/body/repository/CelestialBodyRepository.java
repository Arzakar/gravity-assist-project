package com.klimashin.celestial.body.repository;

import com.klimashin.celestial.body.entity.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CelestialBodyRepository extends JpaRepository<Body, Integer> {

    Optional<Body> findFirstByName(String name);

    Body save(Body body);
}
