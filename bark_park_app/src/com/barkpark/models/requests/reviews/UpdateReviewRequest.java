package com.barkpark.models.requests.reviews;

import java.util.Objects;

public class UpdateReviewRequest {
    private String parkId;
    private String userId;
    private String reviewTitle;
    private String reviewBody;
    private String date;
    private Double rating;

    public UpdateReviewRequest() {
    }

    private UpdateReviewRequest(Builder builder) {
        this.parkId = builder.parkId;
        this.userId = builder.userId;
        this.reviewTitle = builder.reviewTitle;
        this.reviewBody = builder.reviewBody;
        this.date = builder.date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        UpdateReviewRequest that = (UpdateReviewRequest) o;
        return Objects.equals(getParkId(), that.getParkId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getRating(), that.getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkId(), getUserId(), getRating());
    }

    @Override
    public String toString() {
        return "UpdateReviewRequest{" +
                "parkId='" + parkId + '\'' +
                ", userId='" + userId + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewBody='" + reviewBody + '\'' +
                ", date='" + date + '\'' +
                ", rating=" + rating +
                '}';
    }

    public static final class Builder {
        private String parkId;
        private String userId;
        private String reviewTitle;
        private String reviewBody;
        private String date;
        private Double rating;

        public Builder withParkId(String parkId) {
            this.parkId = parkId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
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

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public UpdateReviewRequest build() {
            return new UpdateReviewRequest(this);
        }
    }
}
