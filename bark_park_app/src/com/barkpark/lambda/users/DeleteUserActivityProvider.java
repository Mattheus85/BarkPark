package com.barkpark.lambda.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.users.DeleteUserRequest;
import com.barkpark.models.results.users.DeleteUserResult;

public class DeleteUserActivityProvider implements RequestHandler<DeleteUserRequest, DeleteUserResult> {
    @Override
    public DeleteUserResult handleRequest(DeleteUserRequest input, Context context) {
        return DaggerServiceComponent.create().provideDeleteUserActivity().handleRequest(input, context);
    }
}
