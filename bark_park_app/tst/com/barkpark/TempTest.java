package com.barkpark;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.barkpark.converters.LocationSetConverter;
import com.barkpark.dynamodb.ParkDao;
import com.barkpark.dynamodb.ReviewDao;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;
import com.barkpark.models.results.locations.GetLocationsResult;

import java.util.*;

import static com.barkpark.dynamodb.models.Park.LOCATION_AVG_RATING_INDEX;

public class TempTest {
    public static void main(String[] args) {


        DynamoDBMapper mapper =  new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new ProfileCredentialsProvider("barkpark"))
                .withRegion(Regions.US_WEST_1)
                .build());

        ParkDao parkDao = new ParkDao(mapper);
        ReviewDao reviewDao = new ReviewDao(mapper);

        String parkId = "271828";
//        List<Review> reviews = reviewDao.getReviewsByParkId(parkId);

        parkDao.updateAvgRating(parkId, new ArrayList<>());
    }
}
