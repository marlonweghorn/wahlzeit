package org.wahlzeit.utils;

public class Assertions {

    /**
     *
     * @methodtype assertion
     */
    public static <T> void assertIsNonNullArgument(T argument) {
        assert argument != null;
    }
}
