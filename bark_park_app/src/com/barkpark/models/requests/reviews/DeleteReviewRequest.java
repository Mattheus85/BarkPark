package com.barkpark.models.requests.reviews;

public class DeleteReviewRequest {
    private String parkId;
    private String userId;

    public DeleteReviewRequest() {
    }

    public DeleteReviewRequest(Builder builder) {
        this.parkId = builder.parkId;
        this.userId = builder.userId;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String parkId;
        private String userId;

        public Builder withParkId(String parkId) {
            this.parkId = parkId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public DeleteReviewRequest build() {
            return new DeleteReviewRequest(this);
        }
    }
}
