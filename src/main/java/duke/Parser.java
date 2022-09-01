package duke;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Parser to make sense of user input.
 */
public class Parser {

    /**
     * Parses a command from a string.
     *
     * @param userInput The string to parse.
     * @param taskList The task list to pass in to the Command.
     * @return The command corresponding to the user input.
     */
    public static Command parseCommand(String userInput, TaskList taskList) {
        try {
            String[] inputArr = userInput.split(" ", 2);
            CommandType command = CommandType.parse(inputArr[0]);
            switch (command) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand(taskList);
            case MARK:
                return new MarkCommand(taskList, inputArr);
            case UNMARK:
                return new UnmarkCommand(taskList, inputArr);
            case TODO:
                return new ToDoCommand(taskList, inputArr);
            case EVENT:
                return new EventCommand(taskList, inputArr);
            case DEADLINE:
                return new DeadlineCommand(taskList, inputArr);
            case DELETE:
                return new DeleteCommand(taskList, inputArr);
            case FIND:
                return new FindCommand(taskList, inputArr);
            default:
                return new InvalidCommand("Invalid command.");
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}

