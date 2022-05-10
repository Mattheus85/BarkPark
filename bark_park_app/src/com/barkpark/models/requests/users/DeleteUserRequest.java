package com.barkpark.models.requests.users;

public class DeleteUserRequest {
    private String userId;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(Builder builder) {
        this.userId = builder.userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static final class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public DeleteUserRequest build() {
            return new DeleteUserRequest(this);
        }
    }
}
