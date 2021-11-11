package com.example.vroom.ui.home.recyclervire.Topvehicle;

public class topvehicle_data {
    String title,brand,passanger,door,luggage,gas;
    int photo;

    public topvehicle_data(String title,String brand,String passanger,String door,String luggage,String gas, int photo) {
        this.title=title;
        this.brand = brand;
        this.passanger = passanger;
        this.door = door;
        this.luggage = luggage;
        this.gas = gas;
        this.photo = photo;
    }

    public String getTitle() {return title;
    }

    public String getBrand() {
        return brand;
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

    public int getPhoto() {
        return photo;
    }
}
