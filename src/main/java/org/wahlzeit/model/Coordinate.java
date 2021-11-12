package org.wahlzeit.model;

import java.sql.*;
import java.util.Objects;

import org.wahlzeit.services.DataObject;

public class Coordinate extends DataObject {

    public static final double EPSILON = 1e-6;

    private double x;
    private double y;
    private double z;

    public Coordinate(final double x, final double y, final double z) {
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
    public void setCoordinate(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @methodtype get
     */
    public double getDistance(final Coordinate coordinate) {
        assert coordinate != null;

        final double sum;
        final double xDiff = x - coordinate.getX();
        final double yDiff = y - coordinate.getY();
        final double zDiff = z - coordinate.getZ();

        sum = (xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff);

        return Math.sqrt(sum);
    }

    /**
     *
     * @methodtype boolean-query
     */
    public boolean isEqual(final Coordinate coordinate) {
        if (coordinate == null) return false;

        final boolean eqX = Math.abs(coordinate.getX() - x) <= EPSILON;
        final boolean eqY = Math.abs(coordinate.getY() - y) <= EPSILON;
        final boolean eqZ = Math.abs(coordinate.getZ() - z) <= EPSILON;

        return eqX && eqY && eqZ;
    }


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

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
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
