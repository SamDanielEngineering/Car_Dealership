package com.dealership.car;

public class car {
    String carModel, carMake;
    int carYear;
    car[] inventory = new car[8];

    public car(){

    }

    public car(String model, String make, int year){
        this.carMake = make;
        this.carModel = model;
        this.carYear = year;
    }


}
