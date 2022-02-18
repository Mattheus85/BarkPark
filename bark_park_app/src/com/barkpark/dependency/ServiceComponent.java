package com.barkpark.dependency;

import com.barkpark.activities.CreateReviewActivity;
import com.barkpark.activities.CreateUserActivity;
import com.barkpark.activities.DeleteReviewActivity;
import com.barkpark.activities.DeleteUserActivity;
import com.barkpark.activities.GetLocationsActivity;
import com.barkpark.activities.GetParkActivity;
import com.barkpark.activities.GetReviewsActivity;
import com.barkpark.activities.GetParksActivity;
import com.barkpark.activities.GetUserActivity;
import com.barkpark.activities.UpdateReviewActivity;
import com.barkpark.activities.UpdateUserActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { MapperModule.class })
public interface ServiceComponent {
    CreateReviewActivity provideCreateReviewActivity();
    CreateUserActivity provideCreateUserActivity();
    DeleteReviewActivity provideDeleteReviewActivity();
    DeleteUserActivity provideDeleteUserActivity();
    GetLocationsActivity provideGetLocationsActivity();
    GetParkActivity provideGetParkActivity();
    GetReviewsActivity provideGetReviewsActivity();
    GetParksActivity provideGetParksActivity();
    GetUserActivity provideGetUserActivity();
    UpdateReviewActivity provideUpdateReviewActivity();
    UpdateUserActivity provideUpdateUserActivity();
}
