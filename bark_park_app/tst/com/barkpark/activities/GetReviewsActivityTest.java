package com.barkpark.activities;

import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.models.ReviewModel;
import com.barkpark.models.requests.GetReviewsRequest;
import com.barkpark.models.results.GetReviewsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetReviewsActivityTest {
    @Mock
    private ReviewDao reviewDao;

    private GetReviewsActivity getReviewsActivity;

    private Review review1 = new Review();
    private Review review2 = new Review();
    private List<Review> parkReviewList = new ArrayList<>();
    private List<Review> userReviewList = new ArrayList<>();
    private List<Review> allReviewsList = new ArrayList<>();

    private ReviewModel reviewModel1 = ReviewModel.builder()
            .withParkId("park1")
            .withUserId("user1")
            .withRating(5.0)
            .withDate("today")
            .build();
    private ReviewModel reviewModel2 = ReviewModel.builder()
            .withParkId("park2")
            .withUserId("user2")
            .withRating(5.0)
            .withDate("today")
            .build();
    private List<ReviewModel> parkReviewModelList = new ArrayList<>();
    private List<ReviewModel> userReviewModelList = new ArrayList<>();
    private List<ReviewModel> allReviewsModelList = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        openMocks(this);
        getReviewsActivity = new GetReviewsActivity(reviewDao);

        review1.setParkId("park1");
        review1.setUserId("user1");
        review1.setRating(5.0);
        review1.setDate("today");
        parkReviewList.add(review1);
        review2.setParkId("park2");
        review2.setUserId("user2");
        review2.setRating(5.0);
        review2.setDate("today");
        userReviewList.add(review2);

        allReviewsList.add(review1);
        allReviewsList.add(review2);

        parkReviewModelList.add(reviewModel1);
        userReviewModelList.add(reviewModel2);

        allReviewsModelList.add(reviewModel1);
        allReviewsModelList.add(reviewModel2);
    }

    @Test
    public void handleRequest_withParkIdButNotUserId_returnsCorrectParkReviews() {
        // GIVEN
        GetReviewsRequest request = GetReviewsRequest.builder().withParkId("1").build();
        when(reviewDao.getReviewsByParkId(any(String.class))).thenReturn(parkReviewList);

        // WHEN
        GetReviewsResult result = getReviewsActivity.handleRequest(request, null);

        // THEN
        assertEquals(parkReviewModelList, result.getReviewList(),
                "Result list expected to equal " + parkReviewModelList);
    }

    @Test
    public void handleRequest_withUserIdButNotParkId_returnsCorrectUserReviews() {
        // GIVEN
        GetReviewsRequest request = GetReviewsRequest.builder().withUserId("1").build();
        when(reviewDao.getReviewsByUserId(any(String.class))).thenReturn(userReviewList);

        // WHEN
        GetReviewsResult result = getReviewsActivity.handleRequest(request, null);

        // THEN
        assertEquals(userReviewModelList, result.getReviewList(),
                "Result list expected to equal " + userReviewModelList);
    }

    @Test
    public void handleRequest_withBothParkIdAndUserId_returnsSingleCorrectReview() {
        // GIVEN
        GetReviewsRequest request = GetReviewsRequest.builder().withParkId("1").withUserId("1").build();
        when(reviewDao.getReviewByParkIdAndUserId(any(String.class), any(String.class))).thenReturn(review1);

        // WHEN
        GetReviewsResult result = getReviewsActivity.handleRequest(request, null);

        // THEN
        assertEquals(1, result.getReviewList().size(),
                "The result list expected to have one item.");
        assertEquals(reviewModel1, result.getReviewList().get(0),
                "Result list expected to equal " + parkReviewModelList);
    }

    @Test
    public void handleRequest_withNeitherParkIdNorUserId_throwsIllegalArgumentException() {
        // GIVEN
        GetReviewsRequest request = GetReviewsRequest.builder().build();

        // WHEN && THEN
        assertThrows(IllegalArgumentException.class, () -> getReviewsActivity
                .handleRequest(request, null));
    }
}
