package com.barkpark.lambda.parks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.parks.GetParksRequest;
import com.barkpark.models.results.parks.GetParksResult;

public class GetParksActivityProvider implements RequestHandler<GetParksRequest, GetParksResult> {
    @Override
    public GetParksResult handleRequest(GetParksRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetParksActivity().handleRequest(input, context);
    }
}
