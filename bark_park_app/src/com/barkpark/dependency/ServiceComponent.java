package com.barkpark.dependency;

import com.barkpark.activities.GetParkActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { MapperModule.class })
public interface ServiceComponent {
    GetParkActivity provideGetParkActivity();
}
