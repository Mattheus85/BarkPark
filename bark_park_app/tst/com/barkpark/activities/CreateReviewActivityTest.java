package com.barkpark.activities;

import com.barkpark.activities.reviews.CreateReviewActivity;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.requests.reviews.CreateReviewRequest;
import com.barkpark.models.results.reviews.CreateReviewResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateReviewActivityTest {
    @Mock
    private ReviewDao reviewDao;

    private CreateReviewActivity createReviewActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createReviewActivity = new CreateReviewActivity(reviewDao);
    }

    @Test
    public void handleRequest_withProperRequest_returnsCorrectCreateReviewResult() {
        // GIVEN
        String parkId = "expectedId";
        String userId = "expectedId";
        Double rating = 5.0;

        Review review = new Review();
        review.setParkId(parkId);
        review.setUserId(userId);
        review.setRating(rating);

        when(reviewDao.createReview(any(CreateReviewRequest.class))).thenReturn(review);

        CreateReviewRequest request = CreateReviewRequest.builder()
                .withParkId(parkId)
                .withUserId(userId)
                .withRating(rating)
                .build();

        // WHEN
        CreateReviewResult result = createReviewActivity.handleRequest(request, null);

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
        CreateReviewRequest request = CreateReviewRequest.builder()
                .withParkId(parkId)
                .withUserId(userId)
                .build();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> createReviewActivity.handleRequest(request, null));
    }
}
