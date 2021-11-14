package org.wahlzeit.model;

import org.junit.Test;


public class GoldCoinPhotoTest {

    @Test
    public void testInstance() {

        GoldCoinPhoto goldCoinPhoto = new GoldCoinPhoto();

        assert goldCoinPhoto instanceof Photo;
    }
}
