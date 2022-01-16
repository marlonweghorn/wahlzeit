package org.wahlzeit.model;

import static org.wahlzeit.utils.Assertions.assertIsNonNullArgument;

public class GoldCoin {

    private static int _id = 0;

    private final int id;
    private final GoldCoinType goldCoinType;

    public GoldCoin(final GoldCoinType goldCoinType) {
        assertIsNonNullArgument(goldCoinType);

        this.id = _id++;  // TODO: Overflow possible.
        this.goldCoinType = goldCoinType;
    }

    /**
     *
     * @methodtype get
     */
    public GoldCoinType getType() {
        return goldCoinType;
    }

    /**
     *
     * @methodtype get
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @methodtype get
     */
    public double getKarat() {
        return goldCoinType.getKarat();
    }

    /**
     *
     * @methodtype set
     */
    public void setKarat(final double karat) {
        goldCoinType.setKarat(karat);
    }
}
