package com.barkpark.models.results.reviews;

import com.barkpark.models.ReviewModel;

import java.util.List;
import java.util.Objects;

public class GetReviewsResult {
    private List<ReviewModel> reviewList;

    public GetReviewsResult() {}

    public GetReviewsResult(Builder builder) {
        this.reviewList = builder.reviewList;
    }

    public List<ReviewModel> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewModel> reviewList) {
        this.reviewList = reviewList;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetReviewsResult that = (GetReviewsResult) o;
        return reviewList.equals(that.reviewList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewList);
    }

    @Override
    public String toString() {
        return "GetReviewsResult{" +
                "reviewModelList=" + reviewList +
                '}';
    }

    public static final class Builder {
        private List<ReviewModel> reviewList;

        public Builder withReviewModelList(List<ReviewModel> reviewList) {
            this.reviewList = reviewList;
            return this;
        }

        public GetReviewsResult build() {
            return new GetReviewsResult(this);
        }
    }
}

