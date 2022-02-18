package com.barkpark.dependency;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MapperModule {
    private static final String BARK_PARK = "barkpark";

    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {

        return new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new ProfileCredentialsProvider(BARK_PARK))
                .withRegion(Regions.US_WEST_1)
                .build());
    }
}
