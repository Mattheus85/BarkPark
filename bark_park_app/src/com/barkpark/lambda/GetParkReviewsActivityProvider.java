package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.GetParkReviewsRequest;
import com.barkpark.models.results.GetParkReviewsResult;

public class GetParkReviewsActivityProvider implements RequestHandler<GetParkReviewsRequest, GetParkReviewsResult> {
    @Override
    public GetParkReviewsResult handleRequest(GetParkReviewsRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetParkReviewsActivity().handleRequest(input, context);
    }
}
