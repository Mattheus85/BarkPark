package com.barkpark.activities.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.requests.reviews.UpdateReviewRequest;
import com.barkpark.models.results.reviews.UpdateReviewResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateReviewActivity implements RequestHandler<UpdateReviewRequest, UpdateReviewResult> {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;
    private final ParkDao parkDao;

    @Inject
    public UpdateReviewActivity(ReviewDao reviewDao, ParkDao parkDao) {
        this.reviewDao = reviewDao;
        this.parkDao = parkDao;
    }

    @Override
    public UpdateReviewResult handleRequest(UpdateReviewRequest updateReviewRequest, Context context) {
        log.info("Received UpdateReviewRequest {}", updateReviewRequest);

        String parkId = updateReviewRequest.getParkId();
        String userId = updateReviewRequest.getUserId();

        if (parkId == null || userId == null) {
            throw new IllegalArgumentException("Must provide parkId and userId");
        }

        Review review = reviewDao.updateReview(updateReviewRequest);

        parkDao.updateAvgRating(parkId, reviewDao.getReviewsByParkId(parkId));

        return UpdateReviewResult.builder()
                .withReviewModel(ModelConverter.toReviewModel(review))
                .build();
    }
}
