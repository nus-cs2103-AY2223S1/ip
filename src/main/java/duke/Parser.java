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
     * @param in user input.
     */
    public static void parseInput(String in) throws IllegalArgumentException, IndexOutOfBoundsException {
        String[] inputArr = in.split(" ", 2);
        String inputCommand = inputArr[0];
        Command command = null;
        command = Command.getCommand(inputCommand);
        userCommand = command;
        if (command != Command.LIST && command != Command.BYE) {
            userInstructions = inputArr[1];
        }
    }

    /**
     * Getter for the user's command.
     *
     * @return command enum representing the user's command.
     */
    public static Command getUserCommand() {
        return userCommand;
    }

    /**
     * Getter for the user's instructions.
     *
     * @return string representation of the user's instructions.
     */
    public static String getUserInstructions() {
        return userInstructions;
    }
}
