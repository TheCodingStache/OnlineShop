package com.onlineshop.Model;

public class Orders {
    private String name, address, city, date, time, phone, shipping_state;

    public Orders(String name, String address, String city, String date, String time, String phone, String shipping_state) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.date = date;
        this.time = time;
        this.phone = phone;
        this.shipping_state = shipping_state;
    }

    public Orders() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipping_state() {
        return shipping_state;
    }

    public void setShipping_state(String shipping_state) {
        this.shipping_state = shipping_state;
    }
}
