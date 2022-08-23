import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {
    private Parser() {}

    // Returns trailing integers from a String
    // Throws error if no trailing integers
    public static int getTrailingInt(String string) {
        Pattern pattern = Pattern.compile("[^0-9]+([0-9]+)$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException("String is invalid");
        }
    }

    // Returns substring after first instance of token string
    // Throws error if token is not found in String
    public static String substringAfterToken(String string, String token) {
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            try {
                return string.substring(matcher.end() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                return "";
            }
        } else {
            throw new IllegalArgumentException("Unable to find token in string");
        }
    }

    // Returns substring before and after first instance of token string
    // Before stored in [0]. After stored in [1].
    // Throws error if token is not found in String
    public static String[] substringBeforeAfterToken(String string, String token) {
        String[] stringArray = new String[2];
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            try {
                stringArray[0] = string.substring(0, matcher.start() - 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringArray[0] = "";
            }
            try {
                stringArray[1] = string.substring(matcher.end() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringArray[1] = "";
            }
            return stringArray;
        } else {
            throw new IllegalArgumentException("Unable to find token in string");
        }
    }
}
