package com.barkpark.activities;

import com.barkpark.activities.reviews.UpdateReviewActivity;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.requests.reviews.UpdateReviewRequest;
import com.barkpark.models.results.reviews.UpdateReviewResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateReviewActivityTest {
    @Mock
    private ReviewDao reviewDao;

    @Mock
    private ParkDao parkDao;

    private UpdateReviewActivity updateReviewActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateReviewActivity = new UpdateReviewActivity(reviewDao, parkDao);
    }

    @Test
    public void handleRequest_withProperRequest_returnsCorrectUpdateReviewResult() {
        // GIVEN
        String parkId = "expectedId";
        String userId = "expectedId";
        Double rating = 5.0;

        Review review = new Review();
        review.setParkId(parkId);
        review.setUserId(userId);
        review.setRating(rating);

        when(reviewDao.updateReview(any(UpdateReviewRequest.class))).thenReturn(review);
        doNothing().when(parkDao).updateAvgRating(any(String.class), anyList());

        UpdateReviewRequest request = UpdateReviewRequest.builder()
                .withParkId(parkId)
                .withUserId(userId)
                .withRating(rating)
                .build();

        // WHEN
        UpdateReviewResult result = updateReviewActivity.handleRequest(request, null);

        //THEN
        assertEquals(parkId, result.getReviewModel().getParkId(),
                "Expected the result parkId to be " + parkId);
        assertEquals(userId, result.getReviewModel().getUserId(),
                "Expected the result userId to be " + userId);
        assertEquals(rating, result.getReviewModel().getRating(),
                "Expected the result rating to be " + rating);
    }

    @Test
    public void handleRequest_withImproperRequest_throwsIllegalArgumentException() {
        // GIVEN
        String parkId = "expectedId";
        String userId = "expectedId";
        UpdateReviewRequest request = UpdateReviewRequest.builder()
                .withParkId(parkId)
                .withUserId(userId)
                .build();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> updateReviewActivity.handleRequest(request, null));
    }
}
