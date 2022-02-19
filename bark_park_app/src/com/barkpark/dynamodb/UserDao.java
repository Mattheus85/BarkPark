package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.barkpark.dynamodb.models.User;
import com.barkpark.exceptions.UserNotFoundException;
import com.barkpark.models.requests.users.CreateUserRequest;
import com.barkpark.models.requests.users.UpdateUserRequest;

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
     * Returns the {@link User} corresponding to the specified id.
     *
     * @param id the User ID
     * @return the stored User, or throw {@link UserNotFoundException} if none was found.
     */
    public User getUser(String id) throws UserNotFoundException {
        User user = this.dynamoDBMapper.load(User.class, id);

        if (user == null) {
            throw new UserNotFoundException("Could not find user with id: " + id);
        }

        return user;
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

    public User deleteUser(String userId) throws UserNotFoundException {
        User user = getUser(userId);

        // Should this be in a try block
        // Review dynamodb exception handling conventions
        dynamoDBMapper.delete(user);

        return user;
    }
}
