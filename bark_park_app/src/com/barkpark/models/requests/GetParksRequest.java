package com.barkpark.models.requests;

public class GetParksRequest {
    private String location;
    private String avgRating;

    public GetParksRequest() {}

    private GetParksRequest(Builder builder) {
        this.location = builder.location;
        this.avgRating = builder.avgRating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    @Override
    public String toString() {
        return "GetParksRequest{" +
                "location='" + location + '\'' +
                ", rating='" + avgRating + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String location;
        private String avgRating;

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withRating(String rating) {
            this.avgRating = rating;
            return this;
        }

        public GetParksRequest build() {
            return new GetParksRequest(this);
        }
    }
}
