package org.wahlzeit.model;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

    /*
    @Test
    public void testSerialization() throws SQLException {
        Coordinate coordinate = mock(Coordinate.class);
        ResultSet rset = mock(ResultSet.class);
        Location location = new Location(coordinate);

        location.writeOn(rset);

        verify(coordinate, times(1)).writeOn(rset);
    }

    @Test
    public void testGetCoordinate() {
        Coordinate coordinate = new CartesianCoordinate(0, 0, 0);
        Location location = new Location(coordinate);

        assertTrue(location.getCoordinate().isEqual(coordinate));
    }

    @Test
    public void testSetCoordinate() {
        Location location = new Location(new CartesianCoordinate(0, 0, 0));

        location.setCoordinate(new CartesianCoordinate(1, 1, 1));

        assertTrue(location.getCoordinate().isEqual(new CartesianCoordinate(1, 1, 1)));
    }

    @Test
    public void testAsString() {
        Location location = new Location(new CartesianCoordinate(0, 0, 0));

        String location_string = "0.0 0.0 0.0";

        assertEquals(location.asString(), location_string);
    }
    
     */
}
