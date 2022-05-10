package com.barkpark.lambda.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.reviews.UpdateReviewRequest;
import com.barkpark.models.results.reviews.UpdateReviewResult;

public class UpdateReviewActivityProvider implements RequestHandler<UpdateReviewRequest, UpdateReviewResult> {
    @Override
    public UpdateReviewResult handleRequest(UpdateReviewRequest input, Context context) {
        return DaggerServiceComponent.create().provideUpdateReviewActivity().handleRequest(input, context);
    }
}
