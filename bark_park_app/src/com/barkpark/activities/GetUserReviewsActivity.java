package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.GetUserReviewsRequest;
import com.barkpark.models.results.GetUserReviewsResult;

import javax.inject.Inject;

public class GetUserReviewsActivity implements RequestHandler<GetUserReviewsRequest, GetUserReviewsResult> {

    @Inject
    public GetUserReviewsActivity() {
    }

    @Override
    public GetUserReviewsResult handleRequest(GetUserReviewsRequest input, Context context) {
        return null;
    }
}
