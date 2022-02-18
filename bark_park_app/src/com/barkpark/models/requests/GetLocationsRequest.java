package com.barkpark.models.requests;

public class GetLocationsRequest {

    public GetLocationsRequest() {}

    private GetLocationsRequest(Builder builder) {}

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "GetLocationsRequest{}";
    }

    public static final class Builder {

        public GetLocationsRequest build() {
            return new GetLocationsRequest(this);
        }
    }
}
