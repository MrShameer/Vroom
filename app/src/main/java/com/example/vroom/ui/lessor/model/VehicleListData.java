package com.example.vroom.ui.lessor.model;

public class VehicleListData {
    String brand;
    String totaldays;
    String rate;
    String income;
    String platno;

    public VehicleListData(String brand, String totaldays, String rate, String income, String platno) {
        this.brand = brand;
        this.totaldays = totaldays;
        this.rate = rate;
        this.income = income;
        this.platno = platno;
    }

    public String getBrand() {
        return brand;
    }

    public String getTotaldays() {
        return totaldays;
    }

    public String getRate() {
        return rate;
    }

    public String getIncome() {
        return income;
    }

    public String getPlatno() {
        return platno;
    }
}
