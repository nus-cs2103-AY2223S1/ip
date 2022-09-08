package output;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Class for static methods related to string output
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
    public static String output(String msg) {
        String out = String.format("%s:%n%s", NAME, msg);
        return indent(out);
    }

    /**
     * Format a list of lines with line numbers
     * @param linesList List of String lines to output
     * @return Message with line numbers for each line
     */
    public static String numberedOutput(List<String> linesList) {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < linesList.size(); i++) {
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
    public static String getIntroduction() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Hello, I'm %s!");
        joiner.add("Type 'help' to see a list of available commands.");
        joiner.add("Type help <command name> to see help for a specific command");
        return output(String.format(joiner.toString(), NAME));
    }

}
