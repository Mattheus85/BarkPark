package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.CreateReviewRequest;
import com.barkpark.models.results.CreateReviewResult;

import javax.inject.Inject;

public class CreateReviewActivity implements RequestHandler<CreateReviewRequest, CreateReviewResult> {

    @Inject
    public CreateReviewActivity() {
    }

    @Override
    public CreateReviewResult handleRequest(CreateReviewRequest input, Context context) {
        return null;
    }
}
