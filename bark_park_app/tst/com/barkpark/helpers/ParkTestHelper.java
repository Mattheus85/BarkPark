package com.barkpark.helpers;

import com.barkpark.dynamodb.models.Park;
import com.google.common.collect.Sets;

import java.util.List;

public class ParkTestHelper {
    public ParkTestHelper() {
    }

    public static Park generatePark() {
        Park park = new Park();
        park.setId("314159");
        park.setName("Pi Park");
        park.setLocation("Circle, AK");
        park.setAvgRating(5d);
        park.setTags(Sets.newHashSet("A tag"));

        return park;
    }

    public static List<Park> generateParkListWith3Parks() {
        Park park1 = new Park();
        park1.setId("314159");
        park1.setName("Pi Park");
        park1.setLocation("Circle, AK");

        Park park2 = new Park();
        park2.setId("628318");
        park2.setName("Tau Park");
        park2.setLocation("Berkeley, CA");

        Park park3 = new Park();
        park3.setId("271828");
        park3.setName("Euler's Park");
        park3.setLocation("Berkeley, CA");

        return List.of(park1, park2, park3);
    }
}
