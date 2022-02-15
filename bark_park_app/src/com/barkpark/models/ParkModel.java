package com.barkpark.models;

import java.util.List;
import java.util.Objects;

/**
 * Represents a park as defined by the BarkPark API
 */
public class ParkModel {
    private String id;
    private String name;
    private String location;
    private Integer avgRating;
    private List<String> tags;

    private ParkModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.location = builder.location;
        this.avgRating = builder.avgRating;
        this.tags = builder.tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkModel parkModel = (ParkModel) o;
        return id.equals(parkModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ParkModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", avgRating=" + avgRating +
                ", tags=" + tags +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private String location;
        private Integer avgRating;
        private List<String> tags;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withAvgRating(Integer avgRating) {
            this.avgRating = avgRating;
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public ParkModel build() {
            return new ParkModel(this);
        }
    }
}
