package output;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for static methods related to console output
 */
public class OutputLogger {
    private static final String NAME = "JARVIS";

    /**
     * Indent the lines of a String
     * @param text Text to indent
     * @return String with lines of the text indented with one tab
     */
    public static String indent(String text) {
        return text
            .lines()
            .map(x -> "\t" + x)
            .collect(Collectors.joining("\n"));
    }

    /**
     * Output a message to the user through command line
     * @param msg Message to output
     */
    public static void output(String msg) {
        String line = "____________________________________________________________";
        String out = String.format("%s%n%s:\n%s%n%s%n", line, NAME, msg, line);
        System.out.println(indent(out));
    }

    /**
     * Format a list of lines with line numbers
     * @param linesList List of String lines to output
     * @return Message with line numbers for each line
     */
    public static String numberedOutput(List<String> linesList) {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < linesList.size(); i++) {
            int id = i + 1;
            String taskItem = String.format("%d. %s%n", i + 1, linesList.get(i));
            out.append(taskItem);
        }

        return out.toString();
    }

    /**
     * Return an appropriate error string given a list of issues
     * @param errMsgs A list of error messages for the user of length >= 1
     * @return Appropriate error string
     */
    public static String errorOutput(List<String> errMsgs) {
        if (errMsgs.size() == 1) {
            return "The following issue occurred with your command:\n" + OutputLogger.indent(errMsgs.get(0));
        }
        return "The following issues occurred with your command:\n" + numberedOutput(errMsgs);
    }

    /**
     * Print chatbot introduction for the user
     */
    public static void printIntroduction() {
        String logo = "\n"
                +
                "     _   _    ___ __   __ ___  ___ \n"
                +
                "  _ | | /_\\  | _ \\\\ \\ / /|_ _|/ __|\n"
                +
                " | || |/ _ \\ |   / \\ V /  | | \\__ \\\n"
                +
                "  \\__//_/ \\_\\|_|_\\  \\_/  |___||___/\n";

        System.out.print(logo);

        output(String.format("Hello,I'm %s!\nWhat can I do for you?", NAME));
    }

}
