package stashy.parser;

import stashy.commands.AddDeadlineCommand;
import stashy.commands.AddEventCommand;
import stashy.commands.AddTodoCommand;
import stashy.commands.Command;
import stashy.commands.DeleteCommand;
import stashy.commands.ExitCommand;
import stashy.commands.ListCommand;
import stashy.commands.MarkCommand;
import stashy.commands.UnmarkCommand;
import stashy.data.exception.StashyException;
import stashy.data.task.Deadline;
import stashy.data.task.Event;
import stashy.data.task.Task;
import stashy.data.task.ToDo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents the parser of the user input command.
 */
public class Parser {
    public static final Pattern TASK_DATA_FORMAT =
        Pattern.compile("^\\[(?<category>[TDE])\\]"
                + "\\[(?<isDone>[ X])\\]"
                + " (?<description>[^\\(\\)\\n]*)\\(?"
                + "(?<by>(by: [^\\(\\)]*)?)"
                + "(?<at>(at: [^\\(\\)]*)?)\\)?$");

    public static final String[] ACCEPTABLE_DATETIME_FORMATS = {
            "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
            "dd/MM/yyyy HHmm", "dd/MM/yyyy HH:mm",
            "yyyy/MM/dd HHmm", "yyyy/MM/dd HH:mm",
            "yyyy/MM/dd'T'HHmm", "yyyy/MM/dd'T'HH:mm",
            "yyyy-MM-dd HHmm", "yyyy-MM-dd HH:mm",
            "dd MMM yyyy HHmm", "dd MMM yyyy HH:mm",
            "MMM dd, yyyy HHmm", "MMM dd, yyyy HH:mm"};

    /**
     * Parses the user command into a Command object.
     *
     * @param fullCommand The full command provided by the user
     * @return A Command object to be executed
     * @throws StashyException If there is any issue encountered
     */
    public static Command parseCommand(String fullCommand) throws StashyException {
        String[] splitCommand = fullCommand.strip().split(" ");
        String keyword = splitCommand[0];
        String[] remainingCommand = IntStream.range(1, splitCommand.length)
                .mapToObj(i -> splitCommand[i])
                .toArray(size -> new String[size]);
        switch (keyword) {
        case ExitCommand.KEYWORD:
            return new ExitCommand();
        case ListCommand.KEYWORD:
            return new ListCommand();
        case MarkCommand.KEYWORD:
            return prepareMark(remainingCommand);
        case UnmarkCommand.KEYWORD:
            return prepareUnmark(remainingCommand);
        case DeleteCommand.KEYWORD:
            return prepareDelete(remainingCommand);
        case AddTodoCommand.KEYWORD:
            return prepareAddTodoCommand(remainingCommand);
        case AddDeadlineCommand.KEYWORD:
            return prepareAddDeadlineCommand(remainingCommand);
        case AddEventCommand.KEYWORD:
            return prepareAddEventCommand(remainingCommand);
        default:
            throw new StashyException("I have no idea what are you saying :<");
        }
    }

