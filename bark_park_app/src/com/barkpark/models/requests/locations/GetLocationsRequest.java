package com.barkpark.models.requests.locations;

public class GetLocationsRequest {

    public GetLocationsRequest() {}

    private GetLocationsRequest(Builder builder) {}

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        public GetLocationsRequest build() {
            return new GetLocationsRequest(this);
        }
    }
}
