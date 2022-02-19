package com.barkpark.lambda.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.reviews.CreateReviewRequest;
import com.barkpark.models.results.reviews.CreateReviewResult;

public class CreateReviewActivityProvider implements RequestHandler<CreateReviewRequest, CreateReviewResult> {
    @Override
    public CreateReviewResult handleRequest(CreateReviewRequest input, Context context) {
        return DaggerServiceComponent.create().provideCreateReviewActivity().handleRequest(input, context);
    }
}
