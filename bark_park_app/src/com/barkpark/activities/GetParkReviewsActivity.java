package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.GetParkReviewsRequest;
import com.barkpark.models.results.GetParkReviewsResult;

import javax.inject.Inject;

public class GetParkReviewsActivity implements RequestHandler<GetParkReviewsRequest, GetParkReviewsResult> {

    @Inject
    public GetParkReviewsActivity() {
    }

    @Override
    public GetParkReviewsResult handleRequest(GetParkReviewsRequest input, Context context) {
        return null;
    }
}
