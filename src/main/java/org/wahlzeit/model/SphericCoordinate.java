package org.wahlzeit.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends Coordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(final double phi, final double theta, final double radius) {
        this.phi = phi; // lambda
        this.theta = theta;
        this.radius = radius;
    }

    /**
     *
     * @methodtype get
     */
    public double getPhi() {
        return phi;
    }

    /**
     *
     * @methodtype get
     */
    public double getTheta() {
        return theta;
    }

    /**
     *
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @methodtype set
     */
    public void setSphericCoordinate(final double phi, final double theta, final double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    public String asString() {
        return phi + " " + theta + " " + radius;
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        final double sinTheta = Math.sin(theta);
        final double cosTheta = Math.cos(theta);
        final double sinPhi = Math.sin(phi);
        final double cosPhi = Math.cos(phi);

        final double x = radius * cosTheta * sinPhi;
        final double y = radius * sinTheta * sinPhi;
        final double z = radius * cosPhi;


        final double xRounded =
                new BigDecimal(x).setScale(NUM_VALID_DECIMAL_PLACES + 1, RoundingMode.HALF_DOWN).doubleValue();
        final double yRounded =
                new BigDecimal(y).setScale(NUM_VALID_DECIMAL_PLACES + 1, RoundingMode.HALF_DOWN).doubleValue();
        final double zRounded =
                new BigDecimal(z).setScale(NUM_VALID_DECIMAL_PLACES + 1, RoundingMode.HALF_DOWN).doubleValue();

        return new CartesianCoordinate(xRounded, yRounded, zRounded);
    }

    /**
     *
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(final Coordinate coordinate) {
        final CartesianCoordinate thisCartesianCoordinate = this.asCartesianCoordinate();

        return thisCartesianCoordinate.getCartesianDistance(coordinate);
    }

    /**
     *
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    public double getCentralAngle(final Coordinate coordinate) {
        final SphericCoordinate other = coordinate.asSphericCoordinate();

        final double sum;
        final double thetaDiff = Math.abs(theta - other.getTheta());

        sum = Math.sin(phi) * Math.sin(other.getPhi())
                + Math.cos(phi) * Math.cos(other.getPhi()) * Math.cos(thetaDiff);

        return Math.acos(sum);
    }

    /**
     *
     * @methodtype boolean-query
     */
    @Override
    public boolean isEqual(final Coordinate coordinate) {
        SphericCoordinate other = coordinate.asSphericCoordinate();

        final boolean eqPhi = Math.abs(other.getPhi() - phi) <= EPSILON;
        final boolean eqTheta = Math.abs(other.getTheta() - theta) <= EPSILON;
        final boolean eqRadius = Math.abs(other.getRadius() - radius) <= EPSILON;

        return eqPhi && eqTheta && eqRadius;
    }

    @Override
    public int hashCode() {
        final CartesianCoordinate thisCartesianCoordinate = this.asCartesianCoordinate();

        final double x = thisCartesianCoordinate.getX();
        final double y = thisCartesianCoordinate.getY();
        final double z = thisCartesianCoordinate.getZ();

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
        final double x = rset.getDouble("x");
        final double y = rset.getDouble("y");
        final double z = rset.getDouble("z");

        final SphericCoordinate sphericCoordinate =
                new CartesianCoordinate(x, y, z).asSphericCoordinate();

        phi = sphericCoordinate.getPhi();
        theta = sphericCoordinate.getTheta();
        radius = sphericCoordinate.getRadius();
    }

    /**
     * @param rset
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        final CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();

        rset.updateDouble("x", cartesianCoordinate.getX());
        rset.updateDouble("y", cartesianCoordinate.getY());
        rset.updateDouble("z", cartesianCoordinate.getZ());
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
