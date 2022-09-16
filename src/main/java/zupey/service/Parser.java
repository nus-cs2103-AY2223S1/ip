package zupey.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Parser class handles user-inputs */
public class Parser {
    private static final Pattern COMMAND_REGEX =
            Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: (.*))?$");

    /**
     * Parsers the user input into tokens.
     *
     * @param s
     * @return IHandler
     */
    public static String[] parse(String s) {
        try {
            Matcher m = COMMAND_REGEX.matcher(s);
            m.find();
            String command = m.group(1);
            String value = m.group(2);
            String flag = m.group(3);
            String options = m.group(4);

            return new String[]{command, value, flag, options};

        } catch (IllegalStateException ex) {
            // catch when no match found, returns empty strings
            return new String[]{"", "", "", ""};
        }
    }
}
