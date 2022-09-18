import static java.lang.Boolean.parseBoolean;

/**
 * Parser class to process user input for KKBot
 *
 * @author AkkFiros
 */

public class Parser {
    private static final String INVALID_INPUT = "Invalid user input, please try again.";
    private enum MissingDetails {
        TASK_NUMBER, DESCRIPTION, DESCRIPTION_AND_DATE, DATE, KEYWORD
    }

    /**
     * Parse method to determine the command to pass to KKBot.
     * @param input the user input
     * @return the command to pass to KKBot
     * @throws KKBotException when the user input is erroneous
     */
    public static Command initialParse(String input) throws KKBotException {
        String[] splitInput = input.trim().split(" ", 2);
        String inputCommand = splitInput[0];

        switch (inputCommand) {
            case ByeCommand.KEYWORD:
                return new ByeCommand();
            case ListCommand.KEYWORD:
                return new ListCommand();
            case MarkCommand.KEYWORD:
            case UnmarkCommand.KEYWORD:
            case DeleteCommand.KEYWORD:
                return parseForIndex(splitInput, inputCommand);
            case ToDoCommand.KEYWORD:
                return parseForToDo(splitInput);
            case DeadlineCommand.KEYWORD:
            case EventCommand.KEYWORD:
                return parseForDate(splitInput, inputCommand);
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Method to parse for a user-specified index number.
     * @param splitInput array of input components after initial parse
     * @param inputCommand the commmand input by user
     * @return the command after retrieving the user input task index number
     * @throws InvalidCommandException when user input a wrong command
     * @throws InvalidArgumentException when user input is invalid
     */
    private static Command parseForIndex(String[] splitInput, String inputCommand)
            throws InvalidCommandException, InvalidArgumentException {
        checkInputLength(splitInput, MissingDetails.TASK_NUMBER);
        assert splitInput.length == 2 : INVALID_INPUT;
        int index = getTaskNumber(splitInput[1]);
        switch (inputCommand) {
            case "mark":
                return new MarkCommand(index);
            case"unmark":
                return new UnmarkCommand(index);
            case "delete":
                return new DeleteCommand(index);
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Method to parse for a user-input description for a todo task
     * @param splitInput array of input components after initial parse
     * @return the command to create a ToDo task
     * @throws InvalidArgumentException when user input is invalid
     */
    private static Command parseForToDo(String[] splitInput)
            throws InvalidArgumentException {
        checkInputLength(splitInput, MissingDetails.DESCRIPTION);
        assert splitInput.length == 2 : INVALID_INPUT;
        String description = splitInput[1].trim();
        return new ToDoCommand(description);
    }

    private static Command parseForDate(String[] splitInput, String type)
        throws In
}
