/**
 * This class handles the logic of extrapolating the keywords from the whole command.
 */
public class Parser {
    private static Command userCommand;
    private static String userInstructions;

    /**
     * Parses input by user into a Command enum for interpretation by Elp.
     *
     * @param in user input.
     */
    public static void parseInput(String in) {
        String[] inputArr = in.split(" ", 2);
        String inputCommand = inputArr[0];
        Command command = null;

        try {
            command = Command.getCommand(inputCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        userCommand = command;
        if (command != Command.LIST && command != Command.BYE) {
            try {
                userInstructions = inputArr[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Did you forget to input an index behind your command?\n");
            }
        }
    }

    public static Command getUserCommand() {
        return userCommand;
    }

    public static String getUserInstructions() {
        return userInstructions;
    }
}
