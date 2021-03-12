package com.dealership.car;

import java.lang.Math;
import java.util.Arrays;

public class car {
    String carModel, carMake;
    int carYear, miles, vin;
    double stickerPrice, monthlyPayment, remainingBalance;
    float[] thisCarsOffers, thisCarsPayments;
    public String username ;

    public car(String carModel, String carMake, int carYear, int miles, int vin, double stickerPrice, double monthlyPayment, double remainingBalance, float[] thisCarsOffers) {
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.miles = miles;
        this.vin = vin;
        this.stickerPrice = stickerPrice;
        this.monthlyPayment = monthlyPayment;
        this.remainingBalance = remainingBalance;
        this.thisCarsOffers = thisCarsOffers;
        this.username = null;

    }

    public car(){
        this.carMake = null;
        this.carModel = null;
        this.vin = 0;
        this.carYear = 0;
        this.miles = 0;
        this.thisCarsOffers = null;
        this.username = null;

    }

    public car(String carModel, String carMake, int carYear, int miles) {
        makeNewVIN();
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.miles = miles;
        this.thisCarsOffers = null;
        this.username = null;

    }

    public car(String carModel, String carMake, int vin, int carYear, int miles) {
        this.vin = vin;
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.miles = miles;
        this.thisCarsOffers = null;
        this.username = null;

    }

    public car(String carModel, String carMake, int carYear, int miles, int vin, double stickerPrice) {
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.miles = miles;
        this.vin = vin;
        this.stickerPrice = stickerPrice;
        this.username = null;

    }



    public double getStickerPrice() {
        return stickerPrice;
    }

    public float[] getThisCarsPayments() {
        return thisCarsPayments;
    }

    public car(String model, String make, int vin, int year, int miles, float[] o){
        this.vin = vin;
        this.carMake = make;
        this.carModel = model;
        this.carYear = year;
        this.miles = miles;
        this.thisCarsOffers = o;
        this.username = null;

    }

    public car(String carModel, String carMake, int carYear, int miles, int vin, double stickerPrice, double monthlyPayment, double remainingBalance, float[] thisCarsOffers, float[] thisCarsPayments, String username) {
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.miles = miles;
        this.vin = vin;
        this.stickerPrice = stickerPrice;
        this.monthlyPayment = monthlyPayment;
        this.remainingBalance = remainingBalance;
        this.thisCarsOffers = thisCarsOffers;
        this.thisCarsPayments = thisCarsPayments;
        this.username = username;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public int getCarYear() {
        return carYear;
    }

    public int getMiles() {
        return miles;
    }

    public int getVIN() {
        return vin;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public String getUsername() {
        return username;
    }

    public float[] getThisCarsOffers() {
        return thisCarsOffers;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return  "VIN: " + vin +
                ", Car Make: " + carMake +
                ", Car Model: " + carModel +
                ", Year: " + carYear +
                ", Miles: " + miles +
                ", Sticker Price: " + stickerPrice +
                ", username: " + username;
    }

    void makeNewVIN(){
        int rand = (int) (Math.random() * 999999999) + 100000000;
        this.vin = rand;
        System.out.println("The computer generated a VIN of " + this.vin + " for this car.");
    }
}
