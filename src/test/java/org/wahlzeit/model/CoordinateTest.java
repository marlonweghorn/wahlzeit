package org.wahlzeit.model;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {

    @Test
    public void testCartesianSerialization() throws SQLException {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(42, 73, 6174);
        ResultSet rset = mock(ResultSet.class);

        cartesianCoordinate.writeOn(rset);

        verify(rset, times(1)).updateDouble("x", cartesianCoordinate.getX());
        verify(rset, times(1)).updateDouble("y", cartesianCoordinate.getY());
        verify(rset, times(1)).updateDouble("z", cartesianCoordinate.getZ());
    }

    @Test
    public void testSphericSerialization() throws SQLException {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(42, 73, 6174);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();

        ResultSet rset = mock(ResultSet.class);

        sphericCoordinate.writeOn(rset);

        verify(rset, times(1)).updateDouble("x", cartesianCoordinate.getX());
        verify(rset, times(1)).updateDouble("y", cartesianCoordinate.getY());
        verify(rset, times(1)).updateDouble("z", cartesianCoordinate.getZ());
    }


    @Test
    public void testCartesianInterpretation() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 2, 3);
        for (int i = 0; i < 1_000_000; i++) {
            cartesianCoordinate = cartesianCoordinate
                    .asSphericCoordinate()
                    .asCartesianCoordinate();
        }
        assertEquals(cartesianCoordinate.getX(), 1, Coordinate.EPSILON);
        assertEquals(cartesianCoordinate.getY(), 2, Coordinate.EPSILON);
        assertEquals(cartesianCoordinate.getZ(), 3, Coordinate.EPSILON);
    }

    @Test
    public void testSphericInterpretation() {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(0.6405223,1.1071485, 3.7416573);
        for (int i = 0; i < 1_000_000; i++) {
            sphericCoordinate = sphericCoordinate
                    .asCartesianCoordinate()
                    .asSphericCoordinate();
        }
        assertEquals(sphericCoordinate.getPhi(), 0.6405223, Coordinate.EPSILON);
        assertEquals(sphericCoordinate.getTheta(), 1.1071485, Coordinate.EPSILON);
        assertEquals(sphericCoordinate.getRadius(), 3.7416573, Coordinate.EPSILON);
    }

    @Test
    public void testGetCartesianDistance_1() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 2, 3);
        CartesianCoordinate cartesianCoordinateOther = new CartesianCoordinate(10, 11, 12);
        double cartesianDistance;

        cartesianDistance = cartesianCoordinate.getCartesianDistance(cartesianCoordinateOther);
        assertEquals(cartesianDistance, 15.588457, Coordinate.EPSILON);

        SphericCoordinate sphericCoordinateOther = cartesianCoordinateOther.asSphericCoordinate();

        cartesianDistance = cartesianCoordinate.getCartesianDistance(sphericCoordinateOther);
        assertEquals(cartesianDistance, 15.588457, Coordinate.EPSILON);
    }

    @Test
    public void testGetCartesianDistance_2() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 0, 0);
        CartesianCoordinate cartesianCoordinateOther = new CartesianCoordinate(42, 73, 6174);
        double cartesianDistance;

        cartesianDistance = cartesianCoordinate.getCartesianDistance(cartesianCoordinateOther);
        assertEquals(cartesianDistance, 6174.574398, Coordinate.EPSILON);

        SphericCoordinate sphericCoordinateOther = cartesianCoordinateOther.asSphericCoordinate();

        cartesianDistance = cartesianCoordinate.getCartesianDistance(sphericCoordinateOther);
        assertEquals(cartesianDistance, 6174.574398, Coordinate.EPSILON);
    }

    @Test
    public void testGetSphericDistance() {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(0.6405223,1.1071485, 3.7416573);
        SphericCoordinate sphericCoordinateOther = new SphericCoordinate(0.8916760, 0.8329812, 19.1049713);
        double centralAngle;

        centralAngle = sphericCoordinate.getCentralAngle(sphericCoordinateOther);
        assertEquals(centralAngle, 0.318145, Coordinate.EPSILON);

        CartesianCoordinate cartesianCoordinateOther = sphericCoordinateOther.asCartesianCoordinate();

        centralAngle = sphericCoordinate.getCentralAngle(cartesianCoordinateOther);
        assertEquals(centralAngle, 0.318145, Coordinate.EPSILON);
    }

    @Test
    public void testCartesianCoordinateEquals() {
        CartesianCoordinate coordinate = new CartesianCoordinate(0, 0, 0);

        assertEquals(coordinate, coordinate);

        assertEquals(coordinate, new CartesianCoordinate(Coordinate.EPSILON, 0, 0));
        assertEquals(coordinate, new CartesianCoordinate(0, Coordinate.EPSILON, 0));
        assertEquals(coordinate, new CartesianCoordinate(0, 0, Coordinate.EPSILON));

        double epsilon_smaller = Coordinate.EPSILON - Coordinate.EPSILON * .1;

        assertEquals(coordinate, new CartesianCoordinate(epsilon_smaller, 0, 0));
        assertEquals(coordinate, new CartesianCoordinate(0, epsilon_smaller, 0));
        assertEquals(coordinate, new CartesianCoordinate(0, 0, epsilon_smaller));
    }

    @Test
    public void testCartesianCoordinateNotEquals() {
        CartesianCoordinate coordinate = new CartesianCoordinate(0, 0, 0);

        double epsilon_greater = Coordinate.EPSILON + Coordinate.EPSILON * .1;

        assertNotEquals(coordinate, new CartesianCoordinate(epsilon_greater , 0, 0));
        assertNotEquals(coordinate, new CartesianCoordinate(0, epsilon_greater, 0));
        assertNotEquals(coordinate, new CartesianCoordinate(0, 0, epsilon_greater));
        assertNotEquals(coordinate, null);
    }
}
