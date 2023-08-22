package com.example.shopdemo.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @EmbeddedId
    private CategoryId id;

    private String name;

    public Category(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryId id() {
        return id;
    }

    public String name() {
        return name;
    }

}
