package com.barkpark.models.results.reviews;

import com.barkpark.models.ReviewModel;

import java.util.Objects;

public class UpdateReviewResult {
    private ReviewModel reviewModel;

    public UpdateReviewResult() {
    }

    private UpdateReviewResult(Builder builder) {
        this.reviewModel = builder.reviewModel;
    }

    public ReviewModel getReviewModel() {
        return reviewModel;
    }

    public void setReviewModel(ReviewModel reviewModel) {
        this.reviewModel = reviewModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateReviewResult that = (UpdateReviewResult) o;
        return Objects.equals(reviewModel, that.reviewModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewModel);
    }

    @Override
    public String toString() {
        return "UpdateReviewResult{" +
                "reviewModel=" + reviewModel +
                '}';
    }

    public static final class Builder {
        private ReviewModel reviewModel;

        public Builder withReviewModel(ReviewModel reviewModel) {
            this.reviewModel = reviewModel;
            return this;
        }

        public UpdateReviewResult build() {
            return new UpdateReviewResult(this);
        }
    }
}
