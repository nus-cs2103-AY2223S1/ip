package utils;

/**
 * The {@code Utils} class contains useful utilities used throughout the application.
 */
public class Utils {
    /**
     * Returns a boolean indicating if an input can be parsed into an integer
     *
     * @param input String that we want to test
     * @return <code>true</code> if the string cannot be parsed into an integer
     * <code>false</code> otherwise.
     */
    public static boolean isNotParsable(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException numberFormatException) {
            return true;
        }
    }
}
