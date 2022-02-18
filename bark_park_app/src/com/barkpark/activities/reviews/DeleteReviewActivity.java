package com.barkpark.activities.reviews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.reviews.DeleteReviewRequest;
import com.barkpark.models.results.reviews.DeleteReviewResult;

import javax.inject.Inject;

public class DeleteReviewActivity implements RequestHandler<DeleteReviewRequest, DeleteReviewResult> {

    @Inject
    public DeleteReviewActivity() {
    }

    @Override
    public DeleteReviewResult handleRequest(DeleteReviewRequest input, Context context) {
        return null;
    }
}
