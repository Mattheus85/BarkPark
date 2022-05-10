package com.barkpark.lambda.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolEvent;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPostConfirmationEvent;
import com.barkpark.dependency.DaggerServiceComponent;

public class CreateUserActivityProvider implements RequestHandler<CognitoUserPoolPostConfirmationEvent,
        CognitoUserPoolPostConfirmationEvent> {
    @Override
    public CognitoUserPoolPostConfirmationEvent handleRequest(CognitoUserPoolPostConfirmationEvent input,
                                                              Context context) {
        return DaggerServiceComponent.create().provideCreateUserActivity().handleRequest(input, context);
    }
}
