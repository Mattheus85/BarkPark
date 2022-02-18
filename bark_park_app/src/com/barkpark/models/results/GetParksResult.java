package com.barkpark.models.results;

import com.barkpark.models.ParkModel;

import java.util.List;
import java.util.Objects;

public class GetParksResult {
    private List<ParkModel> parkList;

    public GetParksResult() {}

    private GetParksResult(Builder builder) {
        this.parkList = builder.parkList;
    }

    public List<ParkModel> getParkList() {
        return parkList;
    }

    public void setParkList(List<ParkModel> parkList) {
        this.parkList = parkList;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParksResult that = (GetParksResult) o;
        return parkList.equals(that.parkList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkList);
    }

    @Override
    public String toString() {
        return "GetParksResult{" +
                "parkList=" + parkList +
                '}';
    }

    public static final class Builder {
        private List<ParkModel> parkList;

        public Builder withParkList(List<ParkModel> parkList) {
            this.parkList = parkList;
            return this;
        }

        public GetParksResult build() {
            return new GetParksResult(this);
        }
    }
}
