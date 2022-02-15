package com.barkpark.models.results;

import com.barkpark.models.ParkModel;

import java.util.Objects;

public class GetParkResult {
    private ParkModel parkModel;

    private GetParkResult(Builder builder) {
        this.parkModel = builder.parkModel;
    }

    public ParkModel getParkModel() {
        return parkModel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParkResult that = (GetParkResult) o;
        return parkModel.equals(that.parkModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkModel);
    }

    @Override
    public String toString() {
        return "GetParkResult{" +
                "parkModel=" + parkModel +
                '}';
    }

    public static Builder builder() {
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
