package duke.util;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to parse string representation of task in storage.
 */
public class StorageParser {
    private static final String TASK_STRING_DELIMITER = " \\| ";
    private static final String TASK_TYPE_DELIMITER = " | ";

    private static final String ERROR_MESSAGE_TASK_UNRECOGNIZED =
            "Failed to parse task from storage, task not recognized";
    private static final String ERROR_MESSAGE_PARSE_TODO_ERROR = "Failed to parse todo from storage";
    private static final String ERROR_MESSAGE_PARSE_EVENT_ERROR = "Failed to parse event from storage";
    private static final String ERROR_MESSAGE_PARSE_DEADLINE_ERROR = "Failed to parse deadline from storage";

    private static final int NUMBER_OF_ARGUMENTS_TODO = 2;
    private static final int NUMBER_OF_ARGUMENTS_EVENT = 3;
    private static final int NUMBER_OF_ARGUMENTS_DEADLINE = 3;

    private static final int INDEX_OF_TASK_TYPE = 0;
    private static final int INDEX_OF_DONE_ARGUMENT = 0;
    private static final int INDEX_OF_DESCRIPTION_ARGUMENT = 1;
    private static final int INDEX_OF_TIME_ARGUMENT = 2;

    /**
     * Parses a task string from storage into a {@code Task} object.
     *
     * @param taskString A string representation of {@code Task}.
     * @return {@code Task} object.
     * @throws DukeException Checked exceptions that may occur when parsing task string into {@code Task}.
     */
    public static Task parseTaskString(String taskString) throws DukeException {
        Character taskType = taskString.charAt(INDEX_OF_TASK_TYPE);
        String[] arguments = taskString
                .substring(taskString.indexOf(TASK_TYPE_DELIMITER) + TASK_TYPE_DELIMITER.length())
                .split(TASK_STRING_DELIMITER);
        switch (taskType) {
        case 'T':
            return parseToDo(arguments);
        case 'D':
            return parseDeadline(arguments);
        case 'E':
            return parseEvent(arguments);
        default:
            throw new DukeException(ERROR_MESSAGE_TASK_UNRECOGNIZED);
        }
    }

    private static Todo parseToDo(String[] arguments) throws DukeException {
        String parseErrorMessage = ERROR_MESSAGE_PARSE_TODO_ERROR;
        if (arguments.length != NUMBER_OF_ARGUMENTS_TODO) {
            throw new DukeException(parseErrorMessage);
        }
        try {
            boolean isDone = Integer.parseInt(arguments[INDEX_OF_DONE_ARGUMENT]) == 1 ? true : false;
            String description = arguments[INDEX_OF_DESCRIPTION_ARGUMENT];
            return new Todo(description, isDone);
        } catch (NumberFormatException e) {
            throw new DukeException(parseErrorMessage);
        }
    }

    private static Event parseEvent(String[] arguments) throws DukeException {
        String parseErrorMessage = ERROR_MESSAGE_PARSE_EVENT_ERROR;
        if (arguments.length != NUMBER_OF_ARGUMENTS_EVENT) {
            throw new DukeException(parseErrorMessage);
        }
        try {
            boolean isDone = Integer.parseInt(arguments[INDEX_OF_DONE_ARGUMENT]) == 1 ? true : false;
            String description = arguments[INDEX_OF_DESCRIPTION_ARGUMENT];
            String time = arguments[INDEX_OF_TIME_ARGUMENT];
            return new Event(description, isDone, time);
        } catch (NumberFormatException e) {
            throw new DukeException(parseErrorMessage);
        }
    }

    private static Deadline parseDeadline(String[] arguments) throws DukeException {
        String parseErrorMessage = ERROR_MESSAGE_PARSE_DEADLINE_ERROR;
        if (arguments.length != NUMBER_OF_ARGUMENTS_DEADLINE) {
            throw new DukeException(parseErrorMessage);
        }
        try {
            boolean isDone = Integer.parseInt(arguments[INDEX_OF_DONE_ARGUMENT]) == 1 ? true : false;
            String description = arguments[INDEX_OF_DESCRIPTION_ARGUMENT];
            String time = arguments[INDEX_OF_TIME_ARGUMENT];
            return new Deadline(description, isDone, time);
        } catch (NumberFormatException e) {
            throw new DukeException(parseErrorMessage);
        }
    }
}
