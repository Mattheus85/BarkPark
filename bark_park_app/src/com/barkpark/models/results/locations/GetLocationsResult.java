package com.barkpark.models.results.locations;

import java.util.Set;

public class GetLocationsResult {
    private Set<String> locationSet;

    public GetLocationsResult() {}

    private GetLocationsResult(Builder builder) {
        this.locationSet = builder.locationSet;
    }

    public Set<String> getLocationSet() {
        return locationSet;
    }

    public void setLocationModel(Set<String> locationSet) {
        this.locationSet = locationSet;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Set<String> locationSet;

        public Builder withLocationSet(Set<String> locationSet) {
            this.locationSet = locationSet;
            return this;
        }

        public GetLocationsResult build() {
            return new GetLocationsResult(this);
        }
    }
}
