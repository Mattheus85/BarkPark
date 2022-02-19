package com.barkpark.models.results.locations;

import com.barkpark.models.LocationModel;

import java.util.Objects;

public class GetLocationsResult {
    private LocationModel locationModel;

    public GetLocationsResult() {}

    private GetLocationsResult(Builder builder) {
        this.locationModel = builder.locationModel;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetLocationsResult result = (GetLocationsResult) o;
        return Objects.equals(getLocationModel(), result.getLocationModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationModel());
    }

    @Override
    public String toString() {
        return "GetLocationsResult{" +
                "locationModel=" + locationModel +
                '}';
    }

    public static final class Builder {
        private LocationModel locationModel;

        public Builder withLocationModel(LocationModel locationModel) {
            this.locationModel = locationModel;
            return this;
        }

        public GetLocationsResult build() {
            return new GetLocationsResult(this);
        }
    }
}
