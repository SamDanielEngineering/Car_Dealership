package com.dealership.system;

import com.dealership.car.car;
import com.dealership.sql.sqlFunctions;

public class customerFunctions {
    public static car[] carInventory(){
        car[] allCars = sqlFunctions.retrieveAllCarsIntoArray();

        int arrayCount = 0;

        for (int i = 0; i < allCars.length; i++){
            if (allCars[i].getUsername() == null){
                arrayCount++;
            }
        }
        car[] inventory = new car[arrayCount];

        int j = 0;
        for (int i = 0; i < allCars.length; i++){
            if (allCars[i].getUsername() == null){
                inventory[j] = allCars[i];
                j++;
            }
        }

        return inventory;
    }

    public static void makeOffer(double offer, int vin, user u){
        sqlFunctions.storeOffer(vin, u.username, offer);
    }

    public static car[] ownedCars(user u){
        car[] lot = sqlFunctions.retrieveAllCarsIntoArray();

        int count = 0;
        for (int i = 0; i < lot.length; i++) {
            if (lot[i].username != null){
                if (lot[i].username.equals(u.username))
                    count++;
            }
        }

        car[] ownedCars = new car[count];
        int j = 0;
        for (int i = 0; i < lot.length; i++) {
            if (lot[i].username != null){
                if (lot[i].username.equals(u.username)){
                    ownedCars[j] = lot[i];
                    j++;
                }

            }
        }

        return ownedCars;
    }

    public static double[] remainingPayments(car carWithPayments){
        double[] carPayementsRemaining = new double[0];
        return carPayementsRemaining;
    }

    public static car[] carsWithRemainingPayments(customer id){
        car[] carsList = new car[0];
        return carsList;
    }
}
