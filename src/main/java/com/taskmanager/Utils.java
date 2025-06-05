package com.taskmanager;

import java.util.regex.Pattern;

/**
 * Utility class providing common helper methods.
 */
public class Utils {
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    /**
     * Validates whether the given string matches the YYYY-MM-DD date format.
     *
     * @param dateStr the date string to validate
     * @return true if the dateStr matches the format, false otherwise
     */
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null) {
            return false;
        }
        return DATE_PATTERN.matcher(dateStr).matches();
    }

    /**
     * Trims leading and trailing whitespace from the given string.
     *
     * @param str the string to trim
     * @return a trimmed string, or null if the input was null
     */
    public static String trim(String str) {
        return (str == null) ? null : str.trim();
    }
}