package com.govehicle.payload.vehicles;

import java.io.InputStream;

public class Brands {

    private String path;
    private InputStream image;

    public Brands(InputStream image, String path){
        this.image = image;
        this.path = path;
    }




}
