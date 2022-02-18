package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.models.requests.GetLocationsRequest;
import com.barkpark.models.results.GetLocationsResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

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

        return GetLocationsResult.builder()
                .withLocationModel(ModelConverter.toLocationModelSet(parkDao.getLocations()))
                .build();
    }
}
