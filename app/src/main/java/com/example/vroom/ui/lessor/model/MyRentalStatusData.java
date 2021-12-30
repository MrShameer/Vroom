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
    String payment;
    String date;
    String pickupdate;
    String pickuplocation;

    String paymentstatus;
    String pickupstatus;
    String carreturn;
    String datepaymentstatus;
    String datepickupstatus;
    String datecarreturn;

    public MyRentalStatusData(String platno, String brand, String rating, String passanger, String door, String luggage, String gas, String price, String status, String lessesname, String total, String payment, String date, String pickupdate, String pickuplocation, String paymentstatus, String pickupstatus, String carreturn, String datepaymentstatus, String datepickupstatus, String datecarreturn) {
        this.platno = platno;
        this.brand = brand;
        this.rating = rating;
        this.passanger = passanger;
        this.door = door;
        this.luggage = luggage;
        this.gas = gas;
        this.price = price;
        this.status=status;
        this.lessesname = lessesname;
        this.total = total;
        this.payment = payment;
        this.date = date;
        this.pickupdate = pickupdate;
        this.pickuplocation = pickuplocation;
        this.paymentstatus = paymentstatus;
        this.pickupstatus = pickupstatus;
        this.carreturn = carreturn;
        this.datepaymentstatus = datepaymentstatus;
        this.datepickupstatus = datepickupstatus;
        this.datecarreturn = datecarreturn;
    }

    public String getPlatno() {
        return platno;
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

    public String getStatus() {
        return status;
    }

    public String getLessesname() {
        return lessesname;
    }

    public String getTotal() {
        return total;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }

    public String getPickupdate() {
        return pickupdate;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public String getPickupstatus() {
        return pickupstatus;
    }

    public String getCarreturn() {
        return carreturn;
    }

    public String getDatepaymentstatus() {
        return datepaymentstatus;
    }

    public String getDatepickupstatus() {
        return datepickupstatus;
    }

    public String getDatecarreturn() {
        return datecarreturn;
    }
}
