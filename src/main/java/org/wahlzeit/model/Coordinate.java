package org.wahlzeit.model;

public class Coordinate {

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

        return this.equals(coordinate) ||
                (this.x == coordinate.getX() && this.y == coordinate.getY() && this.z == coordinate.getZ());
    }

    /**
     *
     * @methodtype conversion
     */
    public String asString() {
        return x + " " + y + " " + z;
    }
}
