package duke.parser;

import duke.DateHandler;
import duke.DukeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static String NO_INDEX_SPECIFIED = "No index specified, try again";
    private static String NO_TASK_NAME = "No task name defined, please try again";

    public static Command parse(String command) throws DukeException {
        DateTimeFormatter dateTimeFormatter = DateHandler.getDateTimeFormatter();
        String firstCommand = getFirstWord(command).toUpperCase();
        int targetIndex;
        int lastIdx;
        String mainTask;

        switch (firstCommand) {
            case ListCommand.COMMAND_ID:
                return new ListCommand();
            case ExitCommand.COMMAND_ID:
                return new ExitCommand();
            case DeleteCommand.COMMAND_ID:
                if (command.length() < 8) throw new DukeException(NO_INDEX_SPECIFIED);

                targetIndex = Integer.parseInt(command.substring(7));
                return new DeleteCommand(targetIndex);
            case MarkCommand.COMMAND_ID:
                if (command.length() < 6) throw new DukeException(NO_INDEX_SPECIFIED);

                targetIndex = Integer.parseInt(command.substring(5));
                return new MarkCommand(targetIndex);
            case UnMarkCommand.COMMAND_ID:
                if (command.length() < 8) throw new DukeException(NO_INDEX_SPECIFIED);

                targetIndex = Integer.parseInt(command.substring(7));
                return new UnMarkCommand(targetIndex);
            case ToDoCommand.COMMAND_ID:
                if (command.length() < 6) throw new DukeException(NO_TASK_NAME);

                String taskName = command.substring(5);

                if (taskName.isEmpty()) throw new DukeException(NO_TASK_NAME);

                Task taskToAdd = new ToDo(taskName);
                return new ToDoCommand(taskToAdd);
            case DeadlineCommand.COMMAND_ID:
                lastIdx = command.lastIndexOf("/by");
                if (lastIdx == -1)
                    throw new DukeException("Please follow the format <taskname> /by <datetime>");

                mainTask = command.substring(9, lastIdx);
                if (mainTask.isEmpty()) throw new DukeException(NO_TASK_NAME);

                String doneBy = command.substring(lastIdx + 4);
                if (doneBy.isEmpty()) throw new DukeException("No deadline given, please try again");

                LocalDateTime doneByDate = LocalDateTime.parse(doneBy, dateTimeFormatter);
                taskToAdd = new Deadline(mainTask, doneByDate);

                return new DeadlineCommand(taskToAdd);
            case EventCommand.COMMAND_ID:
                lastIdx = command.lastIndexOf("/at");
                if (lastIdx == -1)
                    throw new DukeException("Please follow the format <taskname> /at <date and time>");

                mainTask = command.substring(6, lastIdx);
                if (mainTask.isEmpty()) throw new DukeException(NO_TASK_NAME);

                String doneAt = command.substring(lastIdx + 4);
                if (doneAt.isEmpty()) throw new DukeException("No date given, please try again");

                LocalDateTime doneAtDate = LocalDateTime.parse(doneAt, dateTimeFormatter);
                taskToAdd = new Event(mainTask, doneAtDate);

                return new EventCommand(taskToAdd);
            default: // Invalid Command Handler
                return new InvalidCommand();
        }
    }

    static String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.
            return text.substring(0, index).trim(); // Extract first word.
        }

        return text;
    }
}
