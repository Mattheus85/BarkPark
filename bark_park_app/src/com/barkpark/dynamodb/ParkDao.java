package com.barkpark.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.barkpark.dynamodb.models.Park;
import com.barkpark.exceptions.ParkNotFoundException;
import com.barkpark.exceptions.ParksNotFoundException;

import javax.inject.Inject;
import java.util.List;

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
     * @param id the Park ID
     * @return the stored Park, or throw {@link ParkNotFoundException} if none was found.
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
}
