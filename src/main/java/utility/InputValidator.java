package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public static boolean isValidCommand(String input) {
        String commandRegex = "(t|todo|l|list|e|event|d|deadline|m|mark|um|unmark|longdesc|istoday|f|find)\\s.*";
        Pattern pattern = Pattern.compile(commandRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
