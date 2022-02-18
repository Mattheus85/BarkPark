package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.GetReviewsRequest;
import com.barkpark.models.results.GetReviewsResult;

import javax.inject.Inject;

public class GetReviewsActivity implements RequestHandler<GetReviewsRequest, GetReviewsResult> {

    @Inject
    public GetReviewsActivity() {
    }

    @Override
    public GetReviewsResult handleRequest(GetReviewsRequest input, Context context) {
        return null;
    }
}
