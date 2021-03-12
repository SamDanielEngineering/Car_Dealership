package com.dealership.system;

public class customer extends user{
    public final String accountType = "customer";

    public customer(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public customer(){}
    
}
