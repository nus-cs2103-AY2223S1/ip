package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.TasksList;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses a user input string to a Command.
     *
     * @param userInput The input string by the user.
     * @param tasksList The TasksList to pass into the Command.
     * @return The corresponding command to the user input.
     * @throws DukeException if there is no command corresponding to the user input.
     */
    public static Command handleCommand(String userInput, TasksList tasksList) throws DukeException {
        String[] inputArray = userInput.trim().split("\\s+", 2);
        CommandType commandType = CommandType.parseToCommand(inputArray[0].toLowerCase());

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand(tasksList);
        case MARK:
            return new MarkCommand(tasksList, inputArray);
        case UNMARK:
            return new UnmarkCommand(tasksList, inputArray);
        case TODO:
            return new ToDoCommand(tasksList, inputArray);
        case DEADLINE:
            return new DeadlineCommand(tasksList, inputArray);
        case EVENT:
            return new EventCommand(tasksList, inputArray);
        case DELETE:
            return new DeleteCommand(tasksList, inputArray);
        case FIND:
            return new FindCommand(tasksList, inputArray);
        default:
            throw new DukeException("Please enter a valid request / command!");
        }
    }
}
