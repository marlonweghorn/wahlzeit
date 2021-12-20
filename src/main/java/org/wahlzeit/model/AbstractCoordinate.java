package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;
import static org.wahlzeit.utils.Assertions.assertIsNonNullArgument;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    protected abstract CartesianCoordinate _asCartesianCoordinate();
    protected abstract SphericCoordinate _asSphericCoordinate();

    protected boolean _isEqual(final Coordinate coordinate) {
        /* intentionally left blank -- may be implemented in subclass */
        return false;
    }

    protected double _getCartesianDistance(final Coordinate coordinate) {
        /* intentionally left blank -- may be implemented in subclass */
        return 0.0;
    }

    protected double _getCentralAngle(final Coordinate coordinate) {
        /* intentionally left blank -- may be implemented in subclass */
        return 0.0;
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        CartesianCoordinate cartesianCoordinate = _asCartesianCoordinate();
        assertClassInvariants();

        return cartesianCoordinate;
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        SphericCoordinate sphericCoordinate = _asSphericCoordinate();
        assertClassInvariants();

        return sphericCoordinate;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertClassInvariants();
        assertIsNonNullArgument(coordinate);

        double cartesianDistance = _asCartesianCoordinate()._getCartesianDistance(coordinate);

        assertClassInvariants();

        return cartesianDistance;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        assertClassInvariants();
        assertIsNonNullArgument(coordinate);

        double centralAngle = _asSphericCoordinate()._getCentralAngle(coordinate);

        assertClassInvariants();

        return centralAngle;
    }

    /**
     *
     * @methodtype boolean-query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        assertClassInvariants();
        assertIsNonNullArgument(coordinate);

        boolean equals = _asCartesianCoordinate()._isEqual(coordinate);

        assertClassInvariants();

        return equals;
    }
}
