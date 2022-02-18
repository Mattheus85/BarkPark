package com.barkpark.activities;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.barkpark.converters.ModelConverter;
import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.ParkModel;
import com.barkpark.models.UserModel;
import com.barkpark.models.requests.GetUserRequest;
import com.barkpark.models.results.GetParkResult;
import com.barkpark.models.results.GetUserResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetUserActivity implements RequestHandler<GetUserRequest, GetUserResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public GetUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public GetUserResult handleRequest(GetUserRequest getUserRequest, Context context) {
        log.info("Received GetUserRequest {}", getUserRequest);

        if (getUserRequest.getId() == null) {
            throw new IllegalArgumentException();
        }

        User user = userDao.getUser(getUserRequest.getId());

        return GetUserResult.builder()
                .withUserModel(ModelConverter.toUserModel(user))
                .build();
    }
}
