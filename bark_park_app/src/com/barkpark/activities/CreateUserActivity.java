package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.CreateUserRequest;
import com.barkpark.models.results.CreateUserResult;

import javax.inject.Inject;

public class CreateUserActivity implements RequestHandler<CreateUserRequest, CreateUserResult> {

    @Inject
    public CreateUserActivity() {
    }

    @Override
    public CreateUserResult handleRequest(CreateUserRequest input, Context context) {
        return null;
    }
}
