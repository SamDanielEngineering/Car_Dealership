package com.dealership;

import com.dealership.car.car;
import com.dealership.jdbc.ConnectionFactory;
import com.dealership.sql.sqlFunctions;
import com.dealership.system.customerFunctions;
import com.dealership.ui.loginMenu;

import java.sql.Connection;

public class driver {
    public static void main(String[] args) {
        /*
        loginMenu test = new loginMenu();
        test.menu();
        System.out.println(test.username);
        System.out.println(test.password);
        */


        //Connection conn = ConnectionFactory.connect();
        /*
        car c = new car("Model 3", "Tesla", 100000002, 2020, 0);
        //sqlFunctions.storeCarIntoLot(c);
        car[] carLot = customerFunctions.carInventory();
        for (int i = 0; i < carLot.length; i++){
            System.out.println(carLot[i].toString());
        }


         */

        //sqlFunctions.retrieveAllOffersOnCar(600);

        /*

        sqlFunctions.retrieveAllOffersOnCar(610);

        sqlFunctions.retrieveAllOffersOnCar(620);

        sqlFunctions.retrieveAllOffersOnCar(630);

         */

        //System.out.println(sqlFunctions.countCars());



        //sqlFunctions.retrieveAllPaymentsOnCar(639);

        loginMenu n = new loginMenu();
        n.menu();


         /*

        System.out.println(sqlFunctions.tableIsEmpty("carlist"));;
        sqlFunctions.tableIsEmpty("finance");
        sqlFunctions.tableIsEmpty("accounts");
        sqlFunctions.tableIsEmpty("offers");

         */

    }
}
