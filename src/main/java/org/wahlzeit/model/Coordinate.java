package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
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
    public void setCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @methodtype get
     */
    public double getDistance(Coordinate coordinate) {
        assert coordinate != null;

        double sum = 0;

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
    public boolean isEqual(Coordinate coordinate) {
        assert coordinate != null;

        return this.equals(coordinate) ||
                (this.x == coordinate.getX() && this.y == coordinate.getY() && this.z == coordinate.getZ());
    }
}
