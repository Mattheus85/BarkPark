package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.dynamodb.models.Review;
import com.barkpark.dynamodb.models.User;
import com.barkpark.models.LocationModel;
import com.barkpark.models.ParkModel;
import com.barkpark.models.ReviewModel;
import com.barkpark.models.UserModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelConverter {
    /**
     * Converts a provided {@link Park} into a {@link ParkModel} representation.
     * @param park the park to convert
     * @return the converted parkModel
     */
    // Discuss choice to make these methods static with staff
    public static ParkModel toParkModel(Park park) {

        return ParkModel.builder()
                .withId(park.getId())
                .withName(park.getName())
                .withLocation(park.getLocation())
                .withAvgRating(park.getAvgRating())
                .withTags(park.getTags() == null ? null : new HashSet<>(park.getTags()))
                .build();
    }

    /**
     * Converts a provided {@link List<Park>} into a {@link List<ParkModel>} representation.
     * @param parkList the list parks to be converted
     * @return the converted parkModelList
     */
    public static List<ParkModel> toParkModelList(List<Park> parkList) {
        List<ParkModel> parkModelList = new ArrayList<>();

        for (Park park : parkList) {
            parkModelList.add(toParkModel(park));
        }

        return parkModelList;
    }

    /**
     * Converts a provided {@link Review} into a {@link ReviewModel} representation.
     * @param review the {@link Review} to convert
     * @return the converted {@link ReviewModel}
     */
    public static ReviewModel toReviewModel(Review review) {

        return ReviewModel.builder()
                .withParkId(review.getParkId())
                .withUserId(review.getUserId())
                .withReviewTitle(review.getReviewTitle())
                .withReviewBody(review.getReviewBody())
                .withDate(review.getDate())
                .withRating(review.getRating())
                .build();
    }

    /**
     * Converts a provided {@link User} into a {@link UserModel} representation.
     * @param user the {@link User} to convert
     * @return the converted {@link UserModel}
     */
    public static UserModel toUserModel(User user) {
        
        return UserModel.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .build();
    }

    /**
     * Converts a provided String Set into a {@link LocationModel} representation.
     * @param locationSet the set of locations to be converted
     * @return the converted {@link LocationModel}
     */
    public static LocationModel toLocationModelSet(Set<String> locationSet) {
        return LocationModel.builder().withLocationSet(locationSet).build();
    }
}
