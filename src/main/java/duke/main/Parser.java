package duke.main;

import duke.exception.DukeException;

/**
 * Class handling input parsing and validation.
 */
public class Parser {
    /* Command keyword of the current input string being parsed */
    private static Keyword command;
    /* Argument string of the current input string being parsed */
    private static String argument;

    /**
     * Parses given input string to update command and argument class fields.
     * Outputs error message if command keyword is invalid or argument field is empty.
     *
     * @param input String to be parsed.
     */
    public static void parse(String input) throws DukeException {
        // Delimit over " " to extract first keyword
        String[] inputTokens = input.split(" ", 2);

        try {
            command = Keyword.getKeyword(inputTokens[0]);
            if (command != Keyword.LIST && command != Keyword.BYE) {
                String argumentString = inputTokens[1];
                validateArgument(argumentString);
                argument = argumentString;
            }
        } catch (DukeException de) {
            throw de;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Hey! Did you forget to add a task name / number?");
        }
    }

    /**
     * Validates arguments for the respective command keywords parsed from input.
     * Throws DukeException if argument is missing or of invalid format.
     *
     * @param argument String to be validated.
     * @throws DukeException If argument is missing or of invalid format.
     */
    private static void validateArgument(String argument) throws DukeException {
        assert command != null : "Missing Command Type";

        try {
            switch (command) {
            case DEADLINE: {
                validateDeadlineArgument(argument);
                break;
            }
            case EVENT: {
                validateEventArgument(argument);
                break;
            }
            case MARK: // Fallthrough
            case UNMARK: // Fallthrough
            case DELETE: {
                validateTaskIndex(argument);
                break;
            }
            default: {
                throw new DukeException("Unexpected Error in validateArgument");
            }
            }
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Validates argument for Deadline Task.
     *
     * @param argument String to be validated.
     * @throws DukeException If argument is invalid.
     */
    private static void validateDeadlineArgument(String argument) throws DukeException {
        try {
            String[] taskTokens = argument.split(" /by ");
            String deadline = taskTokens[1]; // Throws AIOOBE
            DateTimeFormatUtils.parseDate(deadline);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Looks like you're missing a timing / task name for this task...");
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Validates argument for Event Task.
     *
     * @param argument String to be validated.
     * @throws DukeException If argument is invalid.
     */
    private static void validateEventArgument(String argument) throws DukeException {
        try {
            String[] taskTokens = argument.split(" /at ");
            String duration = taskTokens[1]; // Throws AIOOBE
            DateTimeFormatUtils.parseDuration(duration);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Looks like you're missing a timing / task name for this task...");
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Validates task index passed as a string.
     *
     * @param argument String containing task index.
     * @throws DukeException If task index is invalid.
     */
    private static void validateTaskIndex(String argument) throws DukeException {
        try {
            Integer.parseInt(argument);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Sorry, that Task Number doesn't look right...");
        }

    }

    public static Keyword getCommand() {
        return command;
    }

    public static String getArgument() {
        return argument;
    }

}
