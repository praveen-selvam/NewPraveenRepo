package com.autotrek.instantauto.test;

import java.util.Random;

/**
 * This class contains a collection of string helper functions for unit testing
 * purposes.
 *
 * @author Joe C. McPherson
 */
public class StringHelperUtil {

    private static final String RANDOM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    /**
     * Generates a random alphanumeric string based on the supplied size.
     *
     * This is especially useful when generated objects that have a unique
     * constraint on a string field.
     *
     * @param size
     * @return
     */
    public static String generateRandomString(int size) {
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while (builder.length() < size) {
            int idx = (int) (rnd.nextFloat() * RANDOM_CHARS.length());
            builder.append(RANDOM_CHARS.charAt(idx));
        }
        return builder.toString();
    }
}
