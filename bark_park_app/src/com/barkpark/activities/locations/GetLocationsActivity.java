package com.barkpark.activities.locations;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;
import com.barkpark.models.requests.locations.GetLocationsRequest;
import com.barkpark.models.results.locations.GetLocationsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetLocationsActivity implements RequestHandler<GetLocationsRequest, GetLocationsResult> {
    private final Logger log = LogManager.getLogger();
    private final ParkDao parkDao;

    @Inject
    public GetLocationsActivity(ParkDao parkDao) {
        this.parkDao = parkDao;
    }

    @Override
    public GetLocationsResult handleRequest(GetLocationsRequest getLocationsRequest, Context context) {
        log.info("Received GetLocationsRequest {}", getLocationsRequest);

        List<Park> parkList;

        try {
            parkList = parkDao.getAllParks();
        } catch (ParksNotFoundException parksNotFoundException) {
            log.warn("Exception thrown from GetLocationsActivity: ", parksNotFoundException);
            throw new LocationsNotFoundException("No park locations found", parksNotFoundException);
        }

        Set<String> locationSet = new HashSet<>();
        for (Park park : parkList) {
            locationSet.add(park.getLocation());
        }

        return GetLocationsResult.builder()
                .withLocationSet(locationSet)
                .build();
    }
}
