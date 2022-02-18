package com.barkpark.activities;

import com.barkpark.activities.users.CreateUserActivity;
import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.requests.users.CreateUserRequest;
import com.barkpark.models.results.users.CreateUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateUserActivityTest {
    @Mock
    private UserDao userDao;

    private CreateUserActivity createUserActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createUserActivity = new CreateUserActivity(userDao);
    }

    @Test
    public void handleRequest_withProperRequest_returnsCorrectCreateUserResult() {
        // GIVEN
        String id = "expectedId";
        String username = "expectedUsername";
        
        User user = new User();
        user.setId(id);
        user.setUsername(username);

        when(userDao.createUser(any(CreateUserRequest.class))).thenReturn(user);

        CreateUserRequest request = CreateUserRequest.builder()
                .withId(id)
                .withUsername(username)
                .build();

        // WHEN
        CreateUserResult result = createUserActivity.handleRequest(request, null);

        //THEN
        assertEquals(id, result.getUserModel().getId(),
                "Expected the result id to be " + id);
        assertEquals(username, result.getUserModel().getUsername(),
                "Expected the result username to be " + username);
    }

    @Test
    public void handleRequest_withImproperRequest_throwsIllegalArgumentException() {
        // GIVEN
        String id = "expectedId";
        CreateUserRequest request = CreateUserRequest.builder()
                .withId(id)
                .build();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> createUserActivity.handleRequest(request, null));
    }
}
