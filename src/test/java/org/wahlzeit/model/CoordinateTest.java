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
        assertEquals(distance, 6174.574398, 1e-6);

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
    public void testCoordinateIsEqual() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        assertTrue(coordinate.isEqual(coordinate));
        assertTrue(coordinate.isEqual(new Coordinate(0, 0, 0)));
    }

    @Test
    public void testCoordinateIsNotEqual() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        assertFalse(coordinate.isEqual(new Coordinate(42, 0, 0)));
        assertFalse(coordinate.isEqual(new Coordinate(0, 42, 0)));
        assertFalse(coordinate.isEqual(new Coordinate(0, 0, 42)));
        assertFalse(coordinate.isEqual(null));
    }

    @Test
    public void testAsString() {
        Coordinate coordinate = new Coordinate(0, 0, 0);

        String coordinate_string = "0.0 0.0 0.0";

        assertEquals(coordinate.asString(), coordinate_string);
    }
}
