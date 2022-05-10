package com.barkpark.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the reviews table
 */
@DynamoDBTable(tableName = "reviews")
public class Review {
    public static final String USER_ID_INDEX = "userId-index";
    private String parkId;
    private String userId;
    private String reviewTitle;
    private String reviewBody;
    private String date;
    private Double rating;

    @DynamoDBHashKey(attributeName = "parkId")
    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    @DynamoDBRangeKey(attributeName = "userId")
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = USER_ID_INDEX)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "reviewTitle")
    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    @DynamoDBAttribute(attributeName = "reviewBody")
    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return getParkId().equals(review.getParkId()) && getUserId().equals(review.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkId(), getUserId());
    }

    @Override
    public String toString() {
        return "Review{" +
                "parkId='" + parkId + '\'' +
                ", userId='" + userId + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewBody='" + reviewBody + '\'' +
                ", date='" + date + '\'' +
                ", rating=" + rating +
                '}';
    }
}
