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

    // Is there any reason we would need a no-args constructor?

    // Should this be private since we're using the Builder pattern?
    private ParkModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.location = builder.location;
        this.avgRating = builder.avgRating;
        this.tags = builder.tags;
    }

    // Do we actually want all these getters and setters? What are the use cases?

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // equals and hashCode include ALL fields, including avgRating
    // We should discuss the case when a park has no reviews and adjust this accordingly
    // Also consider only checking based on required fields

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkModel parkModel = (ParkModel) o;
        return getId().equals(parkModel.getId()) && getName().equals(parkModel.getName()) && getLocation().equals(parkModel.getLocation()) && getAvgRating().equals(parkModel.getAvgRating()) && getTags().equals(parkModel.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLocation(), getAvgRating(), getTags());
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
