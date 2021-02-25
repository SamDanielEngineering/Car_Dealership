package com.dealership.ui;

import java.util.Scanner;

public class loginMenu {
    String username, password;

    public void menu(){

        System.out.println("Please log in...");
        System.out.println("Username: ");
        Scanner in = new Scanner(System.in);
        username = in.nextLine();
        System.out.println("Password: ");
        password = in.nextLine();
    }
}
