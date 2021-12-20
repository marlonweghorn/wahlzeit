package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * All test cases of the class {@link Photo}.
 */
public class PhotoTest {

    @Test
    public void testSetAndGetCoordinate() {
        Photo photo = new Photo();
        Location location = new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 0));

        assertNull(photo.getLocation());

        photo.setLocation(location);

        assertEquals(photo.getLocation(), location);
    }
}
