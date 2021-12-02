package org.wahlzeit.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends AbstractCoordinate {

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
     * @methodtype assertion
     */
    @Override
    public void assertClassInvariants() {
        assert radius > 0;
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
    protected CartesianCoordinate _asCartesianCoordinate() {
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
     * @methodtype conversion
     */
    @Override
    protected SphericCoordinate _asSphericCoordinate() {
        return this;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    protected double _getCentralAngle(final Coordinate coordinate) {
        final SphericCoordinate other = coordinate.asSphericCoordinate();

        final double sum;
        final double thetaDiff = Math.abs(theta - other.getTheta());

        sum = Math.sin(phi) * Math.sin(other.getPhi())
                + Math.cos(phi) * Math.cos(other.getPhi()) * Math.cos(thetaDiff);

        return Math.acos(sum);
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
        this.asCartesianCoordinate().readFrom(rset);
    }

    /**
     * @param rset
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        this.asCartesianCoordinate().writeOn(rset);
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
