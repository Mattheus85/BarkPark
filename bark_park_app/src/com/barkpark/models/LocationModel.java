package com.barkpark.models;

import java.util.Objects;
import java.util.Set;

public class LocationModel {
    private Set<String> locationSet;

    private LocationModel(Builder builder) {
        this.locationSet = builder.locationSet;
    }

    public Set<String> getLocationSet() {
        return locationSet;
    }
    public void setLocationSet(Set<String> locationSet) {
        this.locationSet = locationSet;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationModel that = (LocationModel) o;
        return Objects.equals(getLocationSet(), that.getLocationSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationSet());
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "locationSet=" + locationSet +
                '}';
    }

    public static final class Builder {
        private Set<String> locationSet;

        public Builder withLocationSet(Set<String> locationSet) {
            this.locationSet = locationSet;
            return this;
        }

        public LocationModel build() {
            return new LocationModel(this);
        }
    }
}
