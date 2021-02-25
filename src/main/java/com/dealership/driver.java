package com.dealership;

import com.dealership.ui.loginMenu;

public class driver {
    public static void main(String[] args) {
        loginMenu test = new loginMenu();
        test.menu();
        System.out.println(test.username);
        System.out.println(test.password);

    }
}
