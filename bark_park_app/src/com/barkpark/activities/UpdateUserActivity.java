package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.dependency.ServiceComponent;
import com.barkpark.models.requests.UpdateUserRequest;
import com.barkpark.models.results.UpdateUserResult;

import javax.inject.Inject;

public class UpdateUserActivity implements RequestHandler<UpdateUserRequest, UpdateUserResult> {

    @Inject
    public UpdateUserActivity() {
    }

    @Override
    public UpdateUserResult handleRequest(UpdateUserRequest input, Context context) {
        ServiceComponent dagger = null;
        dagger.provideUpdateUserActivity();
        return null;
    }
}
