package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.LocationsNotFoundException;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.barkpark.dynamodb.models.Park.LOCATION_AVG_RATING_INDEX;


/**
 * Accesses data for a park using {@link Park} to represent the model in DynamoDB.
 */
public class ParkDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a ParkDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the parks table
     */
    @Inject
    public ParkDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link Park} corresponding to the specified id.
     *
     * @param id the park ID
     * @return the stored park, or throw {@link ParkNotFoundException} if none was found.
     */
    public Park getPark(String id) throws ParkNotFoundException {
        Park park = this.dynamoDbMapper.load(Park.class, id);

        if (park == null) {
            throw new ParkNotFoundException("Could not find park with id: " + id);
        }

        return park;
    }

    /**
     * Returns a {@link List<Park>} of all stored parks.
     *
     * @return the list of stored Parks, or throw {@link ParksNotFoundException} if none was found.
     */
    public List<Park> getAllParks() throws ParksNotFoundException {
        List<Park> parks = dynamoDbMapper.scan(Park.class, new DynamoDBScanExpression());

        if (parks == null || parks.isEmpty()) {
            throw new ParksNotFoundException("No parks found.");
        }

        return parks;
    }

    /**
     * Returns a {@link List<Park>} of stored parks filtered by location.
     *
     * @param location the location to filter by
     * @return the list of stored Parks that match the given location, or throw {@link ParksNotFoundException} if none was found.
     */
    public List<Park> getParksByLocation(String location) throws ParksNotFoundException {

        Park partitionKey = new Park();
        partitionKey.setLocation(location);

        DynamoDBQueryExpression<Park> queryExpression = new DynamoDBQueryExpression<Park>()
                .withHashKeyValues(partitionKey)
                .withIndexName(LOCATION_AVG_RATING_INDEX)
                .withConsistentRead(false);

        List<Park> parks =  dynamoDbMapper.query(Park.class, queryExpression);

        if (parks == null || parks.isEmpty()) {
            throw new ParksNotFoundException("No parks found in " + location);
        }
        return parks;
    }

    /**
     * Returns a {@link List<Park>} of all stored parks.
     *
     * @return the list of stored Parks, or throw {@link ParksNotFoundException} if none was found.
     */
    public List<Park> getParksByAvgRating(Double avgRating) throws ParksNotFoundException {
        Map<String, AttributeValue> valueMap = new HashMap<>();

        // Should avgRating be a String???
        // What does making it a Double cause any issues???
        valueMap.put(":avgRating", new AttributeValue().withN(avgRating.toString()));

        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                .withFilterExpression("avgRating >= :avgRating")
                .withExpressionAttributeValues(valueMap)
                .withConsistentRead(false);

        List<Park> parks = dynamoDbMapper.scan(Park.class, dynamoDBScanExpression);

        if (parks == null || parks.isEmpty()) {
            throw new ParksNotFoundException("No parks found with rating greater than " + avgRating);
        }
        return parks;
    }

    /**
     * Returns a {@link List<Park>} of all stored parks.
     *
     * @return the list of stored Parks, or throw {@link ParksNotFoundException} if none was found.
     */
    public List<Park> getParksByLocationAndAvgRating(String location, Double avgRating) throws ParksNotFoundException {
        Park partitionKey = new Park();
        partitionKey.setLocation(location);
        partitionKey.setAvgRating(avgRating);

        Condition condition = new Condition();
        condition.withComparisonOperator(ComparisonOperator.GE)
                .withAttributeValueList(new AttributeValue().withN(avgRating.toString())); //Set your search value here

        DynamoDBQueryExpression<Park> queryExpression = new DynamoDBQueryExpression<Park>()
                .withHashKeyValues(partitionKey)
                .withRangeKeyCondition("avgRating", condition)
                .withIndexName(LOCATION_AVG_RATING_INDEX)
                .withConsistentRead(false);

        List<Park> parks =  dynamoDbMapper.query(Park.class, queryExpression);

        if (parks == null || parks.isEmpty()) {
            throw new ParksNotFoundException("No parks found in " + location);
        }
        return parks;
    }
}
