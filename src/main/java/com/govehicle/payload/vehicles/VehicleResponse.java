package com.govehicle.payload.vehicles;

public class VehicleResponse {

    private String imageName;
    private String message;

    public VehicleResponse(String imageName, String message) {
        this.imageName = imageName;
        this.message = message;
    }

    public VehicleResponse() {
        
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
