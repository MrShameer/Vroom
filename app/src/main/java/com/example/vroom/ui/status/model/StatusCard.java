package com.example.vroom.ui.status.model;

public class StatusCard {
    String lessorname;
    String model;
    String plat;
    public StatusCard(String lessorname, String model,String plat){
        this.lessorname=lessorname;
        this.model=model;
        this.plat=plat;
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

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }
}
