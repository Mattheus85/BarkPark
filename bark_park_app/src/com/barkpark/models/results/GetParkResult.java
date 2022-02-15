package com.barkpark.models.results;

import com.barkpark.models.ParkModel;

import java.util.Objects;

public class GetParkResult {
    // I named this parkModel rather than park for clarity, happy to change
    private ParkModel parkModel;

    private GetParkResult(Builder builder) {
        this.parkModel = builder.parkModel;
    }

    public ParkModel getParkModel() {
        return parkModel;
    }

    public void setParkModel(ParkModel parkModel) {
        this.parkModel = parkModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParkResult that = (GetParkResult) o;
        return getParkModel().equals(that.getParkModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkModel());
    }

    @Override
    public String toString() {
        return "GetParkResult{" +
                "parkModel=" + parkModel +
                '}';
    }

    public Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ParkModel parkModel;

        public Builder withParkModel(ParkModel parkModel) {
            this.parkModel = parkModel;
            return this;
        }

        public GetParkResult build() {
            return new GetParkResult(this);
        }
    }
}
