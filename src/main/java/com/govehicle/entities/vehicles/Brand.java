package com.govehicle.entities.vehicles;

import jakarta.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brandName;
    private String brandImage;

//    @OneToOne(mappedBy = "brand")
//    private Specifications specifications;
    public Brand(){

    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", brandImage='" + brandImage + '\'' +
                '}';
    }

    public Brand(int id, String brandName, String brandImage) {
        this.id = id;
        this.brandName = brandName;
        this.brandImage = brandImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }
}
