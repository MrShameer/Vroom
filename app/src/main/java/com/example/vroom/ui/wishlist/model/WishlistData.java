package com.example.vroom.ui.wishlist.model;

public class WishlistData {
    String car;
    String lessorname;
    String rating;
    String image;
    String platno;

    public WishlistData(String car, String lessorname, String rating, String image,String platno) {
        this.car = car;
        this.lessorname = lessorname;
        this.rating = rating;
        this.image = image;
        this.platno=platno;
    }

    public String getCar() {
        return car;
    }

    public String getLessorname() {
        return lessorname;
    }

    public String getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public String getPlatno() {
        return platno;
    }
}


