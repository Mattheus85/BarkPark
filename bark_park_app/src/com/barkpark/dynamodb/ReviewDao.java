package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.exceptions.ReviewNotFoundException;
import com.barkpark.exceptions.ReviewsNotFoundException;

import javax.inject.Inject;
import java.util.List;

public class ReviewDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public ReviewDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Review getReviewByParkIdAndUserId(String parkId, String userId) {
        Review review = this.dynamoDBMapper.load(Review.class, parkId, userId);

        if (review == null) {
            throw new ReviewNotFoundException("Could not find a review for " + parkId +
                    " from " + userId + ".");
        }

        return review;
    }

    public List<Review> getAllReviewsByParkId(String parkId) {
        List<Review> reviewList = this.dynamoDBMapper.scan(Review.class, new DynamoDBScanExpression());

        if (reviewList == null || reviewList.isEmpty()) {
            throw new ReviewsNotFoundException("No reviews found.");
        }

        return reviewList;
    }

    public List<Review> getAllReviewsByUserId(String userId) {
        List<Review> reviewList =
    }
}
