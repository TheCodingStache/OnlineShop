package com.onlineshop.Model;

public class Cart {
    private String pid, pname, price, date, address, time;

    public Cart(String pid, String pname, String price, String date, String address, String time) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.date = date;
        this.address = address;
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Cart() {

    }
}
