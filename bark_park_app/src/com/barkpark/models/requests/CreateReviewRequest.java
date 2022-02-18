package com.barkpark.models.requests;

import java.util.Objects;

public class CreateReviewRequest {
    private Double rating;
    private String reviewBody;
    private String reviewTitle;

    public CreateReviewRequest() {
    }

    private CreateReviewRequest(Builder builder) {
        this.rating = builder.rating;
        this.reviewBody = builder.reviewBody;
        this.reviewTitle = builder.reviewTitle;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReviewRequest that = (CreateReviewRequest) o;
        return rating.equals(that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating);
    }

    @Override
    public String toString() {
        return "CreateReviewRequest{" +
                "rating=" + rating +
                ", reviewBody='" + reviewBody + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                '}';
    }

    public static final class Builder {
        private Double rating;
        private String reviewBody;
        private String reviewTitle;

        public Builder withRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder withReviewBody(String reviewBody) {
            this.reviewBody = reviewBody;
            return this;
        }

        public Builder withReviewTitle(String reviewTitle) {
            this.reviewTitle = reviewTitle;
            return this;
        }

        public CreateReviewRequest build() {
            return new CreateReviewRequest(this);
        }
    }
}
