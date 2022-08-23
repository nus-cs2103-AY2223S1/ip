package duke.util;

import duke.DukeException;
import duke.command.*;
import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-yy HHmm");
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

    public static int parseInt(String num) throws DukeException {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new ParseException(num);
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ParseException(dateTime);
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
            switch (TaskSymbolType.valueOf(splits[0])) {
            case T:
                task = new TodoTask(splits[2]);
                break;
            case D:
                task = new DeadlineTask(splits[2], parseDateTime(splits[3]));
                break;
            case E:
                task = new EventTask(splits[2], parseDateTime(splits[3]));
                break;
            default:
                throw new ParseException(input);
            }
            task.setDone(Integer.parseInt(splits[1]) == 1);
        } catch (Exception e) {
            throw new ParseException(input);
        }
        return task;
    }
}
