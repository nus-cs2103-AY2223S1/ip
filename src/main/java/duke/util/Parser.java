package duke.util;

import duke.DukeException;
import duke.command.*;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static int parseInt(String num) throws DukeException {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid argument: " + num);
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid argument: " + dateTime);
        }
    }

    public static Command parseCommand(String input) throws DukeException {
        if (input.length() == 0) {
            return new EmptyCommand();
        }

        String[] parts = input.strip().split("\\s+", 2);
        String command = parts[0].toUpperCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+/\\s+") : new String[0];
        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].strip();
        }

        CommandType type;
        try {
            type = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Unknown command: " + command.toLowerCase());
        }
        if (!type.isCompatible(args)) {
            throw new DukeException("Wrong number of arguments provided!");
        }

        switch (type) {
        case LIST:
            return new ListCommand();
        case CHECK: // fall through
        case UNCHECK:
            return new UpdateStatusCommand(parseInt(args[0]) - 1, type == CommandType.CHECK);
        case TODO:
            return new AddTaskCommand(new TodoTask(args[0]));
        case DEADLINE:
            return new AddTaskCommand(new DeadlineTask(args[0], parseDateTime(args[1])));
        case EVENT:
            return new AddTaskCommand(new EventTask(args[0], parseDateTime(args[1])));
        case DELETE:
            return new DeleteTaskCommand(parseInt(args[0]) - 1);
        case EXIT:
            return new ExitCommand();
        default:
            throw new DukeException("Unknown command: " + command.toLowerCase());
        }
    }

    public static Task parseTask(String input) throws DukeException {
        String[] splits = input.split(" \\| ", -1);
        Task task;
        try {
            switch (splits[0]) {
            case "T":
                task = new TodoTask(splits[2]);
                break;
            case "D":
                task = new DeadlineTask(splits[2], parseDateTime(splits[3]));
                break;
            case "E":
                task = new EventTask(splits[2], parseDateTime(splits[3]));
                break;
            default:
                throw new DukeException("Datafile is corrupted");
            }
            task.setDone(Integer.parseInt(splits[1]) == 1);
        } catch (Exception e) {
            // any type of exception means the datafile is corrupted
            throw new DukeException("Datafile is corrupted");
        }
        return task;
    }
}
