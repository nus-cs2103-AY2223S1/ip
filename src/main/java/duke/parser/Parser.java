package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteTaskCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.UpdateStatusCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskSymbolType;
import duke.task.TodoTask;

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
     * @param rawInput The string to parse.
     * @return The parsed {@code Command}.
     */
    public static Command parseCommand(String rawInput) {
        String input = rawInput.strip();
        if (input.length() == 0) {
            return new EmptyCommand();
        }

        String[] args = getTokens(input);
        CommandType type = getCommandType(args, rawInput);

        switch (type) {
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand();
        case CHECK: // fall through
        case UNCHECK:
            return new UpdateStatusCommand(parseInt(args[1]), type == CommandType.CHECK);
        case TODO:
            return new AddTaskCommand(new TodoTask(args[1]));
        case DEADLINE:
            return new AddTaskCommand(new DeadlineTask(args[1], parseDateTime(args[2])));
        case EVENT:
            return new AddTaskCommand(new EventTask(args[1], parseDateTime(args[2])));
        case FIND:
            return new FindCommand(args[1]);
        case DELETE:
            return new DeleteTaskCommand(parseInt(args[1]));
        case EXIT:
            return new ExitCommand();
        default:
            throw new ParseException(input, "unknown command; enter \"help\" for available commands");
        }
    }

    /**
     * Returns the {@code CommandType} from an argument list.
     * In case of an exception, the {@code rawInput} is used to provide more information.
     *
     * @param args     The argument list.
     * @param rawInput The raw input string.
     * @return The {@code CommandType}.
     */
    private static CommandType getCommandType(String[] args, String rawInput) {
        CommandType type;
        try {
            type = CommandType.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(rawInput, "unknown command; enter \"help\" for available commands");
        }
        if (!type.isCompatible(args)) {
            throw new ParseException(rawInput, "wrong number of arguments provided");
        }
        return type;
    }

    /**
     * Splits the input string into tokens.
     * The first token is seperated from the rest by any amount of whitespace.
     * The rest of the tokens (if any) are seperated by {@code " / "} with
     * any amount of whitespace on either side.
     *
     * @param input The string to split.
     * @return The tokens.
     */
    private static String[] getTokens(String input) {
        String[] parts = input.split("\\s+", 2);
        if (parts.length == 1) {
            return parts;
        }

        // split the rest of the string by " / " and strip the tokens
        String[] args = parts[1].split("\\s+/\\s+");
        Arrays.setAll(args, i -> args[i].strip());

        // merge the first token with the rest of the tokens
        String[] result = new String[args.length + 1];
        result[0] = parts[0];
        System.arraycopy(args, 0, result, 1, args.length);

        return result;
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

        TaskSymbolType type = getTaskSymbolType(splits, input);
        Task task = getTask(splits, type, input);
        task.setDone(getIsDone(splits, input));

        return task;
    }

    /**
     * Returns the {@code TaskSymbolType} from an argument list of a Task.
     * In case of an exception, the {@code input} is used to provide more information.
     *
     * @param splits The argument list.
     * @param input  The input string.
     * @return The {@code TaskSymbolType}.
     */
    private static TaskSymbolType getTaskSymbolType(String[] splits, String input) {
        TaskSymbolType type;
        try {
            type = TaskSymbolType.valueOf(splits[0]);
        } catch (IllegalArgumentException e) {
            throw new ParseException(input, "unknown task symbol");
        }
        if (!type.isCompatible(splits)) {
            throw new ParseException(input, "wrong number of arguments provided");
        }
        return type;
    }

    /**
     * Returns the {@code Task} from an argument list of a Task.
     * In case of an exception, the {@code input} is used to provide more information.
     * Assumes the {@code TaskSymbolType} is compatible with the argument list.
     *
     * @param splits The argument list.
     * @param type   The {@code TaskSymbolType}.
     * @param input  The input string.
     * @return The parsed {@code Task}.
     */
    private static Task getTask(String[] splits, TaskSymbolType type, String input) {
        switch (type) {
        case T:
            return new TodoTask(splits[2]);
        case D:
            return new DeadlineTask(splits[2], parseDateTime(splits[3]));
        case E:
            return new EventTask(splits[2], parseDateTime(splits[3]));
        default:
            // should not reach here as we assume type is valid.
            throw new ParseException(input, "unknown task symbol");
        }
    }

    /**
     * Returns the {@code isDone} from an argument list of a Task.
     * In case of an exception, the {@code input} is used to provide more information.
     *
     * @param splits The argument list.
     * @param input  The input string.
     * @return The {@code isDone}.
     */
    private static boolean getIsDone(String[] splits, String input) {
        int isDone;
        try {
            isDone = parseInt(splits[1]);
        } catch (NumberFormatException e) {
            throw new ParseException(input, "invalid isDone value");
        }
        if (isDone != 0 && isDone != 1) {
            throw new ParseException(input, "invalid isDone value");
        }
        return isDone == 1;
    }
}
