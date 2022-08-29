package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * This class parses the user input into Commands.
 */
public class Parser {
    /**
     * Parses the given text, and returns the appropriate Command.
     *
     * @param text The text to parse.
     * @param taskList  The TaskList to run the Command in.
     * @return The Command corresponding to the user input.
     */
    public static Command parseText(String text, TaskList taskList) {
        // @@author jorrdansoh-reused
        // Reused from https://github.com/teikjun/duke
        // with minor modifications
        String[] words = text.split(" ", 2);
        String commandWord = words[0];
        String argument = words.length > 1 ? words[1] : "";
        // @@author

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
                String[] arguments = splitArgument(argument, DeadlineCommand.COMMAND_SEPARATOR);
                return new DeadlineCommand(taskList, new Deadline(arguments[0], arguments[1]));
            case EventCommand.COMMAND_WORD:
                arguments = splitArgument(argument, EventCommand.COMMAND_SEPARATOR);
                return new EventCommand(taskList, new Event(arguments[0], arguments[1]));
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(taskList, Integer.parseInt(argument) - 1);
            case FindCommand.COMMAND_WORD:
                return new FindCommand(taskList, argument);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new InvalidCommand("Oops! That's not a proper number.");
        }
    }

    /**
     * Splits the given argument into description and date, separated by the command separator.
     *
     * @param argument         The String to split.
     * @param commandSeparator The String to split the argument by.
     * @return An array containing the description and date.
     * @throws DukeException If the command separator is not found, or if the description or date is empty.
     */
    public static String[] splitArgument(String argument, String commandSeparator) throws DukeException {
        int index = argument.indexOf(commandSeparator);
        if (index == -1) {
            throw new DukeException(commandSeparator + " not found.");
        }

        try {
            String description = argument.substring(0, index - 1);
            String date = argument.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The description and date cannot be empty.");
        }
    }
}
