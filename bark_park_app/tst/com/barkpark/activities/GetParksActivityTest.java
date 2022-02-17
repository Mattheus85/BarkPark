package com.barkpark.activities;

import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.models.requests.GetParksRequest;
import com.barkpark.models.results.GetParksResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetParksActivityTest {
    @Mock
    private ParkDao parkDao;

    private GetParksActivity getParksActivity;

    private final Review review1 = new Review();
    private final Review review2 = new Review();
    private final Review review3 = new Review();
    private final List<Review> reviewList1 = new ArrayList<>();
    private final List<Review> reviewList2 = new ArrayList<>();
    private final List<Review> reviewList3 = new ArrayList<>();
    private final Set<String> expectedTags1 = new HashSet<>();
    private final Set<String> expectedTags2 = new HashSet<>();
    private final Set<String> expectedTags3 = new HashSet<>();
    private final Park park1 = new Park();
    private final Park park2 = new Park();
    private final Park park3 = new Park();
    private final List<Park> parkList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getParksActivity = new GetParksActivity(parkDao);

        review1.setParkId("expectedParkId1");
        review1.setDate("expectedDate1");
        review1.setReviewBody("expectedBody1");
        review1.setReviewTitle("expectedTitle1");
        review1.setRating(1.0);
        review1.setUserId("expectedUserId1");

        review2.setParkId("expectedParkId2");
        review2.setDate("expectedDate2");
        review2.setReviewBody("expectedBody2");
        review2.setReviewTitle("expectedTitle2");
        review2.setRating(2.0);
        review2.setUserId("expectedUserId2");
        
        review3.setParkId("expectedParkId3");
        review3.setDate("expectedDate3");
        review3.setReviewBody("expectedBody3");
        review3.setReviewTitle("expectedTitle3");
        review3.setRating(3.0);
        review3.setUserId("expectedUserId3");

        reviewList1.add(review1);
        reviewList2.add(review2);
        reviewList3.add(review3);

        expectedTags1.add("expectedTag1");
        expectedTags2.add("expectedTag2");
        expectedTags3.add("expectedTag3");

        park1.setAvgRating(1.0);
        park1.setLocation("expectedLocation1");
        park1.setId("expectedParkId1");
        park1.setName("expectedPark1");
        park1.setTags(expectedTags1);
        park1.setReviews(reviewList1);

        park2.setAvgRating(2.0);
        park2.setLocation("expectedLocation2");
        park2.setId("expectedParkId2");
        park2.setName("expectedPark2");
        park2.setTags(expectedTags2);
        park2.setReviews(reviewList2);
        
        park3.setAvgRating(2.0);
        park3.setLocation("expectedLocation2");
        park3.setId("expectedParkId3");
        park3.setName("expectedPark3");
        park3.setTags(expectedTags3);
        park3.setReviews(reviewList3);

        parkList.add(park1);
        parkList.add(park2);
        parkList.add(park3);
    }

    @Test
    public void handleRequest_withNeitherLocationNorRating_returnsAllParks() {
        // GIVEN
        when(parkDao.getAllParks()).thenReturn(parkList);
        GetParksRequest request = GetParksRequest.builder().build();

        // WHEN
        GetParksResult result = getParksActivity.handleRequest(request, null);

        // THEN
        assertEquals(3, result.getParkList().size(), "Expected result to return all parks");
    }

    @Test
    public void handleRequest_withLocationAndRating_returnsCorrectParks() {
        // GIVEN
        String expectedLocation = "expectedLocation2";
        Double expectedRating = 2.0;
        parkList.remove(0);
        when(parkDao.getParksByLocationAndAvgRating(expectedLocation, expectedRating)).thenReturn(parkList);
        GetParksRequest request = GetParksRequest.builder()
                .withLocation(expectedLocation)
                .withRating(expectedRating)
                .build();

        // WHEN
        GetParksResult result = getParksActivity.handleRequest(request, null);

        // THEN
        String message = "Expected result to match parkList";
        assertLocationAndRating(result, expectedLocation, expectedRating, message);
    }

    @Test
    public void handleRequest_withLocationButNotRating_returnsCorrectPark() {
        // GIVEN
        String expectedLocation = "expectedLocation1";
        parkList.remove(1);
        parkList.remove(1);
        when(parkDao.getParksByLocation(expectedLocation)).thenReturn(parkList);
        GetParksRequest request = GetParksRequest.builder().withLocation(expectedLocation).build();

        // WHEN
        GetParksResult result = getParksActivity.handleRequest(request, null);

        // THEN
        String message = "Expected result to match parkList";
        assertLocationButNotRating(result, expectedLocation, message);
    }

    @Test
    public void handleRequest_withRatingButNotLocation_returnsCorrectPark() {
        // GIVEN
        Double expectedRating = 2.0;
        parkList.remove(0);
        when(parkDao.getParksByAvgRating(expectedRating)).thenReturn(parkList);
        GetParksRequest request = GetParksRequest.builder().withRating(expectedRating).build();

        // WHEN
        GetParksResult result = getParksActivity.handleRequest(request, null);

        // THEN
        String message = "Expected result to match parkList";
        assertRatingButNotLocation(result, expectedRating, message);
    }

    @Test
    public void handleRequest_parkNotFound_throwsParkNotFoundException() {
        // GIVEN
        GetParksRequest request = GetParksRequest.builder().build();
        when(parkDao.getAllParks()).thenThrow(ParkNotFoundException.class);

        // WHEN && THEN
        assertThrows(ParkNotFoundException.class, () -> getParksActivity
                .handleRequest(request, null));
    }

    public void assertLocationAndRating(GetParksResult result, String expectedLocation, Double expectedRating, String message) {
        assertEquals(2, result.getParkList().size(), message);
        assertEquals(expectedLocation, result.getParkList().get(0).getLocation(), message);
        assertEquals(expectedRating, result.getParkList().get(0).getAvgRating(), message);
        assertEquals(expectedLocation, result.getParkList().get(1).getLocation(), message);
        assertEquals(expectedRating, result.getParkList().get(1).getAvgRating(), message);
    }

    public void assertLocationButNotRating(GetParksResult result, String expectedLocation, String message) {
        assertEquals(1, result.getParkList().size(), message);
        assertEquals("expectedParkId1", result.getParkList().get(0).getId(), message);
        assertEquals(1, result.getParkList().get(0).getAvgRating(), message);
        assertEquals(expectedLocation, result.getParkList().get(0).getLocation(), message);
        assertEquals("expectedPark1", result.getParkList().get(0).getName(), message);
        assertEquals(expectedTags1, result.getParkList().get(0).getTags(), message);
    }

    public void assertRatingButNotLocation(GetParksResult result, Double expectedRating, String message) {
        assertEquals(2, result.getParkList().size(), message);
        assertEquals("expectedParkId2", result.getParkList().get(0).getId(), message);
        assertEquals(expectedRating, result.getParkList().get(0).getAvgRating(), message);
        assertEquals("expectedLocation2", result.getParkList().get(0).getLocation(), message);
        assertEquals("expectedPark2", result.getParkList().get(0).getName(), message);
        assertEquals(expectedTags2, result.getParkList().get(0).getTags(), message);
    }
}
