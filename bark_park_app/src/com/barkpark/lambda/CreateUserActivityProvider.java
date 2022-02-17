package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.CreateUserRequest;
import com.barkpark.models.results.CreateUserResult;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult> {
    @Override
    public CreateUserResult handleRequest(CreateUserRequest input, Context context) {
        return DaggerServiceComponent.create().provideCreateUserActivity().handleRequest(input, context);
    }
}
