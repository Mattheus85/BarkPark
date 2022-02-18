package com.barkpark.models;

import java.util.Objects;

public class UserModel {
    private String id;
    private String username;

    private UserModel(Builder builder) {
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
        UserModel userModel = (UserModel) o;
        return Objects.equals(getId(), userModel.getId()) && Objects.equals(getUsername(), userModel.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override
    public String toString() {
        return "UserModel{" +
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

        public UserModel build() {
            return new UserModel(this);
        }
    }
}
