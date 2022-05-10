package com.barkpark.lambda.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.users.GetUserRequest;
import com.barkpark.models.results.users.GetUserResult;

public class GetUserActivityProvider implements RequestHandler<GetUserRequest, GetUserResult> {
    @Override
    public GetUserResult handleRequest(GetUserRequest input, Context context) {
        return DaggerServiceComponent.create().provideGetUserActivity().handleRequest(input, context);
    }
}
