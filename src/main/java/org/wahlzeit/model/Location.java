package org.wahlzeit.model;

public class Location {

    public Coordinate coordinate;

    public Location(Coordinate coordinate) {
        assert coordinate != null;

        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
