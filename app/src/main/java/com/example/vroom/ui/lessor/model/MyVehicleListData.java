package com.example.vroom.ui.lessor.model;

public class MyVehicleListData {
    String plat;
    String brand;
    String rating;
    String passanger;
    String door;
    String luggage;
    String gas;
    String price;
    Boolean list;

    public  String getPlat(){
        return plat;
    }

    public String getBrand() {
        return brand;
    }

    public String getRating() {
        return rating;
    }

    public String getPassanger() {
        return passanger;
    }

    public String getDoor() {
        return door;
    }

    public String getLuggage() {
        return luggage;
    }

    public String getGas() {
        return gas;
    }

    public String getPrice() {
        return price;
    }

    public Boolean getList() {
        return list;
    }

    public MyVehicleListData(String plat, String brand, String rating, String passanger, String door, String luggage, String gas, String price, Boolean list) {
        this.plat = plat;
        this.brand = brand;
        this.rating = rating;
        this.passanger = passanger;
        this.door = door;
        this.luggage = luggage;
        this.gas = gas;
        this.price = price;
        this.list = list;
    }
}
