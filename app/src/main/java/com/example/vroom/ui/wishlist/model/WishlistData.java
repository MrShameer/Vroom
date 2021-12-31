package com.example.vroom.ui.wishlist.model;

public class WishlistData {
    String car;
    String lessorname;
    String rating;
    String image;

    public WishlistData(String car, String lessorname, String rating, String image) {
        this.car = car;
        this.lessorname = lessorname;
        this.rating = rating;
        this.image = image;
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
}


