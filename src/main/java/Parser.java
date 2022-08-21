import java.time.DateTimeException;

/**
 * This class parses the user input into commands.
 */
public class Parser {
    /**
     * Parses the user input, and returns the appropriate command.
     *
     * @param userInput The user input.
     * @param taskList The task list to run the command in.
     * @return The command corresponding to the input.
     */
    public static Command parseInput(String userInput, TaskList taskList) {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];
        String argument = words[words.length - 1];
        String[] arguments;

        try {
            switch (commandWord) {
                case ExitCommand.COMMAND_WORD:
                    return new ExitCommand();
                case ListCommand.COMMAND_WORD:
                    return new ListCommand(taskList);
                case MarkCommand.COMMAND_WORD:
                    return new MarkCommand(taskList, Integer.parseInt(argument) - 1);
                case UnmarkCommand.COMMAND_WORD:
                    return new UnmarkCommand(taskList, Integer.parseInt(argument) - 1);
                case TodoCommand.COMMAND_WORD:
                    return new TodoCommand(taskList, new Todo(argument));
                case DeadlineCommand.COMMAND_WORD:
                    arguments = splitArgument(argument, DeadlineCommand.COMMAND_SEPARATOR);
                    return new DeadlineCommand(taskList, new Deadline(arguments[0], arguments[1]));
                case EventCommand.COMMAND_WORD:
                    arguments = splitArgument(argument, EventCommand.COMMAND_SEPARATOR);
                    return new EventCommand(taskList, new Event(arguments[0], arguments[1]));
                case DeleteCommand.COMMAND_WORD:
                    return new DeleteCommand(taskList, Integer.parseInt(argument) - 1);
                default:
                    throw new DwukeException("am sowwy, but me dun know wat that means :-(");
            }
        } catch (DwukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new InvalidCommand("oops!!! dats not a pwopew numbew");
        } catch (DateTimeException e) {
            return new InvalidCommand("oops!!! dats not a pwopew date");
        }
    }

    /**
     * Splits the given argument into description and date, separated by the command separator.
     *
     * @param argument The String to split.
     * @param commandSeparator The String to split the argument by.
     * @return An array containing the description and date.
     * @throws DwukeException If the command separator is not found, or if the description or date is empty.
     */
    public static String[] splitArgument(String argument, String commandSeparator) throws DwukeException {
        int index = argument.indexOf(commandSeparator);
        if (index == -1) throw new DwukeException(commandSeparator + " not fwound");

        try {
            String description = argument.substring(0, index - 1);
            String date = argument.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DwukeException("da descwiption and date cannot be empty");
        }
    }
}
