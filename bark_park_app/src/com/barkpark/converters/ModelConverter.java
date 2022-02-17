package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.models.ParkModel;

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
                .withTags(park.getTags().isEmpty() ? null : new HashSet<>(park.getTags()))
                .build();
    }

    /**
     * Converts a provided {@link List<ParkModel>} into a {@link List<ParkModel>} representation.
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
}
