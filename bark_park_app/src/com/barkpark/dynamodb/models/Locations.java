package com.barkpark.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "parks")
public class Locations {
    private List<String> locations;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "locations-index")
    public List<String> getLocations() {
        return locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locations locations1 = (Locations) o;
        return Objects.equals(getLocations(), locations1.getLocations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocations());
    }
}
