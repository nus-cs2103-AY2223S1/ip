package util;

import command.*;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Parser {
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
                // Regex "\\s+/" matches one or more space followed by a /
                String[] taskArgs = splitted[1].split("\\s+/", 2);
                Task deadline = new Deadline(taskArgs[0], taskArgs[1]);
                cmd = new AddTaskCommand(deadline);
                break;
            case "event":
                // Regex "\\s+/" matches one or more space followed by a /
                taskArgs = splitted[1].split("\\s+/", 2);
                Task event = new Event(taskArgs[0], taskArgs[1]);
                cmd = new AddTaskCommand(event);
                break;
            default:
                throw new DukeException(DukeException.ErrorCode.UNKNOWN_CMD);
        }
        return cmd;
    }

    public static Command parseEncodedTask(String encodedTask) throws DukeException {
        String[] splitted = encodedTask.split(",", 4);
        String taskType = splitted[0];
        Task taskItem;

        switch (taskType) {
            case "T":
                taskItem = new Todo(splitted[2]);
                break;
            case "E":
                taskItem = new Event(splitted[2], splitted[3]);
                break;
            case "D":
                taskItem = new Deadline(splitted[2], splitted[3]);
                break;
            default:
                throw new DukeException(DukeException.ErrorCode.UNKNOWN_TASK_ENCODING);
        }
        taskItem.setIsMarked(splitted[1].equals("1"));
        return new AddTaskFromStorageCommand(taskItem);
    }
}
