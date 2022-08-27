package util;

import command.*;
import date.DeadlineDateTime;
import date.EventDateTime;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Parses all input lines from command line or save file and generates commands
 * to be executed
 *
 * @author Bryan Lim Jing Xiang
 */
public class Parser {
    /**
     * Parses a single line of input from the command line
     *
     * @param inputLine Input from the command line containing a command
     *                  and its arguments
     * @return A command to be executed
     * @throws DukeException If inputLine cannot be parsed successfully
     */
    public static Command parseInputLine(String inputLine) throws DukeException {
        String[] splitted = inputLine.split("\\s+", 2);
        String command = splitted[0];
        Command cmd;
        switch (command) {
        case "bye":
            cmd = new TerminateCommand();
            break;
        case "list":
            cmd = new ListAllTasksCommand();
            break;
        case "mark":
            int index = Integer.parseInt(splitted[1]);
            cmd = new MarkTaskCommand(index);
            break;
        case "unmark":
            index = Integer.parseInt(splitted[1]);
            cmd = new UnmarkTaskCommand(index);
            break;
        case "delete":
            index = Integer.parseInt(splitted[1]);
            cmd = new DeleteTaskCommand(index);
            break;
        case "todo":
            Todo.validateInput(splitted);
            Task todo = new Todo(splitted[1]);
            cmd = new AddTaskCommand(todo);
            break;
        case "deadline":
            // Regex "\\s+/" matches one or more space followed by a /by,
            // followed by one or more space
            String[] taskArgs = splitted[1].split("\\s+/by\\s+", 2);
            DeadlineDateTime deadlineDateTime = DeadlineDateTime.parseDate(taskArgs[1]);
            Task deadline = new Deadline(taskArgs[0], deadlineDateTime);
            cmd = new AddTaskCommand(deadline);
            break;
        case "event":
            // Regex "\s+/at\s+" matches one or more space followed by a /at,
            // followed by one or more space
            taskArgs = splitted[1].split("\\s+/at\\s+", 2);
            EventDateTime eventDateTime = EventDateTime.parseDate(taskArgs[1]);
            Task event = new Event(taskArgs[0], eventDateTime);
            cmd = new AddTaskCommand(event);
            break;
        case "find":
            String searchText = splitted[1];
            cmd = new FindSearchTextCommand(searchText);
            break;
        default:
            throw new DukeException(DukeException.ErrorCode.UNKNOWN_CMD);
        }
        return cmd;
    }

    /**
     * Parses a single line of input from the save file
     *
     * @param encodedTask Encoding from a task saved in the save file
     * @return A command to be executed
     * @throws DukeException If encoded task cannot be parsed successfully
     */
    public static Command parseEncodedTask(String encodedTask) throws DukeException {
        String[] splitted = encodedTask.split(",", 4);
        String taskType = splitted[0];
        Task taskItem;

        switch (taskType) {
        case "T":
            taskItem = new Todo(splitted[2]);
            break;
        case "E":
            EventDateTime eventDateTime = EventDateTime.parseDateFromStorage(splitted[3]);
            taskItem = new Event(splitted[2], eventDateTime);
            break;
        case "D":
            DeadlineDateTime deadlineDateTime = DeadlineDateTime.parseDateFromStorage(splitted[3]);
            taskItem = new Deadline(splitted[2], deadlineDateTime);
            break;
        default:
            throw new DukeException(DukeException.ErrorCode.UNKNOWN_TASK_ENCODING);
        }
        taskItem.setIsMarked(splitted[1].equals("1"));
        return new AddTaskFromStorageCommand(taskItem);
    }
}
