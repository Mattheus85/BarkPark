package com.barkpark.lambda.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolEvent;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.users.CreateUserRequest;
import com.barkpark.models.results.users.CreateUserResult;

public class CreateUserActivityProvider implements RequestHandler<CognitoUserPoolEvent, CreateUserResult> {
    @Override
    public CreateUserResult handleRequest(CognitoUserPoolEvent input, Context context) {
        return DaggerServiceComponent.create().provideCreateUserActivity().handleRequest(input, context);
    }
}
