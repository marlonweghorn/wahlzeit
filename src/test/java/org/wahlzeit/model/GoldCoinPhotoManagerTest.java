package org.wahlzeit.model;

import org.junit.Test;

public class GoldCoinPhotoManagerTest {

    @Test
    public void testInstance() {

        GoldCoinPhotoManager goldCoinPhotoManager = new GoldCoinPhotoManager();

        assert goldCoinPhotoManager instanceof PhotoManager;
    }
}
