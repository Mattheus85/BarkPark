package com.barkpark.activities;

import com.barkpark.activities.users.UpdateUserActivity;
import com.barkpark.dynamodb.UserDao;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.requests.users.UpdateUserRequest;
import com.barkpark.models.results.users.UpdateUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateUserActivityTest {
    @Mock
    private UserDao userDao;

    private UpdateUserActivity updateUserActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateUserActivity = new UpdateUserActivity(userDao);
    }

    @Test
    public void handleRequest_withProperRequest_returnsCorrectUpdateUserResult() {
        // GIVEN
        String id = "expectedId";
        String username = "expectedUsername";

        User user = new User();
        user.setId(id);
        user.setUsername(username);

        when(userDao.updateUser(any(UpdateUserRequest.class))).thenReturn(user);

        UpdateUserRequest request = UpdateUserRequest.builder()
                .withId(id)
                .withUsername(username)
                .build();

        // WHEN
        UpdateUserResult result = updateUserActivity.handleRequest(request, null);

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
        UpdateUserRequest request = UpdateUserRequest.builder()
                .withId(id)
                .build();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> updateUserActivity.handleRequest(request, null));
    }
}
