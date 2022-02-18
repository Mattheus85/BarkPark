package com.barkpark.lambda.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.reviews.GetReviewsRequest;
import com.barkpark.models.results.reviews.GetReviewsResult;

public class GetReviewsActivityProvider implements RequestHandler<GetReviewsRequest, GetReviewsResult> {
    @Override
    public GetReviewsResult handleRequest(GetReviewsRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetReviewsActivity().handleRequest(input, context);
    }
}
