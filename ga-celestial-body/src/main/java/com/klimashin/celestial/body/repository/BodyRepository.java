package com.klimashin.celestial.body.repository;

import com.klimashin.celestial.body.entity.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BodyRepository extends JpaRepository<Body, Integer> {

    List<Body> findAll();

    Optional<Body> findFirstByName(String name);
    
    @Modifying
    @Query("UPDATE Body b SET b.gravRadius = :gravRadius " +
            "WHERE b.name = :name")
    void updateGravRadius(@Param("name") String bodyName,
                          @Param("gravRadius") Double gravRadius);
}
