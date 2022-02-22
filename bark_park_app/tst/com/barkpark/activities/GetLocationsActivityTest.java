package com.barkpark.activities;

import com.barkpark.activities.locations.GetLocationsActivity;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;
import com.barkpark.helpers.ParkTestHelper;
import com.barkpark.models.requests.locations.GetLocationsRequest;
import com.barkpark.models.results.locations.GetLocationsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetLocationsActivityTest {
    @Mock
    private ParkDao parkDao;

    @Mock
    private GetLocationsRequest getLocationsRequest;

    private GetLocationsActivity getLocationsActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getLocationsActivity = new GetLocationsActivity(parkDao);
    }

    @Test
    public void handleRequest_withValidRequest_returnsCorrectGetLocationsResult() {
        // GIVEN
        List<Park> parkListWith3Parks = ParkTestHelper.generateParkListWith3Parks();

        String city1 = "Circle, AK";
        String city2 = "Berkeley, CA";

        Set<String> locationSet = new HashSet<>();

        locationSet.add(city1);
        locationSet.add(city2);

        when(parkDao.getAllParks()).thenReturn(parkListWith3Parks);

        // WHEN
        GetLocationsResult result = getLocationsActivity.handleRequest(getLocationsRequest, null);

        // THEN
        assertEquals(locationSet.size(), result.getLocationSet().size(),
                "Result size should be equal to expected size");
        assertTrue(parkListWith3Parks.size() > result.getLocationSet().size(),
                "Result size should be less than initial park list size");
        assertEquals(locationSet, result.getLocationSet(),
                "Result location set should be equal to initial location set.");
    }

    @Test
    public void handleRequest_locationsNotFound_throwsLocationsNotFoundException() {
        // GIVEN
        when(parkDao.getAllParks()).thenThrow(ParksNotFoundException.class);

        // WHEN && THEN
        assertThrows(LocationsNotFoundException.class, () -> getLocationsActivity
                .handleRequest(getLocationsRequest, null));
    }
}
