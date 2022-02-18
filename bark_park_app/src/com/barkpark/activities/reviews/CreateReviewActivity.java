package com.barkpark.activities.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.requests.reviews.CreateReviewRequest;
import com.barkpark.models.results.reviews.CreateReviewResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateReviewActivity for the BarkPark CreateReview API.
 *
 * This API allows the customer to create a single review for a {@link com.barkpark.dynamodb.models.Park}.
 */
public class CreateReviewActivity implements RequestHandler<CreateReviewRequest, CreateReviewResult> {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;

    /**
     * Instantiates a new CreateReviewActivity object.
     *
     * @param reviewDao ReviewDao to access the reviews table
     */
    @Inject
    public CreateReviewActivity(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    /**
     * This method handles the incoming request by creating a review on the database.
     *
     * @param createReviewRequest request object containing the properties with which
     *                           to create a {@link com.barkpark.dynamodb.models.Review}
     * @param context context
     * @return result object containing the API defined {@link com.barkpark.models.ReviewModel}
     */
    @Override
    public CreateReviewResult handleRequest(CreateReviewRequest createReviewRequest, Context context) {
        log.info("Received CreateReviewRequest {}", createReviewRequest);

        if (createReviewRequest.getParkId() == null ||
            createReviewRequest.getUserId() == null ||
            createReviewRequest.getRating() == null) {
            throw new IllegalArgumentException("Must provide parkId, userId, and rating");
        }

        Review review = reviewDao.createReview(createReviewRequest);

        return CreateReviewResult.builder()
                .withReviewModel(ModelConverter.toReviewModel(review))
                .build();
    }
}
