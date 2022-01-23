package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.wahlzeit.utils.Assertions.assertIsNonNullArgument;

public class GoldCoinType {
    private GoldCoinType superType = null;
    private final Set<GoldCoinType> subTypes = new HashSet<>();

    private final String name;
    private double karat;

    public GoldCoinType(final String name) {
        this.name = name;
    }

    /**
     *
     * @methodtype factory
     */
    public GoldCoin createInstance() {
        return new GoldCoin(this);
    }

    /**
     *
     * @methodtype get
     */
    public GoldCoinType getSuperType() {
        return superType;
    }

    /**
     *
     * @methodtype set
     */
    public void setSuperType(final GoldCoinType goldCoinType) {
        superType = goldCoinType;
    }

    /**
     *
     * @methodtype get
     */
    public Iterator<GoldCoinType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    /**
     *
     * @methodtype command
     */
    public void addSubType(final GoldCoinType goldCoinType) {
        assertIsNonNullArgument(goldCoinType);

        goldCoinType.setSuperType(this);
        subTypes.add(goldCoinType);
    }

    /**
     *
     * @methodtype boolean
     */
    public boolean isSubType(final GoldCoin goldCoin) {
        assertIsNonNullArgument(goldCoin);

        if (goldCoin.getType() == this) {
            return false;
        }

        GoldCoinType _superType = getSuperType();

        while (_superType != null) {
            if (goldCoin.getType() == _superType) {
                return true;
            }
            _superType = _superType.getSuperType();
        }

        return false;
    }

    /**
     *
     * @methodtype boolean
     */
    public boolean hasInstance(final GoldCoin goldCoin) {
        assertIsNonNullArgument(goldCoin);

        if (goldCoin.getType() == this) {
            return true;
        }

        for (GoldCoinType goldCoinType : subTypes) {
            if (goldCoinType.hasInstance(goldCoin)) {
                return true;
            }
        }

        return  false;
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
}
