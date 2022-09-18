package duke.enums;

import duke.command.SortCommand;
import duke.exception.InvalidCommandFormatException;

/**
 * Represents the order of the tasks to sort the TaskList by.
 */
public enum SortOrder {
    ASCENDING,
    DESCENDING;

    /** Command argument representing the ascending order. */
    private static final String ASCENDING_STRING = "A";
    /** Command argument representing the descending order. */
    private static final String DESCENDING_STRING = "D";

    /**
     * Returns a SortOrder by parsing the command argument.
     *
     * @param str Command argument.
     * @return SortOrder corresponding to the command argument.
     * @throws InvalidCommandFormatException If the command argument does not match any of the sort orders.
     */
    public static SortOrder parse(String str) throws InvalidCommandFormatException {
        if (str.equals(ASCENDING_STRING)) {
            return ASCENDING;
        } else if (str.equals(DESCENDING_STRING)) {
            return DESCENDING;
        } else {
            throw new InvalidCommandFormatException(SortCommand.getFormat());
        }
    }

    /**
     * Checks if a command argument corresponds to any of the sort orders.
     *
     * @param str Command argument.
     * @return True, if the command argument corresponds to one of the sort orders.
     */
    public static boolean isValidOrder(String str) {
        switch (str) {
        case ASCENDING_STRING:
        case DESCENDING_STRING:
            return true;
        default:
            return false;
        }
    }
}
