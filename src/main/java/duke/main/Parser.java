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

            // List and Bye commands are not passed with argument
            if (command == Keyword.LIST || command == Keyword.BYE) {
                argument = null;
            } else if (command == Keyword.TAG) {
                String argumentString = inputTokens[1]; // Throws AOOIBE
                String[] tokens = argumentString.split(" ", 2);
                String taskNumber = tokens[0];
                String tagName = tokens[1];

                // Only need to check task number, empty tag name is caught by AOOIBE
                validateArgument(taskNumber, tagName);
                argument = taskNumber + "|" + tagName;
            } else {
                String argumentString = inputTokens[1]; // Throws AOOIBE
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
     * @param arguments Strings to be validated.
     * @throws DukeException If argument is missing or of invalid format.
     */
    private static void validateArgument(String... arguments) throws DukeException {
        assert command != null : "Missing Command Type";

        try {
            switch (command) {
            case DEADLINE: {
                validateDeadlineArgument(arguments[0]);
                break;
            }
            case EVENT: {
                validateEventArgument(arguments[0]);
                break;
            }
            case MARK: // Fallthrough
            case UNMARK: // Fallthrough
            case DELETE: {
                validateTaskIndex(arguments[0]);
                break;
            }
            case TAG: {
                validateTaskIndex(arguments[0]);
                checkEmptyArgument(arguments[1]);
                break;
            }
            case TODO: {
                checkEmptyArgument(arguments[0]);
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
     * Validates argument for Deadline Task
     *
     * @param argument String to be validated.
     * @throws DukeException If argument is invalid.
     */
    private static void validateDeadlineArgument(String argument) throws DukeException {
        try {
            String[] taskTokens = argument.split(" /by ");

            // Check empty argument
            String taskName = taskTokens[0];
            checkEmptyArgument(taskName);

            // Check deadline
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

            // Check empty argument
            String taskName = taskTokens[0];
            checkEmptyArgument(taskName);

            // check valid duration
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

    /**
     * Checks if string passed is an empty string.
     * Throw DukeException if empty.
     *
     * @param argument String to be checked.
     * @throws DukeException If string is empty.
     */
    private static void checkEmptyArgument(String argument) throws DukeException {
        if (argument.equals("")) {
            throw new DukeException("You forgot to name this one!");
        }
    }

    public static Keyword getCommand() {
        return command;
    }

    public static String getArgument() {
        return argument;
    }

}
