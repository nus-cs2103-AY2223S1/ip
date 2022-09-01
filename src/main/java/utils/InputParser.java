package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static Pattern intPattern = Pattern.compile("\\d+");

    public static boolean IsInteger(String s) {
        return intPattern.matcher(s).matches();
    }

    private static Pattern flagPattern = Pattern.compile("/\\w+");
    /**
     * Returns a hashmap of arguments and commands from the given input.
     * @param command Command string given
     * @return HashMap of arguments,
     * The given command keyword is under the key "keyword"
     * Unnamed arguments are under the key "args"
     * Keys under any /flags are under the key "/flag"
     */
    public static HashMap<String, String> getInputArguments(String command) {
        HashMap<String, String> result = new HashMap<>();

        result.put("keyword", command.split(" ", 2)[0]);
        if (command.split(" ").length < 2) {
            return result;
        }

        command = command.split(" ", 2)[1];
        result.put("args", command.split("/", 2)[0].strip());
        if (command.split("/").length < 2) {
            return result;
        }

        command = "/" + command.split("/", 2)[1];
        Matcher flagMatches = flagPattern.matcher(command);

        while (flagMatches.find()) {
            String match = flagMatches.group();
            command = command.split(match, 2)[1];
            result.put(match, command.split("/", 2)[0].strip());
        }
        return result;
    }

    public static LocalDate parseStringtoDate(String date) {
        return LocalDate.parse(date);
    }

    public static String parseDatetoString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
