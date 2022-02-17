package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.UpdateReviewRequest;
import com.barkpark.models.results.UpdateReviewResult;

import javax.inject.Inject;

public class UpdateReviewActivity implements RequestHandler<UpdateReviewRequest, UpdateReviewResult> {

    @Inject
    public UpdateReviewActivity() {
    }

    @Override
    public UpdateReviewResult handleRequest(UpdateReviewRequest input, Context context) {
        return null;
    }
}
