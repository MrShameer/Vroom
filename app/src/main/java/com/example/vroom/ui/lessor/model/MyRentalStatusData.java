package com.example.vroom.ui.lessor.model;

public class MyRentalStatusData {
    String platno;
    String brand;
    String rating;
    String passanger;
    String door;
    String luggage;
    String gas;
    String price;

    String status;
    String lessesname;
    String total;
    String paymenttype;

    String datepayment;
    String datepickup;
    String datereturn;
    String progress;
    String location;

    public MyRentalStatusData(String platno, String brand, String rating, String passanger,
                              String door, String luggage, String gas, String price, String status,
                              String lessesname, String total, String paymenttype, String datepayment,
                              String datepickup, String datereturn, String progress, String location) {
        this.platno = platno;
        this.brand = brand;
        this.rating = rating;
        this.passanger = passanger;
        this.door = door;
        this.luggage = luggage;
        this.gas = gas;
        this.price = price;
        this.status = status;
        this.lessesname = lessesname;
        this.total = total;
        this.paymenttype = paymenttype;
        this.datepayment = datepayment;
        this.datepickup = datepickup;
        this.datereturn = datereturn;
        this.progress = progress;
        this.location = location;
    }


    public String getPlatno() {
        return platno;
    }

    public void setPlatno(String platno) {
        this.platno = platno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPassanger() {
        return passanger;
    }

    public void setPassanger(String passanger) {
        this.passanger = passanger;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLessesname() {
        return lessesname;
    }

    public void setLessesname(String lessesname) {
        this.lessesname = lessesname;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getDatepayment() {
        return datepayment;
    }

    public void setDatepayment(String datepayment) {
        this.datepayment = datepayment;
    }

    public String getDatepickup() {
        return datepickup;
    }

    public void setDatepickup(String datepickup) {
        this.datepickup = datepickup;
    }

    public String getDatereturn() {
        return datereturn;
    }

    public void setDatereturn(String datereturn) {
        this.datereturn = datereturn;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
