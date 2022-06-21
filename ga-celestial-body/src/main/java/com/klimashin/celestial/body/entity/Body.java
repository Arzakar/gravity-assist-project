package com.klimashin.celestial.body.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "body")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "mass", nullable = false)
    Double mass;

    @Column(name = "radius")
    Double radius;

    @Column(name = "grav_parameter", nullable = false)
    Double gravParameter;

    @Column(name = "grav_radius")
    Double gravRadius;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Body body = (Body) o;
        return id != null && Objects.equals(id, body.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}