package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.DeleteReviewRequest;
import com.barkpark.models.results.DeleteReviewResult;

public class DeleteReviewActivityProvider implements RequestHandler<DeleteReviewRequest, DeleteReviewResult> {
    @Override
    public DeleteReviewResult handleRequest(DeleteReviewRequest input, Context context) {
        return DaggerServiceComponent.create().provideDeleteReviewActivity().handleRequest(input, context);
    }
}
