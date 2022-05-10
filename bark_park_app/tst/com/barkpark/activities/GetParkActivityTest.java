package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.barkpark.activities.parks.GetParkActivity;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.helpers.ParkTestHelper;
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

    @Mock
    Context context;

    private GetParkActivity getParkActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getParkActivity = new GetParkActivity(parkDao);
    }

    @Test
    public void handleRequest_withValidParkId_returnsCorrectParkModelInResult() {
        // GIVEN
        Park park = ParkTestHelper.generatePark();

        when(parkDao.getPark(park.getId())).thenReturn(park);

        GetParkRequest request = GetParkRequest.builder()
                .withId(park.getId())
                .build();

        // WHEN
        GetParkResult result = getParkActivity.handleRequest(request, context);

        // THEN
        assertEquals(park.getId(), result.getParkModel().getId());
        assertEquals(park.getName(), result.getParkModel().getName());
        assertEquals(park.getLocation(), result.getParkModel().getLocation());
        assertEquals(park.getAvgRating(), result.getParkModel().getAvgRating());
        assertEquals(park.getTags(), result.getParkModel().getTags());
    }

    @Test
    public void handleRequest_withInvalidParkId_throwsParkNotFoundException() {
        // GIVEN
        String invalidId = "someId";

        GetParkRequest request = GetParkRequest.builder()
                .withId(invalidId)
                .build();

        when(parkDao.getPark(invalidId)).thenThrow(ParkNotFoundException.class);

        // WHEN && THEN
        assertThrows(ParkNotFoundException.class, () -> getParkActivity
                .handleRequest(request, context));
    }
}
