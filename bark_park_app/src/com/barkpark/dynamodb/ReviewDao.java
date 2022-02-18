package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.exceptions.ReviewNotFoundException;
import com.barkpark.exceptions.ReviewsNotFoundException;
import com.barkpark.models.requests.CreateReviewRequest;
import com.barkpark.models.requests.UpdateReviewRequest;

import javax.inject.Inject;
import java.util.List;

/**
 * Accesses data for reviews using {@link Review} to represent the model in DynamoDB.
 */
public class ReviewDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a {@link ReviewDao} object
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the reviews table
     */
    @Inject
    public ReviewDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Returns a single {@link Review} from a unique park and user.
     *
     * @param parkId the ID of the park with which to filter
     * @param userId the ID of the user with which to filter
     * @return the matching Review, or throws {@link ReviewNotFoundException}
     */
    public Review getReviewByParkIdAndUserId(String parkId, String userId) {
        Review review = this.dynamoDBMapper.load(Review.class, parkId, userId);

        if (review == null) {
            throw new ReviewNotFoundException("Could not find a review for " + parkId +
                    " from " + userId + ".");
        }

        return review;
    }

    /**
     * Returns a {@link List<Review>} of stored reviews filtered by park.
     *
     * @param parkId the ID of the park with which to filter
     * @return the list of stored Reviews for a park, or throws {@link ReviewsNotFoundException}
     */
    public List<Review> getReviewsByParkId(String parkId) {

        Review partitionKey = new Review();
        partitionKey.setParkId(parkId);

        DynamoDBQueryExpression<Review> queryExpression = new DynamoDBQueryExpression<Review>()
                .withHashKeyValues(partitionKey)
                .withConsistentRead(false);

        List<Review> reviewList = this.dynamoDBMapper.query(Review.class, queryExpression);

        if (reviewList == null || reviewList.isEmpty()) {
            throw new ReviewsNotFoundException("No reviews found.");
        }

        return reviewList;
    }

    /**
     * Returns a {@link List<Review>} of stored reviews filtered by user.
     *
     * @param userId the ID of the user with which to filter
     * @return the list of stored Reviews for a user, or throws {@link ReviewsNotFoundException}
     */
    public List<Review> getReviewsByUserId(String userId) {
        Review partitionKey = new Review();
        partitionKey.setUserId(userId);

        DynamoDBQueryExpression<Review> queryExpression = new DynamoDBQueryExpression<Review>()
                .withHashKeyValues(partitionKey)
                .withIndexName(Review.USER_ID_INDEX)
                .withConsistentRead(false);

        List<Review> reviewList = this.dynamoDBMapper.query(Review.class, queryExpression);

        if (reviewList == null || reviewList.isEmpty()) {
            throw new ReviewsNotFoundException("No reviews were found for " + userId + ".");
        }

        return reviewList;
    }

    /**
     * Creates a review based on the incoming {@link CreateReviewRequest} and saves it to the reviews table
     *
     * @param createReviewRequest the request with which to create the {@link Review}
     * @return the created and subsequently saved {@link Review}
     */
    public Review createReview(CreateReviewRequest createReviewRequest) {

        Review review = new Review();
        review.setParkId(createReviewRequest.getParkId());
        review.setUserId(createReviewRequest.getUserId());
        review.setReviewTitle(createReviewRequest.getReviewTitle());
        review.setReviewBody(createReviewRequest.getReviewBody());
        review.setDate(java.time.LocalDateTime.now().toString());
        review.setRating(createReviewRequest.getRating());

        this.dynamoDBMapper.save(review);
        return review;
    }

    /**
     * Updates a review based on the incoming {@link UpdateReviewRequest} and saves it to the reviews table
     *
     * @param updateReviewRequest the request with which to update the {@link Review}
     * @return the updated and subsequently saved {@link Review}
     */
    public Review updateReview(UpdateReviewRequest updateReviewRequest) {

        Review review = new Review();
        review.setParkId(updateReviewRequest.getParkId());
        review.setUserId(updateReviewRequest.getUserId());
        review.setReviewTitle(updateReviewRequest.getReviewTitle());
        review.setReviewBody(updateReviewRequest.getReviewBody());
        review.setDate(java.time.LocalDateTime.now().toString());
        review.setRating(updateReviewRequest.getRating());

        this.dynamoDBMapper.save(review);
        return review;
    }
}
