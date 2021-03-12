package com.dealership.system;

public class user {
    String password, firstName, lastName;
    public String accountType;
    public String  username;

    public user(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public user(String username, String password, String firstName, String lastName, String accountType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountType() {
        return accountType;
    }

    public user() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.accountType = "";
    }
}
