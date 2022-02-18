package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.requests.CreateUserRequest;
import com.barkpark.models.requests.UpdateUserRequest;

import javax.inject.Inject;

/**
 * Accesses data for users using {@link User} to represent the model in DynamoDB.
 */
public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a {@link UserDao} object
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the users table
     */
    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Creates a user based on the incoming {@link CreateUserRequest} and saves it to the users table
     *
     * @param createUserRequest the request with which to create the {@link User}
     * @return the created and subsequently saved {@link User}
     */
    public User createUser(CreateUserRequest createUserRequest) {

        User user = new User();
        user.setId(createUserRequest.getId());
        user.setUsername(createUserRequest.getUsername());

        this.dynamoDBMapper.save(user);
        return user;
    }

    /**
     * Updates a user based on the incoming {@link UpdateUserRequest} and saves it to the users table
     *
     * @param updateUserRequest the request with which to update the {@link User}
     * @return the updated and subsequently saved {@link User}
     */
    public User updateUser(UpdateUserRequest updateUserRequest) {

        User user = new User();
        user.setId(updateUserRequest.getId());
        user.setUsername(updateUserRequest.getUsername());

        this.dynamoDBMapper.save(user);
        return user;
    }
}
