package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import static org.wahlzeit.utils.Assertions.assertIsNonNullArgument;

public class GoldCoinManager {

    protected static final GoldCoinManager instance = new GoldCoinManager();

    public static GoldCoinManager getInstance() {
        return instance;
    }

    private final Map<String, GoldCoinType> goldCoinTypeMap = new HashMap<>();
    private final Map<Integer, GoldCoin> goldCoinMap = new HashMap<>();


    /**
     *
     * @methodtype factory
     */
    public GoldCoin createGoldCoin(final String typeName) {
        assertIsNonNullArgument(typeName);

        final GoldCoinType goldCoinType = getGoldCoinType(typeName);
        final GoldCoin goldCoin = goldCoinType.createInstance();
        this.goldCoinMap.put(goldCoin.getId(), goldCoin);

        return goldCoin;
    }

    /**
     *
     * @methodtype get or factory
     */
    public GoldCoinType getGoldCoinType(final String typeName) {
        if (goldCoinTypeMap.containsKey(typeName)) {
            return goldCoinTypeMap.get(typeName);
        } else {
            final GoldCoinType goldCoinType = new GoldCoinType(typeName);
            goldCoinTypeMap.put(typeName, goldCoinType);
            return goldCoinType;
        }
    }
}
