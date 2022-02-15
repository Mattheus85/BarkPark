package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.GetParkRequest;
import com.barkpark.models.results.GetParkResult;

public class GetParkActivityProvider implements RequestHandler<GetParkRequest, GetParkResult> {

    @Override
    public GetParkResult handleRequest(GetParkRequest getParkRequest, Context context) {

        return DaggerServiceComponent
                .create()
                .provideGetParkActivity()
                .handleRequest(getParkRequest, context);
    }
}
