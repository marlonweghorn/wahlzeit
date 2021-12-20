package org.wahlzeit.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class SphericCoordinate extends AbstractCoordinate {

    private static final ConcurrentHashMap<Integer, SphericCoordinate> sphericCoordinates = new ConcurrentHashMap<>();

    private final double phi;
    private final double theta;
    private final double radius;

    private SphericCoordinate(final double phi, final double theta, final double radius) {
        this.phi = phi;
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
     * @methodtype get
     */
    public static SphericCoordinate getSphericCoordinate(final double phi, final double theta, final double radius) {
        final int hash = Objects.hash(phi, theta, radius);
        SphericCoordinate sphericCoordinate = sphericCoordinates.get(hash);
        if (sphericCoordinate == null) {
            sphericCoordinate = new SphericCoordinate(phi, theta, radius);
            sphericCoordinates.put(hash, sphericCoordinate);
        }
        return sphericCoordinate;
    }

    /**
     *
     * @methodtype assertion
     */
    @Override
    public void assertClassInvariants() {
        assert phi >= 0 && phi < 2 * Math.PI;
        assert theta >= 0 && theta <= Math.PI;
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

        final double x = radius * cosPhi * sinTheta;
        final double y = radius * sinTheta * sinPhi;
        final double z = radius * cosTheta;

        final double xRounded =
                new BigDecimal(x).setScale(NUM_VALID_DECIMAL_PLACES + 1, RoundingMode.HALF_DOWN).doubleValue();
        final double yRounded =
                new BigDecimal(y).setScale(NUM_VALID_DECIMAL_PLACES + 1, RoundingMode.HALF_DOWN).doubleValue();
        final double zRounded =
                new BigDecimal(z).setScale(NUM_VALID_DECIMAL_PLACES + 1, RoundingMode.HALF_DOWN).doubleValue();

        return CartesianCoordinate.getCartesianCoordinate(xRounded, yRounded, zRounded);
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
        final double centralAngle;

        final double thetaDiff = Math.abs(theta - other.getTheta());

        sum = Math.sin(phi) * Math.sin(other.getPhi())
                + Math.cos(phi) * Math.cos(other.getPhi()) * Math.cos(thetaDiff);

        centralAngle = Math.acos(sum);

        assert 0 <= centralAngle && centralAngle <= 360;

        return centralAngle;
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
