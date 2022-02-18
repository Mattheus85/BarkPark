package com.barkpark.dynamodb.models;

import java.util.Objects;

public class Location {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location locations1 = (Location) o;
        return Objects.equals(getLocation(), locations1.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation());
    }

    @Override
    public String toString() {
        return "Location{" +
                "location='" + location + '\'' +
                '}';
    }
}
