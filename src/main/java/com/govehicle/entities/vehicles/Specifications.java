package com.govehicle.entities.vehicles;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Specifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String price;
    private String displacement;
    private String maxPower;
    private String minTorque;
    private String gears;
    private String mileage;
    private String frontBrakeType;
    private String rearBrakeType;
    private String wheelType;
    private String topSpeed;
    private String tyreType;
    private String fuelTankCapacity;
    private String warranty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "specs_id", referencedColumnName = "id")
    private List<ImagePaths> images;

    public Specifications(int id,String price, String name, String displacement, String maxPower, String minTorque, String gears, String mileage, String frontBrakeType, String rearBrakeType, String wheelType, String topSpeed, String tyreType, String fuelTankCapacity, String warranty, Brand brand, List<ImagePaths> images) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.displacement = displacement;
        this.maxPower = maxPower;
        this.minTorque = minTorque;
        this.gears = gears;
        this.mileage = mileage;
        this.frontBrakeType = frontBrakeType;
        this.rearBrakeType = rearBrakeType;
        this.wheelType = wheelType;
        this.topSpeed = topSpeed;
        this.tyreType = tyreType;
        this.fuelTankCapacity = fuelTankCapacity;
        this.warranty = warranty;
        this.brand = brand;
        this.images = images;
    }

    @Override
    public String toString() {
        return "Specifications{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displacement='" + displacement + '\'' +
                ", maxPower='" + maxPower + '\'' +
                ", minTorque='" + minTorque + '\'' +
                ", gears='" + gears + '\'' +
                ", mileage='" + mileage + '\'' +
                ", frontBrakeType='" + frontBrakeType + '\'' +
                ", rearBrakeType='" + rearBrakeType + '\'' +
                ", wheelType='" + wheelType + '\'' +
                ", topSpeed='" + topSpeed + '\'' +
                ", tyreType='" + tyreType + '\'' +
                ", fuelTankCapacity='" + fuelTankCapacity + '\'' +
                ", warranty='" + warranty + '\'' +
                ", brand=" + brand +
                ", images=" + images +
                ", price=" + price +
                '}';
    }

    public Specifications() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }

    public String getMinTorque() {
        return minTorque;
    }

    public void setMinTorque(String minTorque) {
        this.minTorque = minTorque;
    }

    public String getGears() {
        return gears;
    }

    public void setGears(String gears) {
        this.gears = gears;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getFrontBrakeType() {
        return frontBrakeType;
    }

    public void setFrontBrakeType(String frontBrakeType) {
        this.frontBrakeType = frontBrakeType;
    }

    public String getRearBrakeType() {
        return rearBrakeType;
    }

    public void setRearBrakeType(String rearBrakeType) {
        this.rearBrakeType = rearBrakeType;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getTyreType() {
        return tyreType;
    }

    public void setTyreType(String tyreType) {
        this.tyreType = tyreType;
    }

    public String getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(String fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public List<ImagePaths> getImages() {
        return images;
    }

    public void setImages(List<ImagePaths> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }


}
