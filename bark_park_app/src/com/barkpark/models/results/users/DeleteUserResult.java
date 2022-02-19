package com.barkpark.models.results.users;

import com.barkpark.models.UserModel;

import java.util.Objects;

public class DeleteUserResult {
    private UserModel userModel;

    private DeleteUserResult(Builder  builder) {
        this.userModel = builder.userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteUserResult that = (DeleteUserResult) o;
        return userModel.equals(that.userModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userModel);
    }

    @Override
    public String toString() {
        return "DeleteUserResult{" +
                "userModel=" + userModel +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserModel userModel;

        public Builder withUserModel(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public DeleteUserResult build() {
            return new DeleteUserResult(this);
        }
    }
}
