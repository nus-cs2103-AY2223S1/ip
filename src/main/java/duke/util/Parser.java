package duke.util;

import duke.command.*;
import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser to make sense of user input.
 */
public class Parser {

    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-yy HHmm");
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

    /**
     * Parses an integer from a string.
     *
     * @param num The string to parse.
     * @return The parsed integer.
     */
    public static int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new ParseException(num);
        }
    }

    /**
     * Parses a {@code LocalDateTime} from a string.
     * The string must be in the format {@code d-M-yy HHmm}.
     *
     * @param dateTime The string to parse.
     * @return The parsed {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ParseException(dateTime);
        }
    }

    /**
     * Parses a {@code Command} from a string.
     * <p>
     * The format of the {@code Command} is {@code <command> <arg1> / <arg2> / ...}.
     * The {@code <command>} must be one of the {@code CommandType}s.
     * The case of the {@code <command>} does not matter.
     * <p>
     * The arguments are seperated by {@code " / "}.
     * There must be at least one space before and after the {@code /}.
     *
     * @param input The string to parse.
     * @return The parsed {@code Command}.
     */
    public static Command parseCommand(String input) {
        input = input.strip();
        if (input.length() == 0) {
            return new EmptyCommand();
        }

        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toUpperCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+/\\s+") : new String[0];
        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].strip();
        }

        CommandType type;
        try {
            type = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new ParseException(input, "unknown command");
        }
        if (!type.isCompatible(args)) {
            throw new ParseException(input, "wrong number of arguments provided");
        }

        switch (type) {
        case LIST:
            return new ListCommand();
        case CHECK: // fall through
        case UNCHECK:
            return new UpdateStatusCommand(parseInt(args[0]), type == CommandType.CHECK);
        case TODO:
            return new AddTaskCommand(new TodoTask(args[0]));
        case DEADLINE:
            return new AddTaskCommand(new DeadlineTask(args[0], parseDateTime(args[1])));
        case EVENT:
            return new AddTaskCommand(new EventTask(args[0], parseDateTime(args[1])));
        case DELETE:
            return new DeleteTaskCommand(parseInt(args[0]));
        case EXIT:
            return new ExitCommand();
        default:
            throw new ParseException(input, "unknown command");
        }
    }

    /**
     * Parses a {@code Task} from a string.
     * <p>
     * The format of the {@code Task} is {@code <task symbol> | <isDone> | <description> | <args> ...}.
     * The {@code <task symbol>} must be one of the {@code TaskType}s.
     * <p>
     * The {@code <isDone>} must be either {@code 0} or {@code 1}.
     * <p>
     * There should be exactly one space before and after the {@code |}.
     *
     * @param input The string to parse.
     * @return The parsed {@code Task}.
     */
    public static Task parseTask(String input) {
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
