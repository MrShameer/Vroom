package com.example.vroom.ui.status.model;

public class StatusCard {
    String lessorname;
    String model;

    public StatusCard(String lessorname, String model){
        this.lessorname=lessorname;
        this.model=model;
    }

    public String getlessorName() {
        return lessorname;
    }

    public void setlessorName(String name) {
        this.lessorname = lessorname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
