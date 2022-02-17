package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.models.requests.GetUserRequest;
import com.barkpark.models.results.GetUserResult;

import javax.inject.Inject;

public class GetUserActivity implements RequestHandler<GetUserRequest, GetUserResult> {

    @Inject
    public GetUserActivity() {
    }

    @Override
    public GetUserResult handleRequest(GetUserRequest input, Context context) {
        return null;
    }
}
