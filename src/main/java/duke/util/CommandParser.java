package duke.util;

import duke.Duke;
import duke.exception.DukeCommandFormatException;
import duke.exception.DukeIndexMissingException;
import duke.exception.DukeTaskDateTimeMissingException;
import duke.exception.DukeTaskTitleMissingException;
import duke.legacy.Actionable;
import duke.legacy.Command;
import duke.legacy.CommandType;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private static final String DELIMITER = Duke.DELIMITER;
    private static final String BY_DATE_DELIMITER = Duke.BY_DATE_DELIMITER;
    private static final String AT_DATE_DELIMITER = Duke.AT_DATE_DELIMITER;
    private static final String EVENT_WITHOUT_AT_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time with the delimiter " + AT_DATE_DELIMITER;
    private static final String DEADLINE_WITHOUT_BY_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time with the delimiter " + BY_DATE_DELIMITER;
    private static final String INDEX_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the index, starting from 1.";
    private static final String TASK_TITLE_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the title of the task.";
    private static final String TASK_DATE_TIME_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the title of the task.";

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

    public static String getByDate(String input) throws DukeCommandFormatException, DukeTaskDateTimeMissingException {
        int indexOfDelimiter = input.indexOf(BY_DATE_DELIMITER);
        if (indexOfDelimiter == -1) {
            throw new DukeCommandFormatException(DEADLINE_WITHOUT_BY_ERROR_MESSAGE);
        }
        String roughDate = input.substring(indexOfDelimiter + BY_DATE_DELIMITER.length(), input.length());
        String realDate = removeHeadingAndTailingWhiteSpaces(roughDate);
        if (realDate.isEmpty()) {
            throw new DukeTaskDateTimeMissingException(TASK_DATE_TIME_MISSING_ERROR_MESSAGE);
        }
        return realDate;
    }

    public static String getAtDate(String input) throws DukeCommandFormatException, DukeTaskDateTimeMissingException {
        int indexOfDelimiter = input.indexOf(AT_DATE_DELIMITER);
        if (indexOfDelimiter == -1) {
            throw new DukeCommandFormatException(EVENT_WITHOUT_AT_ERROR_MESSAGE);
        }
        String roughDate = input.substring(indexOfDelimiter + AT_DATE_DELIMITER.length(), input.length());
        String realDate = removeHeadingAndTailingWhiteSpaces(roughDate);
        if (realDate.isEmpty()) {
            throw new DukeTaskDateTimeMissingException(TASK_DATE_TIME_MISSING_ERROR_MESSAGE);
        }
        return realDate;
    }

    public static String getTaskTitle(String input) throws DukeTaskTitleMissingException {
        int indexOfStart = getIndexOfFirstWhiteSpace(input);
        if (indexOfStart == input.length()) {
            throw new DukeTaskTitleMissingException(TASK_TITLE_MISSING_ERROR_MESSAGE);
        }
        int indexOfEnd = indexOfStart
                + getIndexOfFirstDelimiter(input.substring(indexOfStart + 1, input.length()))
                + 1;
        String roughTaskTitle = input.substring(indexOfStart + 1, indexOfEnd);
        String realTaskTitle = removeHeadingAndTailingWhiteSpaces(roughTaskTitle);
        if (realTaskTitle.isEmpty()) {
            throw new DukeTaskTitleMissingException(TASK_TITLE_MISSING_ERROR_MESSAGE);
        }
        return removeHeadingAndTailingWhiteSpaces(roughTaskTitle);
    }

    public static int getTaskIndexFromCommand(String input) throws DukeIndexMissingException {
        int indexOfFirstWhiteSpace = CommandParser.getIndexOfFirstWhiteSpace(input);
        String tailSubString = input.substring(indexOfFirstWhiteSpace, input.length());
        tailSubString = tailSubString.replace(" ", "");
        if (tailSubString.isEmpty()) {
            throw new DukeIndexMissingException(INDEX_MISSING_ERROR_MESSAGE);
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tailSubString) - 1; // Minus 1 because user input indices start from 1
        } catch (NumberFormatException ex) {
            throw new DukeIndexMissingException(INDEX_MISSING_ERROR_MESSAGE);
        }
        return taskIndex;
    }

    public static String removeHeadingAndTailingWhiteSpaces(String input) {
        int start = 0;
        int end = input.length();
        while (start < input.length() && Character.isWhitespace(input.charAt(start))) {
            start++;
        }
        while (end > start && Character.isWhitespace(input.charAt(end - 1))) {
            end--;
        }
        return input.substring(start, end);
    }
}
