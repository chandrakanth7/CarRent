package com.example.carrent;

public class CarRental {
    String fullName;
    String phone;
    String description;
    String location;
    String carModel;

    public CarRental(String fullName, String phone, String description, String location, String carModel) {
        this.fullName = fullName;
        this.phone = phone;
        this.description = description;
        this.location = location;
        this.carModel = carModel;
    }
}
