package com.barkpark.models;

import java.util.List;
import java.util.Objects;

public class ParksModel {
    private List<String> parkIds;

    private ParksModel(Builder builder) {
        this.parkIds = builder.parkIds;
    }

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParksModel that = (ParksModel) o;
        return getParkIds().equals(that.getParkIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkIds());
    }

    @Override
    public String toString() {
        return "ParksModel{" +
                "parkIds=" + parkIds +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<String> parkIds;

        public Builder withParkIds(List<String> parkIds) {
            this.parkIds = parkIds;
            return this;
        }

        public ParksModel build() {
            return new ParksModel(this);
        }
    }
}
