package duke;

/**
 * This class handles the logic of extrapolating the keywords from the whole command.
 */
public class Parser {
    /* User Input Fields.*/
    private static Command userCommand;
    private static String userInstructions;

    /**
     * Parses input by user into a Command enum for interpretation by Elp.
     *
     * @param in User input.
     */
    public static void parseInput(String in) throws IllegalArgumentException, IndexOutOfBoundsException {
        String[] inputArr = in.split(" ", 2);
        String inputCommand = inputArr[0];
        Command command = null;
        command = Command.getCommand(inputCommand);
        userCommand = command;
        assert userCommand != null : "no user command found";
        if (command != Command.LIST && command != Command.BYE) {
            userInstructions = inputArr[1];
        }
    }

    /**
     * Gets user's command.
     *
     * @return Command enum representing the user's command.
     */
    public static Command getUserCommand() {
        return userCommand;
    }

    /**
     * Gets the user's instructions.
     *
     * @return String representation of the user's instructions.
     */
    public static String getUserInstructions() {
        return userInstructions;
    }
}
