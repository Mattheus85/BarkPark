package com.barkpark.activities.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.requests.reviews.DeleteReviewRequest;
import com.barkpark.models.results.reviews.CreateReviewResult;
import com.barkpark.models.results.reviews.DeleteReviewResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteReviewActivity implements RequestHandler<DeleteReviewRequest, DeleteReviewResult> {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;

    @Inject
    public DeleteReviewActivity(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public DeleteReviewResult handleRequest(DeleteReviewRequest deleteReviewRequest, Context context) {
        log.info("Received DeleteReviewRequest {}", deleteReviewRequest);

        String parkId = deleteReviewRequest.getParkId();
        String userId = deleteReviewRequest.getUserId();

        if (parkId == null || parkId.isEmpty() || userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("Must provide parkId and userId");
        }

        Review deleteReview = reviewDao.deleteReview(parkId, userId);

        return DeleteReviewResult.builder()
                .withReviewModel(ModelConverter.toReviewModel(deleteReview))
                .build();
    }
}
