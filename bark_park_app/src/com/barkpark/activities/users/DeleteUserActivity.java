package com.barkpark.activities.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.users.DeleteUserRequest;
import com.barkpark.models.results.users.DeleteUserResult;

import javax.inject.Inject;

public class DeleteUserActivity implements RequestHandler<DeleteUserRequest, DeleteUserResult> {

    @Inject
    public DeleteUserActivity() {
    }

    @Override
    public DeleteUserResult handleRequest(DeleteUserRequest input, Context context) {
        return null;
    }
}
