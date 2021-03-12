package com.dealership.ui;
import com.dealership.*;
import com.dealership.sql.sqlFunctions;
import com.dealership.system.customer;
import com.dealership.system.user;

import java.util.Scanner;

public class loginMenu {
    String username, password;

    public void menu(){
        Scanner in = new Scanner(System.in);
        System.out.println("Press 1 for login, 2 for create an account");
        int temp = in.nextInt();
        if (temp == 1){
            System.out.println("Please log in...");
            System.out.println("Username: ");
            username = in.next();
            //System.out.println(username);
            System.out.println("Password: ");
            password = in.next();

            user loggedInAccount = sqlFunctions.retrieveAccount(username, password);
            //System.out.println(loggedInAccount.getUsername());

            //System.out.println("Press 1 for customer portal, 2 for employee portal");
            //int temp3 = in.nextInt();
            if(loggedInAccount.getAccountType().equals("employee")){
                employeeMenu.showEmployeeMenu();
            }
            else {
                //user n = new user(username, password);
                customerMenu.showCustomerMenu(loggedInAccount);

            }





        }
        else if (temp == 2){
            System.out.println("Press 1 for an employee account, 2 for customer account:");
            Scanner in2 = new Scanner(System.in);
            int temp2 = in2.nextInt();
            if (temp2 == 1){
                user n = new user();
                n.setAccountType("employee");
                System.out.println("What is the username?");
                n.setUsername(in.next());
                System.out.println("What is the password?");
                n.setPassword(in.next());
                System.out.println("What is the firstname?");
                n.setFirstName(in.next());
                System.out.println("What is the lastname?");
                n.setLastName(in.next());
                sqlFunctions.createAccount(n);
                System.out.println("Account Created");
                menu();
            }
            else {
                user n = new user();
                n.setAccountType("customer");
                System.out.println("What is the username?");
                n.setUsername(in.next());
                System.out.println("What is the password?");
                n.setPassword(in.next());
                System.out.println("What is the firstname?");
                n.setFirstName(in.next());
                System.out.println("What is the lastname?");
                n.setLastName(in.next());
                sqlFunctions.createAccount(n);
                System.out.println("Account Created");
                menu();

            }
        }

    }
}
