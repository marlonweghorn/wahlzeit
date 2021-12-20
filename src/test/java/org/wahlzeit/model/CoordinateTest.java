package org.wahlzeit.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.ResultSet;
import java.sql.SQLException;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * All test cases of the class {@link AbstractCoordinate}.
 */
public class CoordinateTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCartesianSerialization() throws SQLException {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(42, 73, 6174);
        ResultSet rset = mock(ResultSet.class);

        cartesianCoordinate.writeOn(rset);

        verify(rset, times(1)).updateDouble("x", cartesianCoordinate.getX());
        verify(rset, times(1)).updateDouble("y", cartesianCoordinate.getY());
        verify(rset, times(1)).updateDouble("z", cartesianCoordinate.getZ());
    }

    @Test
    public void testSphericSerialization() throws SQLException {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(42, 73, 6174);
        SphericCoordinate sphericCoordinate = cartesianCoordinate.asSphericCoordinate();

        ResultSet rset = mock(ResultSet.class);

        sphericCoordinate.writeOn(rset);

        verify(rset, times(1)).updateDouble("x", cartesianCoordinate.getX());
        verify(rset, times(1)).updateDouble("y", cartesianCoordinate.getY());
        verify(rset, times(1)).updateDouble("z", cartesianCoordinate.getZ());
    }


    @Test
    public void testCartesianInterpretation() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        for (int i = 0; i < 1_000_000; i++) {
            cartesianCoordinate = cartesianCoordinate
                    .asSphericCoordinate()
                    .asCartesianCoordinate();
        }
        assertEquals(cartesianCoordinate.getX(), 1, AbstractCoordinate.EPSILON);
        assertEquals(cartesianCoordinate.getY(), 2, AbstractCoordinate.EPSILON);
        assertEquals(cartesianCoordinate.getZ(), 3, AbstractCoordinate.EPSILON);
    }

    @Test
    public void testSphericInterpretation() {
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0.6405223,1.1071485, 3.7416573);
        for (int i = 0; i < 1_000_000; i++) {
            sphericCoordinate = sphericCoordinate
                    .asCartesianCoordinate()
                    .asSphericCoordinate();
        }
        assertEquals(sphericCoordinate.getPhi(), 0.6405223, AbstractCoordinate.EPSILON);
        assertEquals(sphericCoordinate.getTheta(), 1.1071485, AbstractCoordinate.EPSILON);
        assertEquals(sphericCoordinate.getRadius(), 3.7416573, AbstractCoordinate.EPSILON);
    }

    @Test
    public void testGetCartesianDistance_1() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
        CartesianCoordinate cartesianCoordinateOther = CartesianCoordinate.getCartesianCoordinate(10, 11, 12);

        double cartesianDistance;

        cartesianDistance = cartesianCoordinate.getCartesianDistance(cartesianCoordinateOther);
        assertEquals(cartesianDistance, 15.588457, AbstractCoordinate.EPSILON);

        SphericCoordinate sphericCoordinateOther = cartesianCoordinateOther.asSphericCoordinate();

        cartesianDistance = cartesianCoordinate.getCartesianDistance(sphericCoordinateOther);
        assertEquals(cartesianDistance, 15.588457, AbstractCoordinate.EPSILON);
    }

    @Test
    public void testGetCartesianDistance_2() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
        CartesianCoordinate cartesianCoordinateOther = CartesianCoordinate.getCartesianCoordinate(42, 73, 6174);
        double cartesianDistance;

        cartesianDistance = cartesianCoordinate.getCartesianDistance(cartesianCoordinateOther);
        assertEquals(cartesianDistance, 6174.574398, AbstractCoordinate.EPSILON);

        SphericCoordinate sphericCoordinateOther = cartesianCoordinateOther.asSphericCoordinate();

        cartesianDistance = cartesianCoordinate.getCartesianDistance(sphericCoordinateOther);
        assertEquals(cartesianDistance, 6174.574398, AbstractCoordinate.EPSILON);
    }

    @Test
    public void testGetCentralAngle() {
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0.6405223,1.1071485, 3.7416573);
        SphericCoordinate sphericCoordinateOther = SphericCoordinate.getSphericCoordinate(0.8916760, 0.8329812, 19.1049713);
        double centralAngle;

        centralAngle = sphericCoordinate.getCentralAngle(sphericCoordinateOther);
        assertEquals(centralAngle, 0.318145, AbstractCoordinate.EPSILON);

        CartesianCoordinate cartesianCoordinateOther = sphericCoordinateOther.asCartesianCoordinate();

        centralAngle = sphericCoordinate.getCentralAngle(cartesianCoordinateOther);
        assertEquals(centralAngle, 0.318145, AbstractCoordinate.EPSILON);
    }

    @Test
    public void testCartesianCoordinateEquals() {
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);

        assertEquals(coordinate, coordinate);

        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(AbstractCoordinate.EPSILON, 0, 0));
        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(0, AbstractCoordinate.EPSILON, 0));
        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(0, 0, AbstractCoordinate.EPSILON));

        double epsilon_smaller = AbstractCoordinate.EPSILON - AbstractCoordinate.EPSILON * .1;

        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(epsilon_smaller, 0, 0));
        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(0, epsilon_smaller, 0));
        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(0, 0, epsilon_smaller));
    }

    @Test
    public void testCartesianCoordinateNotEquals() {
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);

        double epsilon_greater = AbstractCoordinate.EPSILON + AbstractCoordinate.EPSILON * .1;

        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(epsilon_greater , 0, 0));
        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(0, epsilon_greater, 0));
        assertNotEquals(coordinate, CartesianCoordinate.getCartesianCoordinate(0, 0, epsilon_greater));
        assertNotEquals(coordinate, null);
    }

    @Test
    public void testPreConditions() {
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0.6405223,1.1071485, 3.7416573);

        try {
            sphericCoordinate.getCentralAngle(null);
            assert false;
        } catch (AssertionError ae) {
            assert true;
        }

        try {
            sphericCoordinate.getCartesianDistance(null);
            assert false;
        } catch (AssertionError ae) {
            assert true;
        }

        try {
            sphericCoordinate.isEqual(null);
            assert false;
        } catch (AssertionError ae) {
            assert true;
        }
    }

    @Test
    public void testSphericClassInvariants() {
        SphericCoordinate sphericCoordinate;

        // Test e.g. the first class invariant
        try {
            sphericCoordinate = SphericCoordinate.getSphericCoordinate(-1, 1.1071485, 3.7416573);
            sphericCoordinate.asSphericCoordinate();
            assert false;
        } catch (AssertionError ae) {
            assert true;
        }

        try {
            sphericCoordinate = SphericCoordinate.getSphericCoordinate(2 * Math.PI, 1.1071485, 3.7416573);
            sphericCoordinate.asSphericCoordinate();
            assert false;
        } catch (AssertionError ae) {
            assert true;
        }
    }
}
