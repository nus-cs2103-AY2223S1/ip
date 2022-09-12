package stashy.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import stashy.commands.AddDeadlineCommand;
import stashy.commands.AddEventCommand;
import stashy.commands.AddTodoCommand;
import stashy.commands.Command;
import stashy.commands.DeleteCommand;
import stashy.commands.ExitCommand;
import stashy.commands.FindCommand;
import stashy.commands.HelpCommand;
import stashy.commands.ListCommand;
import stashy.commands.MarkCommand;
import stashy.commands.UnmarkCommand;
import stashy.data.exception.StashyException;
import stashy.data.task.Deadline;
import stashy.data.task.Event;
import stashy.data.task.Task;
import stashy.data.task.ToDo;

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
     * @param helpShown Whether to show help or not
     * @return A Command object to be executed
     * @throws StashyException If there is any issue encountered
     */
    public static Command parseCommand(String fullCommand, boolean helpShown) throws StashyException {
        assert helpShown || !fullCommand.strip().isEmpty() : "Either help is shown or fullCommand should not be empty";

        String[] splittedCommands = fullCommand.strip().split(" ");
        String keyword = splittedCommands[0];
        String[] remainingCommands = IntStream.range(1, splittedCommands.length)
                .mapToObj(i -> splittedCommands[i])
                .toArray(size -> new String[size]);
        switch (keyword) {
        case ExitCommand.KEYWORD:
            return new ExitCommand(helpShown);
        case ListCommand.KEYWORD:
            return new ListCommand(helpShown);
        case MarkCommand.KEYWORD:
            return prepareMark(remainingCommands, helpShown);
        case UnmarkCommand.KEYWORD:
            return prepareUnmark(remainingCommands, helpShown);
        case DeleteCommand.KEYWORD:
            return prepareDelete(remainingCommands, helpShown);
        case AddTodoCommand.KEYWORD:
            return prepareAddTodoCommand(remainingCommands, helpShown);
        case AddDeadlineCommand.KEYWORD:
            return prepareAddDeadlineCommand(remainingCommands, helpShown);
        case AddEventCommand.KEYWORD:
            return prepareAddEventCommand(remainingCommands, helpShown);
        case FindCommand.KEYWORD:
            return prepareFindCommand(remainingCommands, helpShown);
        case HelpCommand.KEYWORD:
            return prepareHelp(remainingCommands);
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
        assert isDone || matcher.group("isDone").equals(" ")
            : "isDone regex capturing group must be either an 'X' or a space";

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
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return A MarkCommand object
     * @throws StashyException If an invalid argument or task ID is given
     */
    private static MarkCommand prepareMark(String[] remainingCommands, boolean helpShown) throws StashyException {
        if (helpShown) {
            return new MarkCommand();
        }
        if (remainingCommands.length != 1) {
            throw new StashyException("Invalid number of arguments given >:(");
        }
        try {
            int taskId = Integer.parseInt(remainingCommands[0]);
            assert taskId > 0 : "Task ID cannot be negative";
            return new MarkCommand(taskId);
        } catch (NumberFormatException nfe) {
            throw new StashyException("Invalid task ID given! Please give an integer instead");
        }
    }

    /**
     * Prepares an UnmarkCommand object to be executed.
     *
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return An UnmarkCommand object
     * @throws StashyException If an invalid argument or task ID is given
     */
    private static UnmarkCommand prepareUnmark(String[] remainingCommands, boolean helpShown) throws StashyException {
        if (helpShown) {
            return new UnmarkCommand();
        }
        if (remainingCommands.length != 1) {
            throw new StashyException("Invalid number of arguments given >:(");
        }
        try {
            int taskId = Integer.parseInt(remainingCommands[0]);
            assert taskId > 0 : "Task ID cannot be negative";
            return new UnmarkCommand(taskId);
        } catch (NumberFormatException nfe) {
            throw new StashyException("Invalid task ID given! Please give an integer instead");
        }
    }

    /**
     * Prepares a DeleteCommand object to be executed.
     *
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return A DeleteCommand object
     * @throws StashyException If an invalid argument or task ID is given
     */
    private static DeleteCommand prepareDelete(String[] remainingCommands, boolean helpShown) throws StashyException {
        if (helpShown) {
            return new DeleteCommand();
        }
        if (remainingCommands.length != 1) {
            throw new StashyException("Invalid number of arguments given >:(");
        }
        try {
            int taskId = Integer.parseInt(remainingCommands[0]);
            assert taskId > 0 : "Task ID cannot be negative";
            return new DeleteCommand(taskId);
        } catch (NumberFormatException nfe) {
            throw new StashyException("Invalid task ID given! Please give an integer instead");
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
                LocalDateTime byDateTime = LocalDateTime.parse(byString,
                        DateTimeFormatter.ofPattern(dateTimeFormat)
                                .withResolverStyle(ResolverStyle.SMART));
                return new Deadline(description, byDateTime, isDone);
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }
        throw new StashyException("Invalid datetime format given!\n"
            + "Consider using \"dd MMM yyyy HH:mm\"\n"
            + "Example: 20 Oct 2020 13:57");
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
                LocalDateTime atDateTime = LocalDateTime.parse(atString,
                        DateTimeFormatter.ofPattern(dateTimeFormat)
                                .withResolverStyle(ResolverStyle.SMART));
                return new Event(description, atDateTime, isDone);
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }
        throw new StashyException("Invalid datetime format given!\n"
            + "Consider using \"dd MMM yyyy HH:mm\"\n"
            + "Example: 20 Oct 2020 13:57");
    }

    /**
     * Prepares an AddTodoCommand object to be executed.
     *
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return An AddTodoCommand object
     * @throws StashyException If the to-do description is empty
     */
    private static AddTodoCommand prepareAddTodoCommand(String[] remainingCommands, boolean helpShown)
            throws StashyException {
        if (helpShown) {
            return new AddTodoCommand();
        }
        String description = String.join(" ", Arrays.asList(remainingCommands)).strip();
        if (description.isEmpty()) {
            throw new StashyException("Please don't give me an empty todo description :(");
        }
        ToDo todo = new ToDo(description);
        return new AddTodoCommand(todo);
    }

    /**
     * Prepares an AddDeadlineCommand object to be executed.
     *
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return An AddDeadlineCommand object
     * @throws StashyException If the Deadline object cannot be created
     */
    private static AddDeadlineCommand prepareAddDeadlineCommand(String[] remainingCommands, boolean helpShown)
            throws StashyException {
        if (helpShown) {
            return new AddDeadlineCommand();
        }
        if (Arrays.stream(remainingCommands).anyMatch("/by"::equals)
                && Arrays.stream(remainingCommands).anyMatch("/at"::equals)) {
            throw new StashyException("You cannot provide both /by and /at simultaneously!");
        }
        String description = "";
        String byString = "";
        int pos = 0;
        while (pos < remainingCommands.length) {
            if (remainingCommands[pos].equals("/by")) {
                pos++;
                break;
            }
            description += remainingCommands[pos++] + " ";
        }
        while (pos < remainingCommands.length) {
            byString += remainingCommands[pos++] + " ";
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
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return An AddEventCommand object
     * @throws StashyException If the Event object cannot be created
     */
    private static AddEventCommand prepareAddEventCommand(String[] remainingCommands, boolean helpShown)
            throws StashyException {
        if (helpShown) {
            return new AddEventCommand();
        }
        if (Arrays.stream(remainingCommands).anyMatch("/by"::equals)
                && Arrays.stream(remainingCommands).anyMatch("/at"::equals)) {
            throw new StashyException("You cannot provide both /by and /at simultaneously!");
        }
        String description = "";
        String atString = "";
        int pos = 0;
        while (pos < remainingCommands.length) {
            if (remainingCommands[pos].equals("/at")) {
                pos++;
                break;
            }
            description += remainingCommands[pos++] + " ";
        }
        while (pos < remainingCommands.length) {
            atString += remainingCommands[pos++] + " ";
        }
        if (description.isEmpty()) {
            throw new StashyException("Please don't give me an empty event description :(");
        }
        Event event = parseEvent(description.strip(), atString.strip(), false);
        return new AddEventCommand(event);
    }

    /**
     * Prepares a FindCommand object to be executed.
     *
     * @param remainingCommands The commands with the keyword excluded
     * @param helpShown Whether to show the help for this command or not
     * @return A FindCommand object
     * @throws StashyException If the query string is empty
     */
    private static FindCommand prepareFindCommand(String[] remainingCommands, boolean helpShown)
            throws StashyException {
        if (helpShown) {
            return new FindCommand();
        }
        String query = String.join(" ", Arrays.asList(remainingCommands)).strip();
        if (query.isEmpty()) {
            throw new StashyException("Please don't give me an empty search query :(");
        }
        return new FindCommand(query);
    }

    /**
     * Prepares a Command object to show the help instead.
     *
     * @param remainingCommands The commands with the keyword excluded
     * @return A Command object, depending on what's parsed next
     * @throws StashyException If any exception is caught
     */
    private static Command prepareHelp(String[] remainingCommands) throws StashyException {
        String joinedRemainingCommands = Arrays
            .stream(remainingCommands)
            .collect(Collectors.joining(" ")).strip().toLowerCase();
        if (joinedRemainingCommands.isEmpty()) {
            return new HelpCommand();
        } else {
            return parseCommand(joinedRemainingCommands, true);
        }
    }
}
