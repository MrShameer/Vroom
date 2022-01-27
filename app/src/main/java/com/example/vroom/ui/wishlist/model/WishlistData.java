package com.example.vroom.ui.wishlist.model;

public class WishlistData {
    String car;
    String lessorname;
    String rating;
    String image;
    String platno;
    String lessorid;

    public WishlistData(String car, String lessorname, String rating, String image,String platno,String lessorid) {
        this.car = car;
        this.lessorname = lessorname;
        this.rating = rating;
        this.image = image;
        this.platno=platno;
        this.lessorid=lessorid;
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

    public String getLessorid() {
        return lessorid;
    }

    public void setLessorid(String lessorid) {
        this.lessorid = lessorid;
    }
}


