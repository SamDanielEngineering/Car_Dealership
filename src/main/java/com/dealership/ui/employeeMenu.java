package com.dealership.ui;

import com.dealership.car.car;
import com.dealership.sql.sqlFunctions;
import com.dealership.system.customerFunctions;
import com.dealership.system.employeeFunctions;
import java.util.Scanner;

public class employeeMenu{
    public static void showEmployeeMenu(){
        Scanner in = new Scanner(System.in);

        System.out.println("What would you like to do today? (Please enter a number 1-5)");
        System.out.println("1. Add a car to the lot.");
        System.out.println("2. Accept or reject offer on a car.");
        System.out.println("3. Remove a car from the lot.");
        System.out.println("4. View all payments on a car.");
        System.out.println("5. Logout.");

        switch (in.nextInt()) {
            case 1:
                sqlFunctions.storeCarIntoLot(employeeFunctions.makeNewCar());
                showEmployeeMenu();
            case 2:
                car[] carLot = sqlFunctions.retrieveAllCarsIntoArray();
                int count = 0;
                for (int i = 0; i < carLot.length; i++){
                    if (carLot[i].getThisCarsOffers() != null){
                        System.out.println(carLot[i].toString());

                        count++;
                    }
                }
                if(count == 0) {
                    System.out.println("There are no offers on any cars right now, returning back to menu...");
                    showEmployeeMenu();
                }

                System.out.println("Which car would you like to see offers on ?");
                System.out.println("Please enter the exact VIN of the car:");
                Scanner in3 = new Scanner(System.in);
                int option = 0;
                option = in3.nextInt();

                float[] offersOnCar = sqlFunctions.retrieveAllOffersOnCar(option);
                int j = 0;
                for (int i = 0; i < offersOnCar.length; i++){
                    System.out.println(offersOnCar[i]);
                }

                System.out.println("Would you like to reject or accept an offer (1 for reject, 2 for accept, any other number to cancel):");
                int temp = in.nextInt();
                if (temp == 1){
                    System.out.println("Which offer would you like to reject, please enter the exact amount:");
                    double offerToBeRejected = in.nextDouble();
                    sqlFunctions.rejectOffer(offerToBeRejected);
                    System.out.println("Done, back to employee menu.");
                    showEmployeeMenu();

                }
                else if (temp == 2){
                    System.out.println("Which offer would you like to accept, please enter the exact amount:");
                    double offerToBeAccepted = in.nextDouble();
                    sqlFunctions.acceptOffer(option, offerToBeAccepted);
                    System.out.println("Done, back to employee menu.");
                    showEmployeeMenu();
                }
                else {
                    System.out.println("Cancelled, back to employee menu.");
                    showEmployeeMenu();
                }
                showEmployeeMenu();
            case 3:
                car[] carLot2 = customerFunctions.carInventory();
                for (int i = 0; i < carLot2.length; i++){
                    System.out.println(carLot2[i].toString());
                }
                System.out.println("Please enter the car VIN you would like to remove from the list above:");
                sqlFunctions.removeCarFromLot(in.nextInt());
                showEmployeeMenu();
            case 4:
                car[] carLot3 = sqlFunctions.retrieveAllCarsIntoArray();
                int count2 = 0;
                for (int i = 0;i < carLot3.length; i++){
                    if (carLot3[i].getThisCarsPayments() != null){
                        System.out.println(carLot3[i].toString());
                        count2++;
                    }
                }
                if (count2 == 0){
                    System.out.println("Sorry it looks like there arent any payments on any cars right now.");
                    showEmployeeMenu();
                }
                System.out.println("Which car would you like to see remaining payments on?");
                System.out.println("Please enter the VIN of the car listed above: ");
                int tempVin = in.nextInt();
                for (int i = 0;i < carLot3.length; i++){
                    if (carLot3[i].getVIN() == tempVin){
                        for (int k = 0; k < carLot3[i].getThisCarsPayments().length; k++){
                            System.out.println(carLot3[i].getThisCarsPayments()[k]);
                        }
                    }
                }

                showEmployeeMenu();
            case 5:
                loginMenu l = new loginMenu();
                l.menu();
            default:
                System.out.println("Invalid Number");
                showEmployeeMenu();
        }
    }
}
