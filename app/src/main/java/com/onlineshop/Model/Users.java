package com.onlineshop.Model;

public class Users {
    public String username;
    public String password;
    public String phone;
    public String image;
    public String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Users(String username, String password, String phone, String image, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.address = address;
    }

    public Users() {

    }
}
