package org.wahlzeit.model;

import java.sql.*;

import org.wahlzeit.services.DataObject;

public class Location extends DataObject {

    private AbstractCoordinate coordinate;

    public Location(final AbstractCoordinate coordinate) {
        this.coordinate = coordinate;
    }


    /**
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     *
     * @methodtype set
     */
    public void setCoordinate(final AbstractCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     * @methodtype conversion
     */
    public String asString() {
        return coordinate.asString();
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
        this.coordinate.readFrom(rset);
    }

    /**
     * @param rset
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        this.coordinate.writeOn(rset);
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
