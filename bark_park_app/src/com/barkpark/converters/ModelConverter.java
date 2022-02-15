package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;
import com.barkpark.models.ParkModel;

public class ModelConverter {
    /**
     * Converts a provided {@link Park} into a {@link ParkModel} representation.
     * @param park the park to convert
     * @return the converted parkModel
     */
    // I'm implementing this method as static since I see no reason why we would need an instance of ModelConverter
    // We can change to the Playlist approach if desired or discuss this further
    public static ParkModel toParkModel(Park park) {
        // Playlist checked if tags was empty and set the model to null if so...
        // Need to review WHY this was being done and if we should do something similar

        return ParkModel.builder()
                .withId(park.getId())
                .withName(park.getName())
                .withLocation(park.getLocation())
                .withAvgRating(park.getAvgRating())
                .withTags(park.getTags())
                .build();
    }
}
