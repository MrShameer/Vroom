package com.example.vroom.database.VehicleDetails;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "VehicleDetails_table")
public class VehicleDetails implements Serializable {
    @PrimaryKey
    @NonNull
    private String vehicleplat;
    private String lessorname;
    private String lessorlocation;
    private String lessorrating;
    private String vehiclepassanger;
    private String vehicledoor;
    private String vehicleluggage;
    private String vehiclebrand;
    private String vehiclemodel;
    private String vehiclecolor;
    private String vehicleage;
    private String vehicleinsurance;
    private String vehicletank;
    private String vehiclerating;
    private String vehicleprice;
    private String lessorid;

    public VehicleDetails(@NonNull String vehicleplat, String lessorname, String lessorid, String lessorlocation, String lessorrating,
                          String vehiclepassanger, String vehicledoor, String vehicleluggage, String vehiclebrand,
                          String vehiclemodel, String vehiclecolor, String vehicleage,
                          String vehicleinsurance, String vehicletank,
                          String vehiclerating, String vehicleprice) {
        this.vehicleplat = vehicleplat;
        this.lessorname = lessorname;
        this.lessorlocation = lessorlocation;
        this.lessorrating = lessorrating;
        this.vehiclepassanger = vehiclepassanger;
        this.vehicledoor = vehicledoor;
        this.vehicleluggage = vehicleluggage;
        this.vehiclebrand = vehiclebrand;
        this.vehiclemodel = vehiclemodel;
        this.vehiclecolor = vehiclecolor;
        this.vehicleage = vehicleage;
        this.vehicleinsurance = vehicleinsurance;
        this.vehicletank = vehicletank;
        this.vehiclerating = vehiclerating;
        this.vehicleprice = vehicleprice;
    }

    @Ignore
    public VehicleDetails(String lessorname, String lessorid, String vehicleplat, String vehiclebrand, String vehiclemodel, String vehicleinsurance, String vehicleage, String vehiclepassanger, String vehicledoor, String vehicleluggage, String vehicletank, String vehicleprice){
        this.vehicleplat = vehicleplat;
        this.lessorname = lessorname;
        this.lessorid = lessorid;
        this.vehiclepassanger = vehiclepassanger;
        this.vehicledoor = vehicledoor;
        this.vehicleluggage = vehicleluggage;
        this.vehiclebrand = vehiclebrand;
        this.vehiclemodel = vehiclemodel;
        this.vehicleage = vehicleage;
        this.vehicleinsurance = vehicleinsurance;
        this.vehicletank = vehicletank;
        this.vehicleprice = vehicleprice;
    }

    @NonNull
    public String getVehicleplat() {
        return vehicleplat;
    }

    public String getLessorname() {
        return lessorname;
    }

    public String getLessorid(){
        return lessorid;
    }

    public String getLessorlocation() {
        return lessorlocation;
    }

    public String getLessorrating() {
        return lessorrating;
    }

    public String getVehiclepassanger() {
        return vehiclepassanger;
    }

    public String getVehicledoor() {
        return vehicledoor;
    }

    public String getVehicleluggage() {
        return vehicleluggage;
    }

    public String getVehiclebrand() {
        return vehiclebrand;
    }

    public String getVehiclemodel() {
        return vehiclemodel;
    }

    public String getVehiclecolor() {
        return vehiclecolor;
    }

    public String getVehicleage() {
        return vehicleage;
    }

    public String getVehicleinsurance() {
        return vehicleinsurance;
    }

    public String getVehicletank() {
        return vehicletank;
    }

    public String getVehiclerating() {
        return vehiclerating;
    }

    public String getVehicleprice() {
        return vehicleprice;
    }
}
