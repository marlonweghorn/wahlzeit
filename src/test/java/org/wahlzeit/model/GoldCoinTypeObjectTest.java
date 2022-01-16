package org.wahlzeit.model;

import org.junit.Test;

public class GoldCoinTypeObjectTest {

    @Test
    public void testGoldCoinTypeAndInstance() {
        // Create types
        GoldCoinType goldCoinType = new GoldCoinType("Gold Coin");
        GoldCoinType kruegerrandType = new GoldCoinType("Krügerrand 2022");
        GoldCoinType mapleLeafType = new GoldCoinType("Maple Leaf 2022");
        // Create instances
        GoldCoin kruegerrandInstance = kruegerrandType.createInstance();
        GoldCoin mapleLeafInstance = mapleLeafType.createInstance();

        goldCoinType.addSubType(kruegerrandType);

        assert !goldCoinType.isSubType();
        assert !goldCoinType.hasInstance(mapleLeafInstance);
        assert !kruegerrandType.hasInstance(mapleLeafInstance);

        assert goldCoinType.hasInstance(kruegerrandInstance);
        assert kruegerrandInstance.getId() == 0;
        assert mapleLeafInstance.getId() == 1;
    }

    @Test
    public void testGoldCoinManager() {
        GoldCoinManager goldCoinManager = GoldCoinManager.getInstance();

        GoldCoin goldCoin0 = goldCoinManager.createGoldCoin("Australien Känguru 2022");
        GoldCoin goldCoin1 = goldCoinManager.createGoldCoin("Australien Känguru 2022");

        assert goldCoin0.getId() != goldCoin1.getId();
        assert goldCoin0.getType() == goldCoin1.getType();
    }
}
