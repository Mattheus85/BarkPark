package com.barkpark.activities.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.exceptions.ReviewsNotFoundException;
import com.barkpark.models.requests.reviews.DeleteReviewRequest;
import com.barkpark.models.results.reviews.CreateReviewResult;
import com.barkpark.models.results.reviews.DeleteReviewResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DeleteReviewActivity implements RequestHandler<DeleteReviewRequest, DeleteReviewResult> {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;
    private final ParkDao parkDao;

    @Inject
    public DeleteReviewActivity(ReviewDao reviewDao, ParkDao parkDao) {
        this.reviewDao = reviewDao;
        this.parkDao = parkDao;
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

        List<Review> reviews;
        try {
            reviews = reviewDao.getReviewsByParkId(parkId);
        } catch (ReviewsNotFoundException reviewsNotFoundException) {
            log.warn("No reviews found: ", reviewsNotFoundException);
            reviews = new ArrayList<>();
        }

        parkDao.updateAvgRating(parkId, reviews);

        return DeleteReviewResult.builder()
                .withReviewModel(ModelConverter.toReviewModel(deleteReview))
                .build();
    }
}
