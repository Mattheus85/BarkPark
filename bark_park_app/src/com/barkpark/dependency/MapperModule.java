package com.barkpark.dependency;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MapperModule {
    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {
        // I noticed we could get a default client with this, but not sure if it works
        // Maybe not because of the no credentials thing?
//        return new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());

        return new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.US_WEST_1)
                .build());
    }
}
