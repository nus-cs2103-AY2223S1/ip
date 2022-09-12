package duke;

import java.util.Arrays;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * A class to parse user input
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Takes in a list of the tasks where the parsed tasks will be stored.
     *
     * @param taskList A list of the tasks
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    private static String generateInvalidArgumentsMessage(String action) {
        return "OOPS!!! The " + action + " must have valid arguments.\n";
    }
    /**
     * Returns a {@code String} representing the parsed input
     *
     * @param command            The user input
     * @return A {@code String} representing the input
     * @throws CustomMessageException if invalid input is given
     */
    public Command parseUserCommand(String command)
            throws CustomMessageException {
        String[] arguments = command.split("\\s+");
        CommandType taskType;
        try {
            taskType = CommandType.valueOf(arguments[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomMessageException(("OOPS!!! I'm sorry, but I "
                    + "don't know what that means :-("));
        }
        arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
        if (!taskType.isNumberOfArgsCorrect(arguments.length)) {
            throw new CustomMessageException((generateInvalidArgumentsMessage(taskType.getString())));
        }
        switch (taskType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand(taskList);
        case MARK:
            return new MarkCommand(taskList, arguments);
        case UNMARK:
            return new UnmarkCommand(taskList, arguments);
        case DELETE:
            return new DeleteCommand(taskList, arguments);
        case TODO:
            return new AddTaskCommand(taskList, arguments, command, arguments.length, CommandType.TODO, "");
        case DEADLINE:
            return new AddTaskCommand(taskList, arguments, command, arguments.length, CommandType.DEADLINE, " /by ");
        case EVENT:
            return new AddTaskCommand(taskList, arguments, command, arguments.length, CommandType.EVENT, " /at ");
        case FIND:
            return new FindCommand(taskList, arguments);
        default:
            throw new CustomMessageException((
                    "OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
        }
    }
}
