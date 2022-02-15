package com.barkpark.models.requests;

import java.util.Objects;

public class GetParkRequest {
    private String id;

    private GetParkRequest(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParkRequest that = (GetParkRequest) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
