package com.barkpark.converters;

import com.barkpark.dynamodb.models.Park;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationSetConverter {

    /**
     * Converts a provided {@link List<Park>} into a {@link Set<String>} representation of locations.
     * @param parkList the parkList to convert
     * @return the converted locationSet
     */
    public static Set<String> convertToSet(List<Park> parkList) {
        Set<String> locationSet = new HashSet<>();
        for (Park park : parkList) {
            locationSet.add(park.getLocation());
        }

        return locationSet;
    }
}
