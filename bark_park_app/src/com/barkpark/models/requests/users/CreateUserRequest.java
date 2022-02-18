package com.barkpark.models.requests.users;

import java.util.Objects;

public class CreateUserRequest {
    private String id;
    private String username;

    public CreateUserRequest() {
    }

    private CreateUserRequest(Builder builder) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
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

        public CreateUserRequest build() {
            return new CreateUserRequest(this);
        }
    }
}
