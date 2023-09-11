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

    private boolean hidden;

    public Category(CategoryId id, String name, boolean hidden) {
        this.id = id;
        this.name = name;
        this.hidden = hidden;
    }

    public Category(CategoryId id, String name) {
        this(id, name, false);
    }

    public CategoryId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean hidden() {
        return hidden;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void show() {
        this.hidden = false;
    }

    public void hide() {
        this.hidden = true;
    }

}
