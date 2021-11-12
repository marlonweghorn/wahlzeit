package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {

    @Test
    public void testGetCoordinates() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        assertEquals(coordinate.getX(), 0, 0);
        assertEquals(coordinate.getY(), 0, 0);
        assertEquals(coordinate.getZ(), 0, 0);
    }

    @Test
    public void testSetCoordinates() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        coordinate.setCoordinate(1, 1, 1);

        assertEquals(coordinate.getX(), 1, 0);
        assertEquals(coordinate.getY(), 1, 0);
        assertEquals(coordinate.getZ(),1, 0);
    }

    @Test
    public void testEuclidianDistance() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        // Standard cases
        double distance = coordinate.getDistance(new Coordinate(42, 73, 6174));
        assertEquals(distance, 6174.574398, Coordinate.EPSILON);

        coordinate.setCoordinate(42, 73, 6174);

        distance = coordinate.getDistance(new Coordinate(42, 73, 6174));
        assertEquals(distance, 0, 0);

        // Edge cases
        coordinate.setCoordinate(0, 0, 0);

        distance = coordinate.getDistance(new Coordinate(0, 0, Double.MIN_VALUE));
        assertEquals(distance, 0, 0);

        distance = coordinate.getDistance(new Coordinate(0, 0, Double.MAX_VALUE));
        assertEquals(distance, Double.POSITIVE_INFINITY, 0);
    }

    @Test
    public void testCoordinateEquals() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        assertEquals(coordinate, coordinate);

        assertEquals(coordinate, new Coordinate(Coordinate.EPSILON, 0, 0));
        assertEquals(coordinate, new Coordinate(0, Coordinate.EPSILON, 0));
        assertEquals(coordinate, new Coordinate(0, 0, Coordinate.EPSILON));

        double epsilon_smaller = Coordinate.EPSILON - Coordinate.EPSILON * .1;

        assertEquals(coordinate, new Coordinate(epsilon_smaller, 0, 0));
        assertEquals(coordinate, new Coordinate(0, epsilon_smaller, 0));
        assertEquals(coordinate, new Coordinate(0, 0, epsilon_smaller));
    }

    @Test
    public void testCoordinateNotEquals() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        double epsilon_greater = Coordinate.EPSILON + Coordinate.EPSILON * .1;

        assertNotEquals(coordinate, new Coordinate(epsilon_greater , 0, 0));
        assertNotEquals(coordinate, new Coordinate(0, epsilon_greater, 0));
        assertNotEquals(coordinate, new Coordinate(0, 0, epsilon_greater));
        assertNotEquals(coordinate, null);
    }

    @Test
    public void testCoordinateIsNotEqual() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        assertNotEquals(coordinate, null);
    }

    @Test
    public void testAsString() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        String coordinate_string = "0.0 0.0 0.0";

        assertEquals(coordinate.asString(), coordinate_string);
    }
}
