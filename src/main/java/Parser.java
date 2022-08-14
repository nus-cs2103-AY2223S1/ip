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
    public static Command parseInput(String userInput, TaskList taskList) {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];
        String argument = words.length > 1 ? words[1] : "";

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
                    return new DeadlineCommand(taskList, new Deadline(argument));
                case EventCommand.COMMAND_WORD:
                    return new EventCommand(taskList, new Event(argument));
                case DeleteCommand.COMMAND_WORD:
                    return new DeleteCommand(taskList, Integer.parseInt(argument) - 1);
                default:
                    throw new DwukeException("oops!!! am sowwy, but me dun know wat that means :-(");
            }
        } catch (DwukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
