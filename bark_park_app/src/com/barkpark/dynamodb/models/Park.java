package com.barkpark.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a record in the parks table
 */
@DynamoDBTable(tableName = "parks")
public class Park {

    public static final String LOCATION_AVG_RATING_INDEX = "location-avgRating-index";

    private String id;
    private String name;
    private String location;
    private Double avgRating;
    private Set<String> tags;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBIndexHashKey(attributeName = "location", globalSecondaryIndexName = LOCATION_AVG_RATING_INDEX)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @DynamoDBIndexRangeKey(attributeName = "avgRating", globalSecondaryIndexName = LOCATION_AVG_RATING_INDEX)
    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    @DynamoDBAttribute(attributeName = "tags")
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return id.equals(park.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Park{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", avgRating=" + avgRating +
                ", tags=" + tags +
                '}';
    }
}
