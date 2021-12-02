package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(final double x, final double y, final double z) {
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
     * @methodtype set
     */
    public void setCartesianCoordinate(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void assertClassInvariants() {

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
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(final Coordinate coordinate) {
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
    public SphericCoordinate asSphericCoordinate() throws ArithmeticException {
        final double radius = Math.sqrt(x * x + y * y + z * z);
        final double phi = Math.acos(z / radius);
        final double theta;

        if (x > 0) {
            theta = Math.atan(y / x);
        } else if (x == 0) {
            theta = Math.PI / 2;
        } else {
            theta = Math.atan(y / x) + Math.PI;
        }

        return new SphericCoordinate(phi, theta, radius);
    }

    /**
     *
     * @methodtype boolean-query
     */
    public boolean isEqual(final Coordinate coordinate) {
        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final boolean eqX = Math.abs(other.getX() - x) <= EPSILON;
        final boolean eqY = Math.abs(other.getY() - y) <= EPSILON;
        final boolean eqZ = Math.abs(other.getZ() - z) <= EPSILON;

        return eqX && eqY && eqZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
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
        this.x = rset.getDouble("x");
        this.y = rset.getDouble("y");
        this.z = rset.getDouble("z");
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
