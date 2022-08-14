/**
 * This class parses the user input into commands.
 */
public class Parser {
    Parser() {
    }

    /**
     * Parses the user input, and returns the appropriate command.
     *
     * @param userInput The user input.
     * @return The command corresponding to the input.
     */
    public Command parseInput(String userInput) {
        String[] words = userInput.split(" ");
        String commandWord = words[0];
        String argument = words.length == 1 ? "" : words[1];

        switch (commandWord) {
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(Integer.parseInt(argument) - 1);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(Integer.parseInt(argument) - 1);
            default:
                return new AddCommand(new Task(commandWord));
        }
    }
}
