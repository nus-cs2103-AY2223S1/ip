package duke.enums;

import duke.command.SortCommand;
import duke.exception.InvalidCommandFormatException;

/**
 * Represents a metric to sort the TaskList by.
 */
public enum SortMetric {
    DEADLINE,
    DATE_ADDED,
    DESCRIPTION,
    TYPE;

    /** Command argument representing the deadline metric. */
    private static final String DEADLINE_STRING = "deadline";
    /** Command argument representing the date added metric. */
    private static final String DATE_ADDED_STRING = "new";
    /** Command argument representing the task description metric. */
    private static final String DESCRIPTION_STRING = "des";
    /** Command argument representing the task type metric. */
    private static final String TYPE_STRING = "type";

    /**
     * Returns a SortMetric by parsing the command argument.
     *
     * @param str Command argument representing the SortMetric.
     * @return SortMetric corresponding to the command argument.
     * @throws InvalidCommandFormatException If the command argument does not match any of the metrics.
     */
    public static SortMetric parse(String str) throws InvalidCommandFormatException {
        switch (str) {
        case DEADLINE_STRING:
            return DEADLINE;
        case DATE_ADDED_STRING:
            return DATE_ADDED;
        case DESCRIPTION_STRING:
            return DESCRIPTION;
        case TYPE_STRING:
            return TYPE;
        default:
            throw new InvalidCommandFormatException(SortCommand.getFormat());
        }
    }

    /**
     * Checks if a command argument corresponds to any of the sort metrics.
     *
     * @param str Command argument.
     * @return True, if the command argument corresponds to one of the sort metrics.
     */
    public static boolean isValidMetric(String str) {
        switch (str) {
        case DEADLINE_STRING:
        case DATE_ADDED_STRING:
        case DESCRIPTION_STRING:
        case TYPE_STRING:
            return true;
        default:
            return false;
        }
    }
}
