package com.barkpark.models;

import com.barkpark.dynamodb.models.Location;

import java.util.Objects;

public class LocationModel {
    private Location location;

    private LocationModel(Builder builder) {
        this.location = builder.location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationModel that = (LocationModel) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "locationList=" + location +
                '}';
    }

    public static final class Builder {
        private Location location;

        public Builder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public LocationModel build() {
            return new LocationModel(this);
        }
    }
}
