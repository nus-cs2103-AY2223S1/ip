package duke;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.SortTasksCommand;
import org.apache.commons.text.WordUtils;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteTaskCommand;
import duke.command.EmptyCommand;
import duke.command.FindTasksCommand;
import duke.command.ListTasksCommand;
import duke.command.SetTaskAsDoneCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

public final class Parser {

    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-y HHmm");
    public static final DateTimeFormatter DATETIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    private static final String LINE = "──────────────────────────────────────────\n";
    private static final PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

    private Parser() {
    }

    /**
     * Pretty-prints an output string
     *
     * @param output The string representing the output.
     */
    public static void printMsg(String output) {
        String[] lines = output.split("\n");
        String newStr = Arrays.stream(lines).map(line ->
                        String.format("\t %s%s\n", line.replace(line.stripLeading(), ""),
                                WordUtils.wrap(line, 40, "\n\t ", false)))
                .reduce("", String::concat);
        pw.printf("\t%s%s\t%s\n", LINE, newStr, LINE);
        pw.flush();
    }

    /**
     * Parses a {@code Command} from a string input
     *
     * @param input The input string.
     * @return the parsed {@code Command}
     * @throws DukeException when the string to be parsed is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] args = input.stripLeading().split(" ", 2);
        CommandType commandType = CommandType.fromString(args[0].toUpperCase());

        if (commandType == null) {
            throw new DukeException("Invalid command: Please try again.");
        }

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListTasksCommand();
        case EMPTY:
            return new EmptyCommand();
        case MARK:
            return parseMarkUnmarkCommand(args, true);
        case UNMARK:
            return parseMarkUnmarkCommand(args, false);
        case TODO:
            return parseAddTodoCommand(args);
        case DEADLINE:
            return parseAddDeadlineCommand(args);
        case EVENT:
            return parseAddEventCommand(args);
        case DELETE:
            return parseDeleteCommand(args);
        case FIND:
            return parseFindCommand(args);
        case SORT:
            return parseSortCommand(args);
        default:
            throw new DukeException("Invalid command: Please try again.");
        }
    }

    public static Command parseMarkUnmarkCommand(String[] args, boolean isDone) throws DukeException {
        if (args.length == 1) {
            throw new DukeException("Wrong number of arguments.");
        }
        try {
            int index = Integer.parseInt(args[1]) - 1;
            return new SetTaskAsDoneCommand(index, isDone);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid argument: Index of task should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
        }
    }

    public static Command parseAddTodoCommand(String[] args) throws DukeException {
        if (args.length == 1) {
            throw new DukeException("Wrong number of arguments.");
        }
        String description = args[1];
        if (description.isBlank()) {
            throw new DukeException("Invalid argument: Description cannot be blank.");
        }
        return new AddTaskCommand(new TodoTask(description, false));
    }

    public static Command parseAddDeadlineCommand(String[] args) throws DukeException {
        if (args.length == 1) {
            throw new DukeException("Wrong number of arguments.");
        }
        try {
            String[] split = args[1].split(" /by ", 2);
            String description = split[0];
            if (description.isBlank()) {
                throw new DukeException("Invalid argument: Description cannot be blank.");
            }
            String deadline = split[1];
            if (deadline.isEmpty()) {
                throw new DukeException("Invalid argument: Deadline cannot be empty.");
            }
            return new AddTaskCommand(new DeadlineTask(description, Parser.parseDateTime(deadline), false));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid argument: Use /by flag to specify the deadline of the task");
        }
    }

    public static Command parseAddEventCommand(String[] args) throws DukeException {
        if (args.length == 1) {
            throw new DukeException("Wrong number of arguments.");
        }
        try {
            String[] split = args[1].split(" /at ", 2);
            String description = split[0];
            if (description.isBlank()) {
                throw new DukeException("Invalid argument: Description cannot be blank.");
            }
            String timeAt = split[1];
            if (timeAt.isEmpty()) {
                throw new DukeException("Invalid argument: Event time cannot be empty.");
            }
            return new AddTaskCommand(new EventTask(description, Parser.parseDateTime(timeAt), false));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Invalid argument: Use /at flag to specify the date and time of the event.");
        }
    }

    public static Command parseDeleteCommand(String[] args) throws DukeException {
        if (args.length == 1) {
            throw new DukeException("Wrong number of arguments.");
        }
        try {
            int index = Integer.parseInt(args[1]) - 1;
            return new DeleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid argument: Index of task should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
        }
    }

    public static Command parseFindCommand(String[] args) throws DukeException {
        if (args.length == 1) {
            throw new DukeException("Wrong number of arguments.");
        }
        String keyword = args[1];
        if (keyword.isBlank()) {
            throw new DukeException("Invalid argument: Keyword cannot be blank.");
        }
        try {
            return new FindTasksCommand(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Include the keyword you want to find.");
        }
    }

    public static Command parseSortCommand(String[] args) {
        // Default sort options
        String sortType = "time";
        boolean isAscending = true;

        if (args.length != 1) {
            sortType = args[1].split(" ", 2)[0];
            isAscending = !args[1].contains("/d");
            System.out.println(sortType + (isAscending ? "asc" : "desc"));
        }
        return new SortTasksCommand(sortType, isAscending);
    }

    /**
     * Parses a {@code LocalDateTime} from a string.
     * The string must be in the format "d-M-y HHmm".
     *
     * @param dateTime The string to parse.
     * @return The parsed {@code LocalDateTime}.
     * @throws DukeException when dateTime is not in correct format
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime should be in the format \"d-M-y HHmm\"");
        }
    }
}
