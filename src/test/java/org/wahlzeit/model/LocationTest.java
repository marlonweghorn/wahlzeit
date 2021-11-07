package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

    @Test
    public void testGetCoordinate() {
        Coordinate coordinate = new Coordinate(0, 0, 0);
        Location location = new Location(coordinate);

        assertTrue(location.getCoordinate().isEqual(coordinate));
    }

    @Test
    public void testSetCoordinate() {
        Location location = new Location(new Coordinate(0, 0, 0));

        location.setCoordinate(new Coordinate(1, 1, 1));

        assertTrue(location.getCoordinate().isEqual(new Coordinate(1, 1, 1)));
    }

    @Test
    public void testAsString() {
        Location location = new Location(new Coordinate(0, 0, 0));

        String location_string = "0.0 0.0 0.0";

        assertEquals(location.asString(), location_string);

    }

    @Test
    public void testGetInstance() {
        Location location = Location.getInstance(".0 0.0 0");

        assertTrue(location.getCoordinate().isEqual(new Coordinate(0, 0, 0)));
    }
}
