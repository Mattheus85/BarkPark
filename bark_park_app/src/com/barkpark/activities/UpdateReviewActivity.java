package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.requests.UpdateReviewRequest;
import com.barkpark.models.results.UpdateReviewResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateReviewActivity implements RequestHandler<UpdateReviewRequest, UpdateReviewResult> {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;

    @Inject
    public UpdateReviewActivity(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public UpdateReviewResult handleRequest(UpdateReviewRequest updateReviewRequest, Context context) {
        log.info("Received UpdateReviewRequest {}", updateReviewRequest);

        if (updateReviewRequest.getParkId() == null ||
            updateReviewRequest.getUserId() == null ||
            updateReviewRequest.getRating() == null) {
            throw new IllegalArgumentException("Must provide parkId, userId, and rating");
        }

        Review review = reviewDao.updateReview(updateReviewRequest);

        return UpdateReviewResult.builder()
                .withReviewModel(ModelConverter.toReviewModel(review))
                .build();
    }
}
