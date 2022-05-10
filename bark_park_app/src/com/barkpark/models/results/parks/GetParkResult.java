package com.barkpark.models.results.parks;

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

    public void setParkModel(ParkModel parkModel) {
        this.parkModel = parkModel;
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
