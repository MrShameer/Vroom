package com.example.vroom.ui.createrequest.model;

public class ReviewCard {
    String lessorname;
    String review;
    String date;

    public ReviewCard(String lessorname, String review, String date) {
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
}
