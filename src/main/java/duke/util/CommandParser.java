package duke.util;

import duke.legacy.Actionable;
import duke.legacy.Command;
import duke.legacy.CommandType;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private static final String DELIMITER = "/";
    private static final String BY_DATE_DELIMITER = "/by";
    private static final String AT_DATE_DELIMITER = "/at";

    public static int getIndexOfFirstOccurrence(String input, String pattern) {
        int indexOfFirstOccurrence = input.indexOf(pattern);
        if (indexOfFirstOccurrence == -1) {
            indexOfFirstOccurrence = input.length();
        }
        return indexOfFirstOccurrence;
    }

    public static int getIndexOfFirstWhiteSpace(String input) {
        return getIndexOfFirstOccurrence(input, " ");
    }

    public static int getIndexOfFirstDelimiter(String input) {
        return getIndexOfFirstOccurrence(input, DELIMITER);
    }

    public static String getFirstWord(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        return input.substring(0, indexOfFirstWhiteSpace);
    }

    public static String getByDate(String input) {
        int indexOfDelimiter = input.indexOf(BY_DATE_DELIMITER);
        if (indexOfDelimiter == -1) {
            return null;
        }
        return input.substring(indexOfDelimiter + BY_DATE_DELIMITER.length(), input.length());
    }

    public static String getAtDate(String input) {
        int indexOfDelimiter = input.indexOf(AT_DATE_DELIMITER);
        if (indexOfDelimiter == -1) {
            return null;
        }
        return input.substring(indexOfDelimiter + AT_DATE_DELIMITER.length(), input.length());
    }

    public static String getTaskTitle(String input) {
        int indexOfStart = getIndexOfFirstWhiteSpace(input);
        int indexOfEnd = indexOfStart
                + getIndexOfFirstDelimiter(input.substring(indexOfStart + 1, input.length()))
                + 1;
        return input.substring(indexOfStart + 1, indexOfEnd);
    }

    public static int getTaskIndexFromCommand(String input) {
        int indexOfFirstWhiteSpace = CommandParser.getIndexOfFirstWhiteSpace(input);
        String tailSubString = input.substring(indexOfFirstWhiteSpace, input.length());
        tailSubString = tailSubString.replace(" ", "");
        if (tailSubString.isEmpty()) {
            return -1;
        }
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(tailSubString) - 1; // Minus 1 because user input indices start from 1
        } catch (NumberFormatException ex) {
            // No need to do anything, because the output will be -1
            // which will be handled in the higher layer
        }
        return taskIndex;
    }
}
