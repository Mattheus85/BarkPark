package com.barkpark;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;
import com.barkpark.models.results.locations.GetLocationsResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.barkpark.dynamodb.models.Park.LOCATION_AVG_RATING_INDEX;

public class TempTest {
    public static void main(String[] args) {


        DynamoDBMapper mapper =  new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new ProfileCredentialsProvider("barkpark"))
                .withRegion(Regions.US_WEST_1)
                .build());

        ParkDao parkDao = new ParkDao(mapper);

        List<Park> parkList;

        try {
            parkList = parkDao.getAllParks();
        } catch (ParksNotFoundException parksNotFoundException) {
            throw new LocationsNotFoundException("No park locations found", parksNotFoundException);
        }

        Set<String> locationSet = new HashSet<>();
        for (Park park : parkList) {
            locationSet.add(park.getLocation());
        }

        GetLocationsResult getLocationsResult = GetLocationsResult.builder()
                .withLocationSet(locationSet)
                .build();
//
//        List<Park> parks = parkDao.getParksByLocationAndAvgRating("Berkeley, CA", 4d);
//
//        for (Park park : parks) {
//            System.out.println(park.getName());
//        }
//
//        ReviewDao reviewDao = new ReviewDao(mapper);
//
//        Review deletedReview = reviewDao.deleteReview("314159", "123456");

    }
}
