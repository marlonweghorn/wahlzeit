package org.wahlzeit.model;

public class Location {

    private Coordinate coordinate;

    public Location(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     * @methodtype factory
     */
    public static Location getInstance(final String location) {
        String[] xyz = location.split(" ");
        final double x = Double.parseDouble(xyz[0]);
        final double y = Double.parseDouble(xyz[1]);
        final double z = Double.parseDouble(xyz[2]);

        return new Location(new Coordinate(x, y, z));
    }

    /**
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     *
     * @methodtype set
     */
    public void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     * @methodtype conversion
     */
    public String asString() {
        return coordinate.asString();
    }
}
