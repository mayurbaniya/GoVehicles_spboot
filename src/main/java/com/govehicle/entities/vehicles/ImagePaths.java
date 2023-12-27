package com.govehicle.entities.vehicles;

import jakarta.persistence.*;

@Entity
public class ImagePaths {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;

    public ImagePaths(){

    }

    public ImagePaths(int id, String path, Specifications specifications) {
        this.id = id;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImagePaths{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
