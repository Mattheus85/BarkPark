package com.barkpark.activities.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.requests.users.UpdateUserRequest;
import com.barkpark.models.results.users.UpdateUserResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateUserActivity implements RequestHandler<UpdateUserRequest, UpdateUserResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public UpdateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UpdateUserResult handleRequest(UpdateUserRequest updateUserRequest, Context context) {
        log.info("Received UpdateUserRequest {}", updateUserRequest);

        if (updateUserRequest.getId() == null || updateUserRequest.getUsername() == null) {
            throw new IllegalArgumentException("Must provide userId and username");
        }

        User user = userDao.updateUser(updateUserRequest);

        return UpdateUserResult.builder()
                .withUserModel(ModelConverter.toUserModel(user))
                .build();
    }
}
