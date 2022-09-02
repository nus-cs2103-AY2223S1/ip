package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A parser class to parse all input strings
 */
public class Parser {
    private final static Pattern INT_PATTERN = Pattern.compile("\\d+");

    private static Pattern FLAG_PATTERN = Pattern.compile("/\\w+");

    /**
     * Test if a string is an integer
     * @param s string to be tested
     * @return true if string is integer
     */
    public static boolean IsInteger(String s) {
        return INT_PATTERN.matcher(s).matches();
    }


    /**
     * A class representing a parsed input string (into arguments)
     */
    public static class ParsedInputArguments {
        public String keyword = "";
        public String args = "";
        public HashMap<String, String> flags = new HashMap<>();

        public ParsedInputArguments() {

        }

        public ParsedInputArguments(String keyword, String args) {
            this.keyword = keyword;
            this.args = args;
        }

        public ParsedInputArguments(String keyword, String args, HashMap<String, String> flags) {
            this.keyword = keyword;
            this.args = args;
            this.flags = flags;
        }

        /**
         * Add flag (key-value pair) to arguments object.
         * @param key key of flag to add
         * @param value value of flag to add
         */
        public void addFlag(String key, String value) {
            flags.put(key, value);
        }

        /**
         * Get a flag value via a key.
         * @param key key of flag to get
         * @return the flag value retrieved.
         */
        public String getFlag(String key) {
            return (String) flags.get(key);
        }

    }

    /**
     * Returns a hashmap of arguments and commands from the given input.
     * @param command duke.command.Command string given
     * @return HashMap of arguments,
     * The given command keyword is under the key "keyword"
     * Unnamed arguments are under the key "args"
     * Keys under any /flags are under the key "/flag"
     */
    public static ParsedInputArguments getInputArguments(String command) {
        ParsedInputArguments parsedArguments = new ParsedInputArguments();

        parsedArguments.keyword = command.split(" ", 2)[0].toLowerCase(Locale.ROOT);
        if (command.split(" ").length < 2) {
            return parsedArguments;
        }

        command = command.split(" ", 2)[1];
        parsedArguments.args = command.split("/", 2)[0].strip();
        if (command.split("/").length < 2) {
            return parsedArguments;
        }

        command = "/" + command.split("/", 2)[1];
        Matcher flagMatches = FLAG_PATTERN.matcher(command);

        while (flagMatches.find()) {
            String match = flagMatches.group();
            command = command.split(match, 2)[1];
            parsedArguments.addFlag(match, command.split("/", 2)[0].strip());
        }
        return parsedArguments;
    }

    /**
     * Parse string to a LocalDate object.
     * @param date String representation of a date.
     * @return a LocalDate object from the string.
     */
    public static LocalDate parseStringtoDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Parse a LocalDate object to a string, for serialisation or output.
     * @param date a LocalDate object to be converted to a string
     * @return a String object from the LocalDate object
     */
    public static String parseDatetoString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }
}
