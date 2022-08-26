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
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("\tHey! Did you forget to add a task name / number?");
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
        try {
            switch (command) {
            case DEADLINE: {
                String[] taskTokens = argument.split(" /by ");
                String taskName = taskTokens[0];
                String deadline = taskTokens[1]; // Throws AIOOBE
                DateTimeFormatUtils.parseDate(deadline);
                break;
            }
            case EVENT: {
                String[] taskTokens = argument.split(" /at ");
                String taskName = taskTokens[0];
                String duration = taskTokens[1]; // Throws AIOOBE
                DateTimeFormatUtils.parseDuration(duration);
                break;
            }
            case MARK:
            case UNMARK:
            case DELETE: {
                Integer.parseInt(argument);
                break;
            }
            default: {
                break;
            }
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("\tLooks like you're missing a timing / task name for this task...");
        } catch (NumberFormatException nfe) {
            throw new DukeException("\tSorry, that Task Number doesn't look right...");
        } catch (DukeException de) {
            throw de;
        }
    }

    public static Keyword getCommand() {
        return command;
    }

    public static String getArgument() {
        return argument;
    }
}
