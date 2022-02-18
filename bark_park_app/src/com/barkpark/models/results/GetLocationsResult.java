package com.barkpark.models.results;

import com.barkpark.models.LocationModel;

import java.util.List;
import java.util.Objects;

public class GetLocationsResult {
    private List<LocationModel> locationModelList;

    private GetLocationsResult(Builder builder) {
        this.locationModelList = builder.locationModelList;
    }

    public List<LocationModel> getLocationModelList() {
        return locationModelList;
    }

    public void setLocationModelList(List<LocationModel> locationModelList) {
        this.locationModelList = locationModelList;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetLocationsResult result = (GetLocationsResult) o;
        return Objects.equals(getLocationModelList(), result.getLocationModelList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationModelList());
    }

    @Override
    public String toString() {
        return "GetLocationsResult{" +
                "locationModel=" + locationModelList +
                '}';
    }

    public static final class Builder {
        private List<LocationModel> locationModelList;

        public Builder withLocationModelList(List<LocationModel> locationModelList) {
            this.locationModelList = locationModelList;
            return this;
        }

        public GetLocationsResult build() {
            return new GetLocationsResult(this);
        }
    }
}
