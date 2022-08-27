package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DateHandler;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnMarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a Parser for Duke
 */
public class Parser {
    private static final String NO_INDEX_SPECIFIED = "No index specified, try again";
    private static final String NO_TASK_NAME = "No task name defined, please try again";
    private static final String NO_QUERY_SPECIFIED = "No query specified, please try again";
    private static final DateTimeFormatter dateTimeFormatter = DateHandler.getDateTimeFormatter();

    /**
     * Parses the given String command based on a list of available commands.
     * @param command full command from Scanner
     * @return a Command instance
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException {
        String firstCommand = getFirstWord(command).toUpperCase();

        switch (firstCommand) {
        case ListCommand.COMMAND_ID:
            return new ListCommand();
        case ExitCommand.COMMAND_ID:
            return new ExitCommand();
        case DeleteCommand.COMMAND_ID:
            return executeDeleteCommand(command);
        case MarkCommand.COMMAND_ID:
            return executeMarkCommand(command);
        case UnMarkCommand.COMMAND_ID:
            return executeUnMarkCommand(command);
        case ToDoCommand.COMMAND_ID:
            return executeToDoCommand(command);
        case DeadlineCommand.COMMAND_ID:
            return executeDeadlineCommand(command);
        case EventCommand.COMMAND_ID:
            return executeEventCommand(command);
        case FindCommand.COMMAND_ID:
            return executeFindCommand(command);
        default: // Invalid Command Handler
            return new InvalidCommand();
        }
    }

    private static DeleteCommand executeDeleteCommand(String command) throws DukeException {
        if (command.length() < 8) {
            throw new DukeException(NO_INDEX_SPECIFIED);
        }

        int targetIndex = Integer.parseInt(command.substring(7));
        return new DeleteCommand(targetIndex);
    }

    private static MarkCommand executeMarkCommand(String command) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException(NO_INDEX_SPECIFIED);
        }

        int targetIndex = Integer.parseInt(command.substring(5));
        return new MarkCommand(targetIndex);
    }

    private static UnMarkCommand executeUnMarkCommand(String command) throws DukeException {
        if (command.length() < 8) {
            throw new DukeException(NO_INDEX_SPECIFIED);
        }

        int targetIndex = Integer.parseInt(command.substring(7));
        return new UnMarkCommand(targetIndex);
    }

    private static ToDoCommand executeToDoCommand(String command) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException(NO_TASK_NAME);
        }

        String taskName = command.substring(5);

        if (taskName.isEmpty()) {
            throw new DukeException(NO_TASK_NAME);
        }

        Task taskToAdd = new ToDo(taskName);
        return new ToDoCommand(taskToAdd);
    }

    private static DeadlineCommand executeDeadlineCommand(String command) throws DukeException {
        int lastIdx = command.lastIndexOf("/by");
        if (lastIdx == -1) {
            throw new DukeException("Please follow the format <taskname> /by <datetime>");
        }

        String mainTask = command.substring(9, lastIdx);
        if (mainTask.isEmpty()) {
            throw new DukeException(NO_TASK_NAME);
        }

        String doneBy = command.substring(lastIdx + 4);
        if (doneBy.isEmpty()) {
            throw new DukeException("No deadline given, please try again");
        }

        LocalDateTime doneByDate = LocalDateTime.parse(doneBy, Parser.dateTimeFormatter);
        Task taskToAdd = new Deadline(mainTask, doneByDate);

        return new DeadlineCommand(taskToAdd);
    }

    private static EventCommand executeEventCommand(String command) throws DukeException {
        int lastIdx = command.lastIndexOf("/at");
        if (lastIdx == -1) {
            throw new DukeException("Please follow the format <taskname> /at <date and time>");
        }

        String mainTask = command.substring(6, lastIdx);
        if (mainTask.isEmpty()) {
            throw new DukeException(NO_TASK_NAME);
        }

        String doneAt = command.substring(lastIdx + 4);
        if (doneAt.isEmpty()) {
            throw new DukeException("No date given, please try again");
        }

        LocalDateTime doneAtDate = LocalDateTime.parse(doneAt, Parser.dateTimeFormatter);
        Task taskToAdd = new Event(mainTask, doneAtDate);

        return new EventCommand(taskToAdd);
    }

    private static FindCommand executeFindCommand(String command) throws DukeException {
        String query;
        String mainTask = command.substring(5);
        if (!mainTask.isEmpty()) {
            query = mainTask;
            return new FindCommand(query);
        } else {
            throw new DukeException(NO_QUERY_SPECIFIED);
        }
    }

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) {
            return text.substring(0, index).trim();
        }

        return text;
    }
}
