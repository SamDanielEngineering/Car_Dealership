package com.dealership.ui;

import com.dealership.car.car;
import com.dealership.system.customerFunctions;
import com.dealership.system.user;

import java.util.Scanner;

public class customerMenu {
    public static void showCustomerMenu(user u){
        Scanner in = new Scanner(System.in);

        System.out.println("What would you like to do today? (Please enter a number 1-5)");
        System.out.println("1. View cars on the lot.");
        System.out.println("2. Make an offer on a car.");
        System.out.println("3. View my cars that I own.");
        System.out.println("4. View my remaining payments on a car.");
        System.out.println("5. Logout.");

        int choice = in.nextInt();

        switch (choice){
            case 1:
                car[] carLot2 = customerFunctions.carInventory();
                for (int i = 0; i < carLot2.length; i++){
                    System.out.println(carLot2[i].toString());
                }
                showCustomerMenu(u);
            case 2:
                car[] carLot = customerFunctions.carInventory();
                System.out.println("Which car would you like to make an offer on? Please type the vin number from this list:");
                for (int i = 0; i < carLot.length; i++){
                    System.out.println(carLot[i].toString());
                }
                int carChoice = in.nextInt();
                boolean loop = false;
                while (loop == false){
                    for (int i = 0; i < carLot.length; i++){
                        if (carChoice == carLot[i].getVIN()){
                            loop = true;
                        }
                    }
                    if (loop ==  false){
                        System.out.println("Incorrect VIN, please try again. Here is the list of cars that you can make an offer on.");
                        for (int i = 0; i < carLot.length; i++){
                            System.out.println(carLot[i].toString());
                        }
                        System.out.println("Please enter the vin to make an offer on:");
                        carChoice = in.nextInt();
                    }
                }
                System.out.println("How much would you like to make an offer for?");
                double offer = in.nextDouble();
                customerFunctions.makeOffer(offer, carChoice, u);
                showCustomerMenu(u);
                break;
            case 3:
                car[] ownedCars = customerFunctions.ownedCars(u);
                for (int i = 0; i < ownedCars.length; i++){
                    System.out.println(ownedCars[i].toString());
                }
                showCustomerMenu(u);
                break;
            case 4:

                System.out.println("Which car would you like to see remaining payments on?");
                car[] ownedCars2 = customerFunctions.ownedCars(u);
                int count = 0;
                for (int i = 0; i < ownedCars2.length; i++){
                    if (ownedCars2[i].getRemainingBalance() > 0){
                        System.out.println(ownedCars2[i].toString());
                        count++;
                    }
                }

                car[] remainingPaymentCars = new car[count];
                int j = 0;
                for (int i = 0; i < ownedCars2.length; i++){
                    if (ownedCars2[i].getRemainingBalance() > 0){
                        remainingPaymentCars[j] = ownedCars2[i];
                    }
                }

                System.out.println("Please enter the exact VIN of the car:");
                int option = in.nextInt();
                boolean loop2 = false;
                while (loop2 == false){
                    for (int i = 0; i < remainingPaymentCars.length; i++){
                        if (option == remainingPaymentCars[i].getVIN()){
                            System.out.println("Remaining balance: $" + remainingPaymentCars[i].getRemainingBalance());
                            loop2 = true;
                        }
                    }
                    if (loop2 ==  false){
                        System.out.println("Incorrect VIN, please try again. Here is the list of cars that you can view remaining balances on.");
                        for (int i = 0; i < remainingPaymentCars.length; i++){
                            System.out.println(remainingPaymentCars[i].toString());
                        }
                        System.out.println("Please enter the VIN of the car you want to view the remaining balance on:");
                        option = in.nextInt();
                    }
                }
                showCustomerMenu(u);
                break;
            case 5:
                loginMenu l = new loginMenu();
                l.menu();
            default:
                System.out.println("Invalid Number");
                showCustomerMenu(u);
        }
    }
}
