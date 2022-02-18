package com.barkpark.models.results.users;

import com.barkpark.models.UserModel;

import java.util.Objects;

public class CreateUserResult {
    private UserModel userModel;

    public CreateUserResult() {
    }

    private CreateUserResult(Builder builder) {
        this.userModel = builder.userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserResult that = (CreateUserResult) o;
        return Objects.equals(userModel, that.userModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userModel);
    }

    @Override
    public String toString() {
        return "CreateUserResult{" +
                "userModel=" + userModel +
                '}';
    }

    public static final class Builder {
        private UserModel userModel;

        public Builder withUserModel(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public CreateUserResult build() {
            return new CreateUserResult(this);
        }
    }
}
