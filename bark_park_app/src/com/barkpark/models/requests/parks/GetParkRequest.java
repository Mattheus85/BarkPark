package com.barkpark.models.requests.parks;

public class GetParkRequest {
    private String id;

    public GetParkRequest() {}

    private GetParkRequest(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public GetParkRequest build() {
            return new GetParkRequest(this);
        }
    }
}
