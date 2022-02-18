package com.barkpark;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;

import java.util.List;

public class TempTest {
    public static void main(String[] args) {


        DynamoDBMapper mapper =  new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new ProfileCredentialsProvider("barkpark"))
                .withRegion(Regions.US_WEST_1)
                .build());

        ParkDao parkDao = new ParkDao(mapper);

        List<Park> parks = parkDao.getParksByLocationAndAvgRating("Berkeley, CA", 4d);

        for (Park park : parks) {
            System.out.println(park.getName());
        }

    }
}
