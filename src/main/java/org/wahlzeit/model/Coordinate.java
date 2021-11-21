package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;

public abstract class Coordinate extends DataObject {

    public static final int NUM_VALID_DECIMAL_PLACES = 6;
    public static final double EPSILON = 1 / Math.pow(10, NUM_VALID_DECIMAL_PLACES);


    /**
     *
     * @methodtype conversion
     */
    public abstract String asString();

    /**
     *
     * @methodtype conversion
     */
    public abstract CartesianCoordinate asCartesianCoordinate();

    /**
     *
     * @methodtype get
     */
    public abstract double getCartesianDistance(final Coordinate coordinate);

    /**
     *
     * @methodtype conversion
     */
    public abstract SphericCoordinate asSphericCoordinate();

    /**
     *
     * @methodtype get
     */
    public abstract double getCentralAngle(final Coordinate coordinate);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return isEqual(other);
    }

    /**
     *
     * @methodtype boolean-query
     */
    public abstract boolean isEqual(final Coordinate coordinate);

    public abstract int hashCode();
}
