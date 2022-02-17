package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.UpdateReviewRequest;
import com.barkpark.models.results.UpdateReviewResult;

public class UpdateReviewActivityProvider implements RequestHandler<UpdateReviewRequest, UpdateReviewResult> {
    @Override
    public UpdateReviewResult handleRequest(UpdateReviewRequest input, Context context) {
        return DaggerServiceComponent.create().provideUpdateReviewActivity().handleRequest(input, context);
    }
}
