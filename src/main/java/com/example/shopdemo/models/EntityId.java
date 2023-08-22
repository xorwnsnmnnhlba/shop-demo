package com.example.shopdemo.models;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@MappedSuperclass
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public abstract class EntityId implements Serializable {

    @Column(name = "id")
    private String value;

    protected static String newTsid() {
        return TSID.Factory.getTsid().toString();
    }

    @Override
    public String toString() {
        return value;
    }

}
