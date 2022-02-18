package com.barkpark.activities;

import com.barkpark.activities.parks.GetParkActivity;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.models.requests.parks.GetParkRequest;
import com.barkpark.models.results.parks.GetParkResult;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetParkActivityTest {
    @Mock
    private ParkDao parkDao;

    private GetParkActivity getParkActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getParkActivity = new GetParkActivity(parkDao);
    }

    @Test
    public void handleRequest_savedParkFound_returnsParkModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedLocation = "expectedLocation";
        Double expectedAvgRating = 4.5;
        Set<String> expectedTags = Sets.newHashSet("tag");

        Park park = new Park();
        park.setId(expectedId);
        park.setName(expectedName);
        park.setLocation(expectedLocation);
        park.setAvgRating(expectedAvgRating);
        park.setTags(new HashSet<>(expectedTags));

        when(parkDao.getPark(expectedId)).thenReturn(park);

        GetParkRequest request = GetParkRequest.builder()
                .withId(expectedId)
                .build();

        // WHEN
        GetParkResult result = getParkActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedId, result.getParkModel().getId());
        assertEquals(expectedName, result.getParkModel().getName());
        assertEquals(expectedLocation, result.getParkModel().getLocation());
        assertEquals(expectedAvgRating, result.getParkModel().getAvgRating());
        assertEquals(expectedTags, result.getParkModel().getTags());
    }

    @Test
    public void handleRequest_parkNotFound_throwsParkNotFoundException() {
        // GIVEN
        String id = "someId";
        GetParkRequest request = GetParkRequest.builder().withId(id).build();
        when(parkDao.getPark(id)).thenThrow(ParkNotFoundException.class);

        // WHEN && THEN
        assertThrows(ParkNotFoundException.class, () -> getParkActivity
                .handleRequest(request, null));
    }
}
