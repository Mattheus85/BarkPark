package com.barkpark.activities.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.models.requests.reviews.GetReviewsRequest;
import com.barkpark.models.results.reviews.GetReviewsResult;
import com.barkpark.models.ReviewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetReviewsActivity for the BarkPark GetReviews API.
 *
 * This API allows the customer to get a List of {@link Review} objects,
 * optionally filtered by park or by user.
 */
public class GetReviewsActivity implements RequestHandler<GetReviewsRequest, GetReviewsResult> {
    private final Logger log = LogManager.getLogger();
    private final ReviewDao reviewDao;

    /**
     * Instantiates a new GetReviewsActivity object.
     *
     * @param reviewDao {@link ReviewDao} object to access the Reviews table
     */
    @Inject
    public GetReviewsActivity(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    /**
     * Handles the incoming request by retrieving the List of {@link Review} objects
     * from the database.
     *
     * @param getReviewsRequest request object containing a parkId, userId, or both
     * @param context context
     * @return {@link GetReviewsResult} object containing a list of API defined {@link ReviewModel} objects
     */
    @Override
    public GetReviewsResult handleRequest(GetReviewsRequest getReviewsRequest, Context context) {
        log.info("Received GetReviewsRequest {}", getReviewsRequest);

        String parkId = getReviewsRequest.getParkId();
        String userId = getReviewsRequest.getUserId();

        List<Review> reviewList = new ArrayList<>();

        if ((parkId != null && !parkId.isEmpty()) && (userId != null && !userId.isEmpty())) {
            reviewList.add(reviewDao.getReviewByParkIdAndUserId(parkId, userId));
        } else if (parkId != null && !parkId.isEmpty()) {
            reviewList = reviewDao.getReviewsByParkId(parkId);
        } else if (userId != null && !userId.isEmpty()) {
            reviewList = reviewDao.getReviewsByUserId(userId);
        } else {
            throw new IllegalArgumentException("The request must include a parkId, userId, or both");
        }
        
        return GetReviewsResult.builder()
                .withReviewModelList(ModelConverter.toReviewModelList(reviewList))
                .build();
    }
}
