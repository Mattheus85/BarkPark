package com.barkpark.activities.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolEvent;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.results.users.CreateUserResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateUserActivity implements RequestHandler<CognitoUserPoolEvent, CreateUserResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CreateUserResult handleRequest(CognitoUserPoolEvent event, Context context) {
        log.info("Received CreateUserRequest {}", event.getTriggerSource());

        String userId = event.getCallerContext().getClientId();
        String username = event.getUserName();

        if (userId == null || username == null) {
            throw new IllegalArgumentException("Must provide userId and username");
        }

        User user = userDao.createUser(userId, username);

        return CreateUserResult.builder()
                .withUserModel(ModelConverter.toUserModel(user))
                .build();
    }
}
