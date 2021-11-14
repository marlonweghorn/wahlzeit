package org.wahlzeit.model;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.*;

public class GoldCoinPhotoFactoryTest {

    @Test
    public void testInstance() {
        GoldCoinPhotoFactory goldCoinPhotoFactory = GoldCoinPhotoFactory.getInstance();

        assert goldCoinPhotoFactory != null;
        assert goldCoinPhotoFactory instanceof PhotoFactory;
    }

    @Test
    public void testSingleton() {
        // Test access modifier of constructor and instance is private
        Class<GoldCoinPhotoFactory> goldCoinPhotoFactoryClass = GoldCoinPhotoFactory.class;
        Constructor<?>[] constructors = goldCoinPhotoFactoryClass.getDeclaredConstructors();
        assert constructors.length == 1;
        assert (Modifier.PRIVATE & constructors[0].getModifiers()) == Modifier.PRIVATE;

        try {
            Field instance = goldCoinPhotoFactoryClass.getDeclaredField("instance");
            assert (Modifier.PRIVATE & instance.getModifiers()) == Modifier.PRIVATE;
        } catch (NoSuchFieldException e) {
           Assert.fail(e.getMessage());
        }
    }
}