    /**
     * Parses a line from the raw data file into a Task object.
     *
     * @param rawLine The line from raw input data
     * @return The Task object as a parse result
     */
    public static Task parseTask(String rawLine) throws StashyException {
        final Matcher matcher = TASK_DATA_FORMAT.matcher(rawLine);
        if (!matcher.matches()) {
            throw new StashyException("Input data does not match task parser!");
        }

        final String category = matcher.group("category");
        final boolean isDone = matcher.group("isDone").equals("X");
        final String description = matcher.group("description").strip();
        final String byString = matcher.group("by").replaceAll("by: ", "").strip();
        final String atString = matcher.group("at").replaceAll("at: ", "").strip();

        switch (category) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return parseDeadline(description, byString, isDone);
        case "E":
            return parseEvent(description, atString, isDone);
        default:
            throw new StashyException("Invalid task data!");
        }
    }

    /**
     * Prepares a MarkCommand object to be executed.
     *
     * @param remainingCommand The commands with the keyword excluded
     * @return A MarkCommand object
     * @throws StashyException If an invalid argument or task ID is given
     */
    private static MarkCommand prepareMark(String[] remainingCommand) throws StashyException {
        if (remainingCommand.length != 1) {
            throw new StashyException("Invalid number of arguments given >:(");
        }
        try {
            int taskId = Integer.parseInt(remainingCommand[0]);
            return new MarkCommand(taskId);
        } catch (NumberFormatException nfe) {
            throw new StashyException("Invalid task ID given!");
        }
    }

    /**
     * Prepares an UnmarkCommand object to be executed.
     *
     * @param remainingCommand The commands with the keyword excluded
     * @return An UnmarkCommand object
     * @throws StashyException If an invalid argument or task ID is given
     */
    private static UnmarkCommand prepareUnmark(String[] remainingCommand) throws StashyException {
        if (remainingCommand.length != 1) {
            throw new StashyException("Invalid number of arguments given >:(");
        }
        try {
            int taskId = Integer.parseInt(remainingCommand[0]);
            return new UnmarkCommand(taskId);
        } catch (NumberFormatException nfe) {
            throw new StashyException("Invalid task ID given!");
        }
    }

    /**
     * Prepares a DeleteCommand object to be executed.
     *
     * @param remainingCommand The commands with the keyword excluded
     * @return A DeleteCommand object
     * @throws StashyException If an invalid argument or task ID is given
     */
    private static DeleteCommand prepareDelete(String[] remainingCommand) throws StashyException {
        if (remainingCommand.length != 1) {
            throw new StashyException("Invalid number of arguments given >:(");
        }
        try {
            int taskId = Integer.parseInt(remainingCommand[0]);
            return new DeleteCommand(taskId);
        } catch (NumberFormatException nfe) {
            throw new StashyException("Invalid task ID given!");
        }
    }


    /**
     * Specifically parses a Deadline object by trying on various datetime formats.
     *
     * @param description Description of the task
     * @param byString The datetime string of the deadline
     * @param isDone Status of the task
     * @return A Deadline object
     * @throws StashyException If the datetime cannot be parsed
     */
    private static Deadline parseDeadline(String description, String byString, boolean isDone)
            throws StashyException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                LocalDateTime by = LocalDateTime.parse(byString,
                        DateTimeFormatter.ofPattern(dateTimeFormat)
                                .withResolverStyle(ResolverStyle.SMART));
                return new Deadline(description, by, isDone);
            } catch (Exception e) {
            }
        }
        throw new StashyException("Invalid datetime format given!");
    }

    /**
     * Specifically parses an Event object by trying on various datetime formats.
     *
     * @param description Description of the task
     * @param atString The datetime string of the event time
     * @param isDone Status of the task
     * @return An Event object
     * @throws StashyException If the datetime cannot be parsed
     */
    private static Event parseEvent(String description, String atString, boolean isDone)
            throws StashyException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                LocalDateTime at = LocalDateTime.parse(atString,
                        DateTimeFormatter.ofPattern(dateTimeFormat)
                                .withResolverStyle(ResolverStyle.SMART));
                return new Event(description, at, isDone);
            } catch (Exception e) {
            }
        }
        throw new StashyException("Invalid datetime format given!");
    }

    /**
     * Prepares an AddTodoCommand object to be executed.
     *
     * @param remainingCommand The commands with the keyword excluded
     * @return An AddTodoCommand object
     */
    private static AddTodoCommand prepareAddTodoCommand(String[] remainingCommand)
            throws StashyException {
        String description = String.join(" ", Arrays.asList(remainingCommand)).strip();
        if (description.isEmpty()) {
            throw new StashyException("Please don't give me an empty todo description :(");
        }
        ToDo todo = new ToDo(description);
        return new AddTodoCommand(todo);
    }

    /**
     * Prepares an AddDeadlineCommand object to be executed.
     *
     * @param remainingCommand The commands with the keyword excluded
     * @return An AddDeadlineCommand object
     * @throws StashyException If the Deadline object cannot be created
     */
    private static AddDeadlineCommand prepareAddDeadlineCommand(String[] remainingCommand)
            throws StashyException {
        if (Arrays.stream(remainingCommand).anyMatch("/by"::equals) &&
                Arrays.stream(remainingCommand).anyMatch("/at"::equals)) {
            throw new StashyException("You cannot provide both /by and /at simultaneously!");
        }
        String description = "";
        String byString = "";
        int pos = 0;
        while (pos < remainingCommand.length) {
            if (remainingCommand[pos].equals("/by")) {
                pos++;
                break;
            }
            description += remainingCommand[pos++] + " ";
        }
        while (pos < remainingCommand.length) {
            byString += remainingCommand[pos++] + " ";
        }
        if (description.isEmpty()) {
            throw new StashyException("Please don't give me an empty deadline description :(");
        }
        Deadline deadline = parseDeadline(description.strip(), byString.strip(), false);
        return new AddDeadlineCommand(deadline);
    }

    /**
     * Prepares an AddEventCommand object to be executed.
     *
     * @param remainingCommand The commands with the keyword excluded
     * @return An AddEventCommand object
     * @throws StashyException If the Event object cannot be created
     */
    private static AddEventCommand prepareAddEventCommand(String[] remainingCommand)
            throws StashyException {
        if (Arrays.stream(remainingCommand).anyMatch("/by"::equals) &&
                Arrays.stream(remainingCommand).anyMatch("/at"::equals)) {
            throw new StashyException("You cannot provide both /by and /at simultaneously!");
        }
        String description = "";
        String atString = "";
        int pos = 0;
        while (pos < remainingCommand.length) {
            if (remainingCommand[pos].equals("/at")) {
                pos++;
                break;
            }
            description += remainingCommand[pos++] + " ";
        }
        while (pos < remainingCommand.length) {
            atString += remainingCommand[pos++] + " ";
        }
        if (description.isEmpty()) {
            throw new StashyException("Please don't give me an empty event description :(");
        }
        Event event = parseEvent(description.strip(), atString.strip(), false);
        return new AddEventCommand(event);
    }
}
