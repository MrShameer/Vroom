package com.example.vroom.ui.vehicledetails.model;

public class ReviewCard {
    String lessorname;
    String lessorid;
    String review;
    String date;

    public ReviewCard(String lessorid, String lessorname, String review, String date) {
        this.lessorid = lessorid;
        this.lessorname = lessorname;
        this.review = review;
        this.date = date;
    }

    public String getLessorname() {
        return lessorname;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }

    public String getLessorid(){
        return lessorid;
    }
}
