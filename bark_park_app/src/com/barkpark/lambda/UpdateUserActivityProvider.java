package com.barkpark.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.DaggerServiceComponent;
import com.barkpark.models.requests.UpdateUserRequest;
import com.barkpark.models.results.UpdateUserResult;

public class UpdateUserActivityProvider implements RequestHandler<UpdateUserRequest, UpdateUserResult> {
    @Override
    public UpdateUserResult handleRequest(UpdateUserRequest input, Context context) {
        return DaggerServiceComponent.create().provideUpdateUserActivity().handleRequest(input, context);
    }
}
