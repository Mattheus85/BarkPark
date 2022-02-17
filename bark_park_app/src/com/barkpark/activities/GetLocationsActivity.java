package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.GetLocationsRequest;
import com.barkpark.models.results.GetLocationsResult;

import javax.inject.Inject;

public class GetLocationsActivity implements RequestHandler<GetLocationsRequest, GetLocationsResult> {

    @Inject
    public GetLocationsActivity() {
    }

    @Override
    public GetLocationsResult handleRequest(GetLocationsRequest input, Context context) {
        return null;
    }
}
