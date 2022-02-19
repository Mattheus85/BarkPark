package com.barkpark.models.requests.reviews;

public class GetReviewsRequest {
    String parkId;
    String userId;

    public GetReviewsRequest() {}

    private GetReviewsRequest(Builder builder) {
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

    @Override
    public String toString() {
        return "GetReviewsRequest{" +
                "parkId='" + parkId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public static final class Builder {
        String parkId;
        String userId;

        public Builder withParkId(String parkId) {
            this.parkId = parkId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetReviewsRequest build() {
            return new GetReviewsRequest(this);
        }
    }
}
