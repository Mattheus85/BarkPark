package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.models.ParkModel;

import java.util.ArrayList;
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

        // If park.tags is null or empty, set parkModel.tags to null
        Set<String> tagsSet = park.getTags();
        List<String> tags = tagsSet == null || tagsSet.isEmpty() ? null : new ArrayList<>(tagsSet);

        return ParkModel.builder()
                .withId(park.getId())
                .withName(park.getName())
                .withLocation(park.getLocation())
                .withAvgRating(park.getAvgRating())
                .withTags(tags)
                .build();
    }
}
