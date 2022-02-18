package com.barkpark.activities;

import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.User;
import com.barkpark.exceptions.UserNotFoundException;
import com.barkpark.models.requests.GetUserRequest;
import com.barkpark.models.results.GetUserResult;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetUserActivityTest {
    @Mock
    private UserDao userDao;

    private GetUserActivity getUserActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getUserActivity = new GetUserActivity(userDao);
    }

    @Test
    public void handleRequest_savedUserFound_returnsUserModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedUsername = "expectedUsername";

        User user = new User();
        user.setId(expectedId);
        user.setUsername(expectedUsername);

        when(userDao.getUser(expectedId)).thenReturn(user);

        GetUserRequest request = GetUserRequest.builder()
                .withId(expectedId)
                .build();

        // WHEN
        GetUserResult result = getUserActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedId, result.getUserModel().getId());
        assertEquals(expectedUsername, result.getUserModel().getUsername());
    }

    @Test
    public void handleRequest_userNotFound_throwsUserNotFoundException() {
        // GIVEN
        String id = "someId";
        GetUserRequest request = GetUserRequest.builder().withId(id).build();
        when(userDao.getUser(id)).thenThrow(UserNotFoundException.class);

        // WHEN && THEN
        assertThrows(UserNotFoundException.class, () -> getUserActivity
                .handleRequest(request, null));
    }
}
