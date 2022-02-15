package com.barkpark.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the parks table
 */
@DynamoDBTable(tableName = "parks")
public class Park {
    private String id;
    private String name;
    private String location;
    private Integer avgRating;
    private List<String> tags;

    private List<String> reviews;
    // Playlists project implementation stored AlbumTrack objects directly in a list rather than by id.
    // This would be even better time complexity when getting reviews and may be easier to implement
    // Review with Matt and consider same approach for reviews

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

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = "avgRating") // What should we do when the Park has no reviews yet? Also need to decide WHEN we're calculating this
    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    @DynamoDBAttribute(attributeName = "tags")
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @DynamoDBAttribute(attributeName = "reviews") // This was converted to LinkedList in Playlist project
    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
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

    // equals and hashCode include ALL fields, including avgRating
    // We should discuss the case when a park has no reviews and adjust this accordingly
    // Also consider only checking based on required fields
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return getId().equals(park.getId()) && getName().equals(park.getName()) && getLocation().equals(park.getLocation()) && getAvgRating().equals(park.getAvgRating()) && getTags().equals(park.getTags()) && getReviews().equals(park.getReviews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLocation(), getAvgRating(), getTags(), getReviews());
    }
}
