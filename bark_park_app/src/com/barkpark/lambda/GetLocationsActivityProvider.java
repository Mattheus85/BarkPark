package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.GetLocationsRequest;
import com.barkpark.models.results.GetLocationsResult;

public class GetLocationsActivityProvider implements RequestHandler<GetLocationsRequest, GetLocationsResult> {
    @Override
    public GetLocationsResult handleRequest(GetLocationsRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetLocationsActivity().handleRequest(input, context);
    }
}
