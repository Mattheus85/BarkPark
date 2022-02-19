package com.barkpark.activities;

import com.barkpark.activities.locations.GetLocationsActivity;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.models.requests.locations.GetLocationsRequest;
import com.barkpark.models.results.locations.GetLocationsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetLocationsActivityTest {
    @Mock
    private ParkDao parkDao;

    private GetLocationsActivity getLocationsActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getLocationsActivity = new GetLocationsActivity(parkDao);
    }

    @Test
    public void handleRequest_withProperRequest_returnsCorrectGetLocationsResult() {
        // GIVEN
        String city1 = "Circle, AK";
        String city2 = "Berkeley, CA";
        
        Set<String> locationSet = new HashSet<>();

        locationSet.add(city1);
        locationSet.add(city2);
        
        when(parkDao.getLocations()).thenReturn(locationSet);
        GetLocationsRequest request = GetLocationsRequest.builder().build();

        // WHEN
        GetLocationsResult result = getLocationsActivity.handleRequest(request, null);

        // THEN
        assertEquals(locationSet.size(), result.getLocationModel().getLocationSet().size(),
                "Result size should be equal to expected size");
    }

    @Test
    public void handleRequest_locationsNotFound_throwsLocationsNotFoundException() {
        // GIVEN
        GetLocationsRequest request = GetLocationsRequest.builder().build();
        when(parkDao.getLocations()).thenThrow(LocationsNotFoundException.class);

        // WHEN && THEN
        assertThrows(LocationsNotFoundException.class, () -> getLocationsActivity
                .handleRequest(request, null));
    }
}
