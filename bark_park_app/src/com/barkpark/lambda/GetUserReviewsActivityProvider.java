package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.GetUserReviewsRequest;
import com.barkpark.models.results.GetUserReviewsResult;

public class GetUserReviewsActivityProvider implements RequestHandler<GetUserReviewsRequest, GetUserReviewsResult> {
    @Override
    public GetUserReviewsResult handleRequest(GetUserReviewsRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetUserReviewsActivity().handleRequest(input, context);
    }
}
