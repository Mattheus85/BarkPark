package com.barkpark.activities.locations;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.LocationSetConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;
import com.barkpark.models.ParkModel;
import com.barkpark.models.requests.locations.GetLocationsRequest;
import com.barkpark.models.results.locations.GetLocationsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the GetLocationsActivity for the BarkPark GetLocations API.
 *
 * This API allows the customer to get a list of saved park locations
 */
public class GetLocationsActivity implements RequestHandler<GetLocationsRequest, GetLocationsResult> {
    private final Logger log = LogManager.getLogger();
    private final ParkDao parkDao;

    /**
     * Instantiates a new GetLocationsActivity object.
     *
     * @param parkDao ParkDao to access the parks table.
     */
    @Inject
    public GetLocationsActivity(ParkDao parkDao) {
        this.parkDao = parkDao;
    }

    /**
     * This method handles the incoming request by retrieving a list of parks from the database
     * and extracting the set of all locations with saved parks
     *
     * If the park does not exist, this should throw a ParkNotFoundException.
     *
     * @param getLocationsRequest request object
     * @return getLocationsResult result object containing the set of all locations with saved parks
     */
    @Override
    public GetLocationsResult handleRequest(GetLocationsRequest getLocationsRequest, Context context) throws LocationsNotFoundException {
        log.info("Received GetLocationsRequest {}", getLocationsRequest);

        List<Park> parkList;

        try {
            parkList = parkDao.getAllParks();
        } catch (ParksNotFoundException parksNotFoundException) {
            log.warn("Exception thrown from GetLocationsActivity: ", parksNotFoundException);
            throw new LocationsNotFoundException("No park locations found", parksNotFoundException);
        }

        Set<String> locationSet = LocationSetConverter.convertToSet(parkList);

        return GetLocationsResult.builder()
                .withLocationSet(locationSet)
                .build();
    }
}
