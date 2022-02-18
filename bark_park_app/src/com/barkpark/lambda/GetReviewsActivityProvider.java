package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.GetReviewsRequest;
import com.barkpark.models.results.GetReviewsResult;

public class GetReviewsActivityProvider implements RequestHandler<GetReviewsRequest, GetReviewsResult> {
    @Override
    public GetReviewsResult handleRequest(GetReviewsRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetReviewsActivity().handleRequest(input, context);
    }
}
