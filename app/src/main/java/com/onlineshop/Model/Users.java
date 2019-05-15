package com.onlineshop.Model;

public class Users {
    private String name;
    private String password;
    private String phone;


    public Users(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Users(){

    }
}
