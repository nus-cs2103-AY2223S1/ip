package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Pattern intPattern = Pattern.compile("\\d+");

    public static boolean IsInteger(String s) {
        return intPattern.matcher(s).matches();
    }

    private static Pattern flagPattern = Pattern.compile("/\\w+");

    public static class ParsedInputArguments {
        public String keyword;
        public String args;
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

        public void addFlag(String key, String value) {
            flags.put(key, value);
        }

        public String getFlag(String key) {
            return (String) flags.get(key);
        }

    }
    /**
     * Returns a hashmap of arguments and commands from the given input.
     * @param command duke.Command string given
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
        Matcher flagMatches = flagPattern.matcher(command);

        while (flagMatches.find()) {
            String match = flagMatches.group();
            command = command.split(match, 2)[1];
            parsedArguments.addFlag(match, command.split("/", 2)[0].strip());
        }
        return parsedArguments;
    }

    public static LocalDate parseStringtoDate(String date) {
        return LocalDate.parse(date);
    }

    public static String parseDatetoString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }


    public static void main (String[] args) {
//        System.out.println(getInputArguments("hello"));
//        System.out.println(getInputArguments("hello args"));
//        System.out.println(getInputArguments("hello args args"));
//        System.out.println(getInputArguments("hello args /at 2pm"));
//        System.out.println(getInputArguments("hello args /at 2pm /by 1 2 3 4 5 /hello greetings asdf sd"));
//        System.out.println(getInputArguments("deadline do things /by tmr"));
        LocalDate date = parseStringtoDate("2019-12-01");
        System.out.println(parseDatetoString(date));
    }
}
