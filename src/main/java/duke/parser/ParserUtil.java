package duke.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.domain.task.TaskDescription;
import duke.domain.task.TaskIndex;
import duke.exceptions.ParseException;

/**
 * ParserUtil class
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");

    /**
     * Returns the parsed task description
     *
     * @param description
     *            The unparsed description
     * @return Parsed task description
     * @throws ParseException
     *             When the task description is invalid
     */
    public static TaskDescription parseTaskDescription(String description) throws ParseException {
        assert Objects.nonNull(description);
        String trimmedDescription = description.trim();
        if (!TaskDescription.isValid(trimmedDescription)) {
            throw new ParseException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        return new TaskDescription(description);
    }

    /**
     * Returns the parsed task index
     *
     * @param oneBasedIndex
     *            THe unparsed one based index
     * @return The parsed task index
     * @throws ParseException
     *             If the index is wrong
     */
    public static TaskIndex parseTaskIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return TaskIndex.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Returns a predicate if the given string is a non zero unsigned integer
     *
     * @param s
     *            The test string
     * @return If the given string is a non zero unsigned integer
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns a predicate if the given strNum is numeric
     *
     * @param strNum
     *            The test string
     * @return If the given string is a number
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     * Returns the parsed date time string
     *
     * @param inputDateTime
     *            The given inputDateTime string
     * @return The parsed date time string to LocalDateTime
     */
    public static LocalDateTime parseDateTimeString(String inputDateTime)
            throws DateTimeParseException {
        return LocalDateTime.parse(inputDateTime, formatter);
    }
}
