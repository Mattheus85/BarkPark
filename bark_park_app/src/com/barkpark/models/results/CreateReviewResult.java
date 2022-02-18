package com.barkpark.models.results;

import com.barkpark.models.ReviewModel;

import java.util.Objects;

public class CreateReviewResult {
    private ReviewModel reviewModel;

    private CreateReviewResult(Builder builder) {
        this.reviewModel = builder().reviewModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateReviewResult that = (CreateReviewResult) o;
        return Objects.equals(reviewModel, that.reviewModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewModel);
    }

    @Override
    public String toString() {
        return "CreateReviewResult{" +
                "reviewModel=" + reviewModel +
                '}';
    }

    public static final class Builder {
        private ReviewModel reviewModel;

        public Builder withReviewModel(ReviewModel reviewModel) {
            this.reviewModel = reviewModel;
            return this;
        }

        public CreateReviewResult build() {
            return new CreateReviewResult(this);
        }
    }
}
