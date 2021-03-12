package com.dealership.system;

public class employee extends user {
    final String accountType = "employee";

    public employee(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public employee(){}


}
