package com.barkpark.activities;

import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Location;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.models.requests.GetLocationsRequest;
import com.barkpark.models.requests.GetParksRequest;
import com.barkpark.models.results.GetLocationsResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

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
        
        List<Location> locationList = new ArrayList<>();
        
        Location location1 = new Location();
        location1.setLocation(city1);
        locationList.add(location1);
        
        Location location2 = new Location();
        location2.setLocation(city2);
        locationList.add(location2);
        
        when(parkDao.getLocations()).thenReturn(locationList);
        GetLocationsRequest request = GetLocationsRequest.builder().build();

        // WHEN
        GetLocationsResult result = getLocationsActivity.handleRequest(request, null);

        // THEN
        assertEquals(locationList.size(), result.getLocationModelList().size(),
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
