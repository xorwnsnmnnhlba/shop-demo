package com.example.shopdemo.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {

    @EmbeddedId
    private ImageId id;

    private String url;

    public Image(String url) {
        this.id = ImageId.generate();
        this.url = url;
    }

    public Image(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

    public String url() {
        return url;
    }
}
