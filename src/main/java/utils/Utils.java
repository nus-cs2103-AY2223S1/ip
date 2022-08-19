package utils;

public class Utils {
    public static boolean isNotParsable(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException numberFormatException) {
            return true;
        }
    }
}
