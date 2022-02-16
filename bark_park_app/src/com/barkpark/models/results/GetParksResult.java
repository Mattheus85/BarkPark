package com.barkpark.models.results;

import com.barkpark.models.ParksModel;

import java.util.Objects;

public class GetParksResult {
    private ParksModel parksModel;

    private GetParksResult(Builder builder) {
        this.parksModel = builder.parksModel;
    }

    public ParksModel getParksModel() {
        return parksModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetParksResult that = (GetParksResult) o;
        return Objects.equals(getParksModel(), that.getParksModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParksModel());
    }

    @Override
    public String toString() {
        return "GetParksResult{" +
                "parksModel=" + parksModel +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ParksModel parksModel;

        public Builder withParksModel(ParksModel parksModel) {
            this.parksModel = parksModel;
            return this;
        }

        public GetParksResult build() {
            return new GetParksResult(this);
        }
    }
}
