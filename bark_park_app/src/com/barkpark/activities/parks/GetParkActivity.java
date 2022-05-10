package com.barkpark.activities.parks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.models.ParkModel;
import com.barkpark.models.requests.parks.GetParkRequest;
import com.barkpark.models.results.parks.GetParkResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetParkActivity for the BarkPark GetPark API.
 *
 * This API allows the customer to get a single saved park.
 */
public class GetParkActivity implements RequestHandler<GetParkRequest, GetParkResult> {
    private final Logger log = LogManager.getLogger();
    private final ParkDao parkDao;

    /**
     * Instantiates a new GetParkActivity object.
     *
     * @param parkDao ParkDao to access the parks table.
     */
    @Inject
    public GetParkActivity(ParkDao parkDao) {
        this.parkDao = parkDao;
    }

    /**
     * This method handles the incoming request by retrieving the park from the database.
     *
     * If the park does not exist, this should throw a ParkNotFoundException.
     *
     * @param getParkRequest request object containing the park ID
     * @return getParkResult result object containing the API defined {@link ParkModel}
     */
    @Override
    public GetParkResult handleRequest(final GetParkRequest getParkRequest, Context context) throws ParkNotFoundException {
        log.info("Received GetParkRequest {}", getParkRequest);

        String requestId = getParkRequest.getId();

        // Throws ParkNotFoundException
        Park park = parkDao.getPark(requestId);

        ParkModel parkModel = ModelConverter.toParkModel(park);

        return GetParkResult.builder()
                .withParkModel(parkModel)
                .build();
    }
}
