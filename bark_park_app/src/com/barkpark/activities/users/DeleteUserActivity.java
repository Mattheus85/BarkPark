package com.barkpark.activities.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.requests.users.DeleteUserRequest;
import com.barkpark.models.results.users.DeleteUserResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteUserActivity implements RequestHandler<DeleteUserRequest, DeleteUserResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public DeleteUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DeleteUserResult handleRequest(DeleteUserRequest deleteUserRequest, Context context) {

        String userId = deleteUserRequest.getUserId();

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("Must provide userId");
        }

        User deleteUser = userDao.deleteUser(userId);

        return DeleteUserResult.builder()
                .withUserModel(ModelConverter.toUserModel(deleteUser))
                .build();
    }
}
