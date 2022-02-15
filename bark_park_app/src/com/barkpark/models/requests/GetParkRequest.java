package com.barkpark.models.requests;

import java.util.Objects;

public class GetParkRequest {
    private String id;

    // I believe a no-args constructor is required by AWS, need to review
    public GetParkRequest() {}

    // Can/should this be private?
    public GetParkRequest(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParkRequest that = (GetParkRequest) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "GetParkRequest{" +
                "id='" + id + '\'' +
                '}';
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
