package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class GoldCoinTypeObjectTest {

    @Test
    public void testGoldCoinTypeAndInstance() {
        // Create types
        GoldCoinType goldCoinType = new GoldCoinType("Gold Coin");
        GoldCoinType kruegerrandType = new GoldCoinType("Krügerrand 2022");
        GoldCoinType mapleLeafType = new GoldCoinType("Maple Leaf 2022");
        // Create instances
        GoldCoin goldCoinInstance = goldCoinType.createInstance();
        GoldCoin kruegerrandInstance = kruegerrandType.createInstance();
        GoldCoin mapleLeafInstance = mapleLeafType.createInstance();

        goldCoinType.addSubType(kruegerrandType);

        assertTrue(kruegerrandType.isSubType(goldCoinInstance));
        assertFalse(kruegerrandType.isSubType(kruegerrandInstance));
        assertFalse(kruegerrandType.isSubType(mapleLeafInstance));

        assertFalse(goldCoinType.hasInstance(mapleLeafInstance));
        assertFalse(kruegerrandType.hasInstance(mapleLeafInstance));
        assertTrue(goldCoinType.hasInstance(kruegerrandInstance));

        assertEquals(goldCoinInstance.getId(), 0);
        assertEquals(kruegerrandInstance.getId(), 1);
        assertEquals(mapleLeafInstance.getId(), 2);
    }

    @Test
    public void testGoldCoinManager() {
        GoldCoinManager goldCoinManager = GoldCoinManager.getInstance();

        GoldCoin goldCoin0 = goldCoinManager.createGoldCoin("Australien Känguru 2022");
        GoldCoin goldCoin1 = goldCoinManager.createGoldCoin("Australien Känguru 2022");

        assertNotEquals(goldCoin0.getId(), goldCoin1.getId());
        assertEquals(goldCoin0.getType(), goldCoin1.getType());
    }
}
