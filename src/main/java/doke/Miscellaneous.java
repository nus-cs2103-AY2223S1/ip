package doke;

/**
 * A Miscellaneous class to handle miscellaneous methdods
 */
public class Miscellaneous {

    /**
     * Returns a boolean whether the str can be parsed to a String or not
     * A static method.
     *
     * @param str to be parsed
     * @return true if the string can be parsed to int and false otherwise.
     */
    public static boolean isInt(String str) {
        try {
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }

    /**
     * Returns the int after parsing the string.
     * A static method.
     *
     * @param str to be parse
     * @return the parsed int
     */
    public static int toInt(String str) {
        try {
            int x = Integer.parseInt(str);
            return x; //String is an Integer
        } catch (NumberFormatException e) {
            return -1; //String is not an Integer
        }
    }
}
