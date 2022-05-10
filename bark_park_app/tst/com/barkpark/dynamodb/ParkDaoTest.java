package com.barkpark.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ParkDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private ParkDao parkDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        parkDao = new ParkDao(dynamoDBMapper);
    }

    @Test
    public void getPark_parkExists_returnPark() {
        // GIVEN - a known park id
        String testId = "314159";
        String testName = "Pi Park";
        String testLocation = "San Francisco";

        Park testPark = new Park();

        testPark.setId(testId);
        testPark.setName(testName);
        testPark.setLocation(testLocation);

        when(dynamoDBMapper.load(Park.class, testId)).thenReturn(testPark);

        // WHEN
        Park result = parkDao.getPark(testId);

        // THEN
        assertNotNull(result);
        assertEquals(testPark.getId(), result.getId(), "Park IDs do not match");
        assertEquals(testPark.getName(), result.getName(), "Park names do not match");
        assertEquals(testPark.getLocation(), result.getLocation(), "Park locations do not match");
    }

    @Test
    public void getPark_parkDoesNotExist_throwsParkNotFoundException() {
        // GIVEN - an unknown park id
        String testId = "628318";

        when(dynamoDBMapper.load(Park.class, testId)).thenReturn(null);

        // WHEN & THEN
        ParkNotFoundException thrown = assertThrows(
                ParkNotFoundException.class,
                () ->  parkDao.getPark(testId),
                "Expected getPark to throw a ParkNotFoundException"
        );


        assertEquals(thrown.getMessage(), "Could not find park with id: " + testId);
    }

    @Test
    public void getAllParks_parksExist_returnParksList() {
        // GIVEN

        PaginatedScanList<Park> mockPaginatedScanList = mock (PaginatedScanList.class);

        when(dynamoDBMapper.scan(eq(Park.class), Mockito.any())).thenReturn(mockPaginatedScanList);

        // WHEN
        List<Park> result = parkDao.getAllParks();

        // THEN
        assertNotNull(result);
    }

    @Test
    public void getAllParks_parksDoNotExist_throwsParksNotFoundException() {
        // GIVEN - no parks in table

        when(dynamoDBMapper.scan(eq(Park.class), Mockito.any())).thenReturn(null);

        // WHEN & THEN
        ParksNotFoundException thrown = assertThrows(
                ParksNotFoundException.class,
                () ->  parkDao.getAllParks(),
                "Expected getAllParks to throw a ParksNotFoundException"
        );


        assertEquals(thrown.getMessage(), "No parks found.");
    }
}
