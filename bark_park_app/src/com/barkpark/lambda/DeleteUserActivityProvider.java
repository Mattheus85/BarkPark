package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.DeleteUserRequest;
import com.barkpark.models.results.DeleteUserResult;

public class DeleteUserActivityProvider implements RequestHandler<DeleteUserRequest, DeleteUserResult> {
    @Override
    public DeleteUserResult handleRequest(DeleteUserRequest input, Context context) {
        return DaggerServiceComponent.create().provideDeleteUserActivity().handleRequest(input, context);
    }
}
