package com.barkpark.dependency;

import com.barkpark.activities.reviews.CreateReviewActivity;
import com.barkpark.activities.users.CreateUserActivity;
import com.barkpark.activities.reviews.DeleteReviewActivity;
import com.barkpark.activities.users.DeleteUserActivity;
import com.barkpark.activities.locations.GetLocationsActivity;
import com.barkpark.activities.parks.GetParkActivity;
import com.barkpark.activities.reviews.GetReviewsActivity;
import com.barkpark.activities.parks.GetParksActivity;
import com.barkpark.activities.users.GetUserActivity;
import com.barkpark.activities.reviews.UpdateReviewActivity;
import com.barkpark.activities.users.UpdateUserActivity;
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
