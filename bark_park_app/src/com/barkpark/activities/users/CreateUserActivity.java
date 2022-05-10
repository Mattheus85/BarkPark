package com.barkpark.activities.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPostConfirmationEvent;
import com.barkpark.dynamodb.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateUserActivity implements RequestHandler<CognitoUserPoolPostConfirmationEvent,
        CognitoUserPoolPostConfirmationEvent> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CognitoUserPoolPostConfirmationEvent handleRequest(CognitoUserPoolPostConfirmationEvent event,
                                                              Context context) {
        log.info("Received CreateUserRequest {}", event);

        String userId = event.getRequest().getUserAttributes().get("sub");
        String username = event.getUserName();

        if (userId == null || username == null) {
            throw new IllegalArgumentException("Must provide userId and username");
        }

        userDao.createUser(userId, username);

        return event;
    }
}
