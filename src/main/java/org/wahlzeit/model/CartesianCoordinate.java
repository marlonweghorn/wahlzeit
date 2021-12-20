package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public final class CartesianCoordinate extends AbstractCoordinate {

    private static final ConcurrentHashMap<Integer, CartesianCoordinate> cartesianCoordinates = new ConcurrentHashMap<>();

    private final double x;
    private final double y;
    private final double z;

    private CartesianCoordinate(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     *
     * @methodtype get
     */
    public static CartesianCoordinate getCartesianCoordinate(final double x, final double y, final double z) {
        final int hash = Objects.hash(x, y, z);
        CartesianCoordinate cartesianCoordinate = cartesianCoordinates.get(hash);
        if (cartesianCoordinate == null) {
            cartesianCoordinate = new CartesianCoordinate(x, y, z);
            cartesianCoordinates.put(hash, cartesianCoordinate);
        }
        return cartesianCoordinate;
    }

    /**
     *
     * @methodtype assertion
     */
    @Override
    public void assertClassInvariants() {
        /* intentionally left blank -- nothing to check */
    }

    /**
     *
     * @methodtype conversion
     */
    public String asString() {
        return x + " " + y + " " + z;
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    protected CartesianCoordinate _asCartesianCoordinate() {
        return this;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    protected double _getCartesianDistance(final Coordinate coordinate) {
        final CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final double sum;
        final double xDiff = x - other.getX();
        final double yDiff = y - other.getY();
        final double zDiff = z - other.getZ();

        sum = (xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff);

        return Math.sqrt(sum);
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    protected SphericCoordinate _asSphericCoordinate() {
        final double radius = Math.sqrt(x * x + y * y + z * z);

        if (radius <= EPSILON) {
            return SphericCoordinate.getSphericCoordinate(0, 0, 0);
        }

        final double theta = Math.acos(z / radius);
        final double phi;

        if (x > 0) {
            phi = Math.atan(y / x);
        } else if (x == 0) {
            phi = Math.PI / 2;
        } else {
            phi = Math.atan(y / x) + Math.PI;
        }

        return SphericCoordinate.getSphericCoordinate(phi, theta, radius);
    }

    /**
     *
     * @methodtype boolean-query
     */
    @Override
    protected boolean _isEqual(final Coordinate coordinate) {
        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final boolean eqX = Math.abs(other.getX() - x) <= EPSILON;
        final boolean eqY = Math.abs(other.getY() - y) <= EPSILON;
        final boolean eqZ = Math.abs(other.getZ() - z) <= EPSILON;

        return eqX && eqY && eqZ;
    }

    /**
     *
     */
    @Override
    public String getIdAsString() {
        /* intentionally left blank */
        return null;
    }

    /**
     * @param rset
     */
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        final double x = rset.getDouble("x");
        final double y = rset.getDouble("y");
        final double z = rset.getDouble("z");

        getCartesianCoordinate(x, y, z);
    }

    /**
     * @param rset
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("x", this.x);
        rset.updateDouble("y", this.y);
        rset.updateDouble("z", this.z);
    }

    /**
     * @param stmt
     * @param pos
     */
    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        /* intentionally left blank */
    }
}
