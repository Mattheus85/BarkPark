package com.barkpark.lambda.parks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.parks.GetParkRequest;
import com.barkpark.models.results.parks.GetParkResult;

public class GetParkActivityProvider implements RequestHandler<GetParkRequest, GetParkResult> {
    @Override
    public GetParkResult handleRequest(GetParkRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetParkActivity().handleRequest(input, context);
    }
}
