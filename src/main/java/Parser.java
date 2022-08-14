/**
 * This class parses the user input into commands.
 */
public class Parser {
    /**
     * Parses the user input, and returns the appropriate command.
     *
     * @param userInput The user input.
     * @return The command corresponding to the input.
     */
    public static Command parseInput(String userInput) {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];
        String argument = words.length > 1 ? words[1] : "";

        switch (commandWord) {
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(Integer.parseInt(argument) - 1);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(Integer.parseInt(argument) - 1);
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(new Todo(argument));
            case DeadlineCommand.COMMAND_WORD:
                String[] arguments = splitArguments(words[1]);
                return new DeadlineCommand(new Deadline(arguments[0], arguments[1]));
            case EventCommand.COMMAND_WORD:
                arguments = splitArguments(words[1]);
                return new EventCommand(new Event(arguments[0], arguments[1]));
            default:
                return new AddCommand(new Task(commandWord));
        }
    }

    /**
     * Splits the given argument into content and date.
     *
     * @param argument The argument to split.
     * @return An array containing the content and date.
     */
    public static String[] splitArguments(String argument) {
        int index = argument.indexOf(" /");
        String argument1 = argument.substring(0, index);
        String argument2 = argument.substring(index + 5);
        return new String[]{argument1, argument2};
    }
}
