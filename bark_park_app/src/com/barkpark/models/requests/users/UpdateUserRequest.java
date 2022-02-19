package com.barkpark.models.requests.users;

public class UpdateUserRequest {
    private String id;
    private String username;

    public UpdateUserRequest() {
    }

    private UpdateUserRequest(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String username;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UpdateUserRequest build() {
            return new UpdateUserRequest(this);
        }
    }
}
