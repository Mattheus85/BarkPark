package com.barkpark.models.results.reviews;

import com.barkpark.models.ReviewModel;

import java.util.Objects;

public class DeleteReviewResult {
    private ReviewModel reviewModel;

    private DeleteReviewResult(Builder builder) {
        this.reviewModel = builder.reviewModel;
    }

    public ReviewModel getReviewModel() {
        return reviewModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteReviewResult that = (DeleteReviewResult) o;
        return reviewModel.equals(that.reviewModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewModel);
    }

    @Override
    public String toString() {
        return "DeleteReviewResult{" +
                "reviewModel=" + reviewModel +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ReviewModel reviewModel;

        public Builder withReviewModel(ReviewModel reviewModel) {
            this.reviewModel = reviewModel;
            return this;
        }

        public DeleteReviewResult build() {
            return new DeleteReviewResult(this);
        }
    }
}
