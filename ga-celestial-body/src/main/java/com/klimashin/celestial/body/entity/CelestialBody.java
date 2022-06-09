package com.klimashin.celestial.body.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "celestial_body")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CelestialBody {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false, length = 50)
    @NotBlank
    String name;

    @Column(name = "mass", nullable = false)
    @Positive
    Double mass;

    @Column(name = "radius")
    @Positive
    Double radius;

    @Column(name = "grav_parameter", nullable = false)
    @Positive
    Double gravParameter;

    @Column(name = "grav_radius")
    @Positive
    Double gravRadius;
}