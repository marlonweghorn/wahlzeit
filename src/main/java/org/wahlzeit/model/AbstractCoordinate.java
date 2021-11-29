package org.wahlzeit.model;


import org.wahlzeit.services.DataObject;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return isEqual(other);
    }
}
