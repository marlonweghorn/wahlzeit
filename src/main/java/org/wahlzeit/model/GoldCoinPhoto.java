package org.wahlzeit.model;

import org.wahlzeit.annotations.PatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;


@PatternInstance(
    patternName = "Abstract Factory",
    participants = {
        "ConcreteProduct"
    }
)
public class GoldCoinPhoto extends Photo {

    private double karat = 0;

    /**
     *
     */
    public GoldCoinPhoto() {
        super();
    }

    /**
     *
     * @methodtype constructor
     */
    public GoldCoinPhoto(PhotoId myId) {
        super(myId);
    }

    /**
     *
     * @methodtype constructor
     */
    public GoldCoinPhoto(ResultSet rset) throws SQLException {
        super(rset);
    }

    /**
     *
     */
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        this.karat = rset.getDouble("karat");
    }

    /**
     *
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        rset.updateDouble("karat", this.karat);
    }

    /**
     *
     * @methodtype get
     */
    public double getKarat() {
        return karat;
    }

    /**
     *
     * @methodtype set
     */
    public void setKarat(final double karat) {
        this.karat = karat;
    }

    /**
     *
     * @methodtype boolean-query
     */
    public boolean hasSameOwner(GoldCoinPhoto photo) {
        return super.hasSameOwner(photo);
    }
}
