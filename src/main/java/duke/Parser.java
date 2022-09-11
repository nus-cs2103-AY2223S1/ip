package duke;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteTaskCommand;
import duke.command.EmptyCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SetDoneCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String LINE = "──────────────────────────────────────────\n";

    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-y HHmm");
    public static final DateTimeFormatter DATETIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

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
        System.out.printf("\t%s%s\t%s%n", LINE, newStr, LINE);
    }

    /**
     * Parses a {@code Command} from a string input
     *
     * @param input The input string.
     * @return the parsed {@code Command}
     * @throws DukeException when the string to be parsed is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);
        CommandType commandType = CommandType.fromString(inputs[0].toUpperCase());

        if (commandType == null) {
            throw new DukeException("Invalid command: Please try again.");
        }

        String description;

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case EMPTY:
            return new EmptyCommand();
        case MARK:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                return new SetDoneCommand(index, true);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
        case UNMARK:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                return new SetDoneCommand(index, false);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
        case TODO:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            description = inputs[1];
            if (description.length() == 0) {
                throw new DukeException("Invalid argument: Description cannot be empty.");
            }
            return new AddTaskCommand(new TodoTask(description, false));
        case DEADLINE:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                description = inputs[1].substring(0, inputs[1].indexOf(" /by "));
                if (description.length() == 0) {
                    throw new DukeException("Invalid argument: Description cannot be empty.");
                }
                String deadline = inputs[1].substring(inputs[1].indexOf(" /by ") + 5);
                if (deadline.length() == 0) {
                    throw new DukeException("Invalid argument: Deadline cannot be empty.");
                }
                return new AddTaskCommand(new DeadlineTask(description, Parser.parseDateTime(deadline), false));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Use /by flag to specify the deadline of the task");
            }
        case EVENT:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                description = inputs[1].substring(0, inputs[1].indexOf(" /at "));
                if (description.length() == 0) {
                    throw new DukeException("Invalid argument: Description cannot be empty.");
                }
                String time = inputs[1].substring(inputs[1].indexOf(" /at ") + 5);
                if (time.length() == 0) {
                    throw new DukeException("Invalid argument: Event time cannot be empty.");
                }
                return new AddTaskCommand(new EventTask(description, Parser.parseDateTime(time), false));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Use /at flag to specify the date and time of the event.");
            }
        case DELETE:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            try {
                int index = Integer.parseInt(inputs[1]) - 1;
                return new DeleteTaskCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid argument: Index of task should be a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
            }
        case FIND:
            if (inputs.length == 1) {
                throw new DukeException("Wrong number of arguments.");
            }
            description = inputs[1];
            if (description.length() == 0) {
                throw new DukeException("Invalid argument: Description cannot be empty.");
            }
            try {
                return new FindCommand(description);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Include the keyword you want to find.");
            }
        default:
            throw new DukeException("Invalid command: Please try again.");
        }
    }

    /**
     * Parses a {@code Task} from a string (from the Duke data file).
     *
     * @param s The string read from the Duke data file to be parsed.
     * @return the parsed {@code Task}
     * @throws DukeException when the string in the data file is invalid.
     */
    public static Task parseTask(String s) throws DukeException {
        String[] strings = s.split(" \\| ", -1);
        Task task;
        if (!strings[1].equals(" ") && !strings[1].equals("X")) {
            throw new DukeException("Error parsing Task");
        }
        boolean isDone = strings[1].equals("X");
        String description = strings[2];

        switch (strings[0]) {
        case "T":
            if (strings.length > 3) {
                throw new DukeException("Error parsing TodoTask");
            }
            task = new TodoTask(description, isDone);
            break;
        case "D":
            if (strings.length > 4) {
                throw new DukeException("Error parsing DeadlineTask");
            }
            task = new DeadlineTask(description, parseDateTime(strings[3]), isDone);
            break;
        case "E":
            if (strings.length > 4) {
                throw new DukeException("Error parsing EventTask");
            }
            task = new EventTask(description, parseDateTime(strings[3]), isDone);
            break;
        default:
            throw new DukeException("Error parsing Task");
        }
        return task;
    }

    /**
     * Parses a {@code LocalDateTime} from a string.
     * The string must be in the format "d-M-y HHmm".
     *
     * @param dateTime The string to parse.
     * @return The parsed {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime should be in the format \"d-M-y HHmm\"");
        }
    }
}
