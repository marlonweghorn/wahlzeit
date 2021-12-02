package org.wahlzeit.model;

public interface Coordinate {

    int NUM_VALID_DECIMAL_PLACES = 6;
    double EPSILON = 1 / Math.pow(10, NUM_VALID_DECIMAL_PLACES);

    /**
     *
     * @methodtype assertion
     */
    void assertClassInvariants();

    /**
     *
     * @methodtype conversion
     */
    String asString();

    /**
     *
     * @methodtype conversion
     */
    CartesianCoordinate asCartesianCoordinate();

    /**
     *
     * @methodtype get
     */
    double getCartesianDistance(final Coordinate coordinate);

    /**
     *
     * @methodtype conversion
     */
    SphericCoordinate asSphericCoordinate();

    /**
     *
     * @methodtype get
     */
    double getCentralAngle(final Coordinate coordinate);

    /**
     *
     * @methodtype boolean-query
     */
    boolean isEqual(final Coordinate coordinate);
}
