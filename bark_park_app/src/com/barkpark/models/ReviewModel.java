package com.barkpark.models;

import java.util.Objects;

public class ReviewModel {
    private String parkId;
    private String userId;
    private String date;
    private String reviewTitle;
    private String reviewBody;
    private Double rating;

    private ReviewModel(Builder builder) {
        this.parkId = builder.parkId;
        this.userId = builder.userId;
        this.date = builder.date;
        this.reviewTitle = builder.reviewTitle;
        this.reviewBody = builder.reviewBody;
        this.rating = builder.rating;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewModel that = (ReviewModel) o;
        return getParkId().equals(that.getParkId()) &&
                getUserId().equals(that.getUserId()) &&
                getDate().equals(that.getDate()) &&
                getRating().equals(that.getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkId(), getUserId(), getDate(), getRating());
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "parkId='" + parkId + '\'' +
                ", userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewBody='" + reviewBody + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static final class Builder {
        private String parkId;
        private String userId;
        private String date;
        private String reviewTitle;
        private String reviewBody;
        private Double rating;

        public Builder withParkId(String parkId) {
            this.parkId = parkId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withReviewTitle(String reviewTitle) {
            this.reviewTitle = reviewTitle;
            return this;
        }

        public Builder withReviewBody(String reviewBody) {
            this.reviewBody = reviewBody;
            return this;
        }

        public Builder withRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public ReviewModel build() {
            return new ReviewModel(this);
        }
    }
}
