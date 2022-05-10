package com.barkpark.activities.parks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.ParksNotFoundException;
import com.barkpark.models.ParkModel;
import com.barkpark.models.requests.parks.GetParksRequest;
import com.barkpark.models.results.parks.GetParksResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GetParksActivity for the BarkPark GetParks API.
 *
 * This API allows the customer to get list of parks, optionally filtered by
 * location, avgRating, or both.
 */
public class GetParksActivity implements RequestHandler<GetParksRequest, GetParksResult> {
    private final Logger log = LogManager.getLogger();
    private final ParkDao parkDao;

    /**
     * Instantiates a new GetParksActivity object.
     *
     * @param parkDao {@link ParkDao} object to access the parks table
     */
    @Inject
    public GetParksActivity(ParkDao parkDao) {
        this.parkDao = parkDao;
    }

    /**
     * This method handles the incoming request by retrieving a list of parks from the database.
     *
     * If no parks exist, this should throw a ParksNotFoundException.
     *
     * @param getParksRequest request object containing optional filtering parameters
     * @return getParksResult result object containing a list of API defined {@link ParkModel} objects
     */
    @Override
    public GetParksResult handleRequest(final GetParksRequest getParksRequest, Context context) throws ParksNotFoundException {
        log.info("Received GetParkRequest {}", getParksRequest);

        String location = getParksRequest.getLocation();
        Double avgRating = getParksRequest.getAvgRating();

        List<Park> parkList;
        if ((location != null && !location.isEmpty()) && avgRating != null) {
            parkList = parkDao.getParksByLocationAndAvgRating(location, avgRating);
        } else if (location != null && !location.isEmpty()) {
            parkList = parkDao.getParksByLocation(location);
        } else if (avgRating != null) {
            parkList = parkDao.getParksByAvgRating(avgRating);
        } else {
            parkList = parkDao.getAllParks();
        }

        List<ParkModel> parkModelList = ModelConverter.toParkModelList(parkList);

        return GetParksResult.builder()
                .withParkList(parkModelList)
                .build();
    }
}
