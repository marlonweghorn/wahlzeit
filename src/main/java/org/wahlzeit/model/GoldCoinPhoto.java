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

    private GoldCoin goldCoin = null;

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
        if (goldCoin != null) {
            this.goldCoin.setKarat(rset.getDouble("karat"));
        }
    }

    /**
     *
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        if (goldCoin != null) {
            rset.updateDouble("karat", this.goldCoin.getKarat());
        }
    }

    /**
     *
     * @methodtype set
     */
    public void setGoldCoin(final GoldCoin goldCoin) {
        this.goldCoin = goldCoin;
    }

    /**
     *
     * @methodtype boolean-query
     */
    public boolean hasSameOwner(GoldCoinPhoto photo) {
        return super.hasSameOwner(photo);
    }
}
