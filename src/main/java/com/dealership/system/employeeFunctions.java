package com.dealership.system;

import com.dealership.car.car;
import com.dealership.car.offer;

import java.util.Random;
import java.util.Scanner;

public class employeeFunctions {
    static Scanner in = new Scanner(System.in);


    public static car makeNewCar(){
        String carModel, carMake;
        int carYear, miles, vin;
        double stickerPrice;

        System.out.println("Who is the manufacturer of the car:");
        carMake = in.nextLine();
        System.out.println("What is the car model:");
        carModel = in.nextLine();
        System.out.println("What year is the car:");
        carYear = in.nextInt();
        System.out.println("How many miles are on the car:");
        miles = in.nextInt();
        System.out.println("What is the sticker price:");
        stickerPrice = in.nextInt();


        car[] list = customerFunctions.carInventory();
        int num = 0;
        boolean forNow = false;
        while (forNow == false){
            forNow = true;
            num = (int)(Math.random()* 1001) + 1;
            for (int i = 0; i < list.length; i++){
                if (list[i].getVIN() == num){
                    forNow = false;
                }
            }
        }


        vin = (int)(Math.random()* 1001) + 1;

        car newCar = new car(carModel, carMake, carYear, miles, vin, stickerPrice);

        return newCar;
    }


    public static boolean addToLot(){
        car c = makeNewCar();
        String sqlStatementCarLot = "INSERT INTO CarLot (Make, Model, Year, Miles, VIN) VALUES (" + c.getCarMake() + ", " +
                c.getCarModel() + ", " + c.getCarYear() + ", " + c.getVIN()+ ", " + c.getMiles() + ");";

        String sqlStatementOffers = "INSERT INTO OFFERS (Offer, VIN) VALUES (" + c.getThisCarsOffers() + ", " + c.getVIN() + ");";


        return false;
    }

    //  return the entire inventory of cars that have offers on them
    public static car[] carsWithOffers(car[] inventory){
        String sqlStatement = "SELECT * FROM CarLot WHERE offer IS NOT NULL;";
        car[] inventoryWithOffers = new car[0];
        return inventoryWithOffers;
    }

    //  As an employee I want to see all offers on a car and choose whether to accept or reject each one
    //  Make an offer object with the customer details and return that if accepted? Have that offer object within the car object?
    //  Return the offer accepted, if no offer accepted return null.
    /*
    public static offer viewOffersOnCar(car checkOffers){
        offer accepted = new offer();
        return accepted;
    }
    */

    public static boolean remove(car carToRemove){
        return false;
    }

    public static double[] paymentsOnCar(car carPaymentHistory){
        double[] carPayments = new double[0];
        return carPayments;
    }

}
