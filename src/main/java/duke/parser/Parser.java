package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.IllegalDateFormatException;
import duke.exception.IllegalInputException;
import duke.exception.IllegalSyntaxException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineTaskException;
import duke.exception.InvalidDeleteCommandException;
import duke.exception.InvalidEditCommandException;
import duke.exception.InvalidEventTaskException;
import duke.exception.InvalidFindCommandException;
import duke.exception.InvalidMarkCommand;
import duke.exception.InvalidTodoTaskException;
import duke.exception.InvalidUnmarkCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A parser class that encapsulates the action of parsing string to desired Object.
 */
public class Parser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S++)(?<arguments>.*)");
    private static final Pattern EDIT_FORMAT = Pattern.compile("(?<index>\\S++)(?<newTask>.*)");
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<description>.*)/by(?<date>.*)");
    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>.*)/at(?<date>.*)");

    //@@author wweqg-reused
    //Reused from https://github.com/RussellDash332/ip/blob/master/src/main/java/stashy/parser/Parser.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date format.
    private static final String[] ACCEPTABLE_DATETIME_FORMATS =
        {
        "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
        "yyyy-MM-dd'T'HH:mm", "dd/MM/yyyy HHmm",
        "dd/MM/yyyy HH:mm", "yyyy/MM/dd HHmm",
        "yyyy/MM/dd HH:mm", "yyyy/MM/dd'T'HHmm",
        "yyyy/MM/dd'T'HH:mm", "yyyy-MM-dd HHmm",
        "yyyy-MM-dd HH:mm", "dd MMM yyyy HHmm",
        "dd MMM yyyy HH:mm", "MMM dd, yyyy HHmm", "MMM dd, yyyy HH:mm"
        };
    //@@author

    /***
     * Parses the user input to specific command
     * @param fullCommand User input
     * @return Command that needs to be executed
     * @throws DukeException throws an exception when there is unexpected input
     */
    public static Command parse(String fullCommand) throws DukeException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(fullCommand.strip());
        if (!matcher.matches()) {
            throw new IllegalSyntaxException("Invalid Command Syntax");
        }

        final String commandWord = matcher.group("commandWord").strip();
        final String arguments = matcher.group("arguments").strip();

        switch (commandWord.toUpperCase()) {
        case ExitCommand.COMMAND:
            return new ExitCommand();

        case ListCommand.COMMAND:
            return new ListCommand();

        case HelpCommand.COMMAND:
            return new HelpCommand();

        case MarkCommand.COMMAND:
            return parseToMarkCommand(arguments);

        case UnmarkCommand.COMMAND:
            return parseToUnmarkCommand(arguments);

        case DeleteCommand.COMMAND:
            return parseToDeleteCommand(arguments);

        case AddTodoCommand.COMMAND:
            return parseToAddTodoCommand(arguments);

        case AddDeadlineCommand.COMMAND:
            return parseToAddDeadlineCommand(arguments);

        case AddEventCommand.COMMAND:
            return parseToAddEventCommand(arguments);

        case FindCommand.COMMAND:
            return parseToFindCommand(arguments);

        case EditCommand.COMMAND:
            return parseToEditCommand(arguments);

        default:
            throw new InvalidCommandException();
        }

    }

    private static Command parseToDeleteCommand(String arguments) throws InvalidDeleteCommandException {
        try {
            int indexToDelete = parseToTaskIndex(arguments);
            return new DeleteCommand(indexToDelete);
        } catch (IllegalInputException e) {
            throw new InvalidDeleteCommandException("Please enter a number for index + \n "
                    + DeleteCommand.MESSAGE_USAGE);
        }
    }

    private static Command parseToUnmarkCommand(String arguments) throws InvalidUnmarkCommandException {
        try {
            int indexToUnmark = parseToTaskIndex(arguments);
            return new UnmarkCommand(indexToUnmark);
        } catch (IllegalInputException e) {
            throw new InvalidUnmarkCommandException("Please enter a number for index + \n "
                    + UnmarkCommand.MESSAGE_USAGE);
        }
    }

    private static Command parseToMarkCommand(String arguments) throws InvalidMarkCommand {
        try {
            int indexToMark = parseToTaskIndex(arguments);
            return new MarkCommand(indexToMark);
        } catch (IllegalInputException e) {
            throw new InvalidMarkCommand("Please enter a number for index + \n "
                    + MarkCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the string command into index if possible.
     *
     * @param commandDescription command description
     * @return the index of a task
     * @throws IllegalInputException When user did not include an index
     */
    public static int parseToTaskIndex(String commandDescription) throws IllegalInputException {
        if (commandDescription.matches("\\d++")) {
            return Integer.parseInt(commandDescription);
        } else {
            throw new IllegalInputException("OOPS!! I do not recognise this task number");
        }
    }

    private static Command parseToEditCommand(String arguments) throws DukeException {
        final Matcher matcher = EDIT_FORMAT.matcher(arguments.strip());
        if (!matcher.matches()) {
            throw new InvalidEditCommandException("Invalid syntax for edit command + \n"
                    + EditCommand.MESSAGE_USAGE);
        }

        final String index = matcher.group("index").strip();
        final String newTask = matcher.group("newTask").strip();

        try {
            int taskToReschedule = parseToTaskIndex(index);
            Command rescheduledTaskCommand = parse(newTask);
            AddCommand rescheduledTaskAddCommand = (AddCommand) rescheduledTaskCommand;
            Task editedTask = rescheduledTaskAddCommand.getTask();
            return new EditCommand(taskToReschedule, editedTask);
        } catch (InvalidCommandException unknownCommandError) {
            throw new InvalidEditCommandException("Unknown new task + \n"
                    + EditCommand.MESSAGE_USAGE);
        } catch (IllegalInputException invalidIndexError) {
            throw new InvalidEditCommandException("Please enter a positive number for index + \n "
                    + EditCommand.MESSAGE_USAGE);
        } catch (IllegalSyntaxException syntaxError) {
            throw new InvalidEditCommandException("Invalid syntax for edit command \n"
                    + EditCommand.MESSAGE_USAGE);
        } catch (ClassCastException classCastError) {
            throw new InvalidEditCommandException("Edit could only change the selected task to other task + \n"
                    + EditCommand.MESSAGE_USAGE);
        }

    }

    /**
     * Parses the string command into AddCommand that adds a Todo_task
     *
     * @param arguments user input
     * @return AddCommand instance
     * @throws InvalidTodoTaskException throws exception when the description of Todo_task is not given.
     */
    private static Command parseToAddTodoCommand(String arguments) throws InvalidTodoTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTodoTaskException("Please provide description for the task \n" + "\n"
                    + AddTodoCommand.MESSAGE_USAGE);
        }
        return new AddTodoCommand(new Todo(arguments));
    }

    private static Command parseToAddDeadlineCommand(String arguments) throws InvalidDeadlineTaskException,
            IllegalDateFormatException {
        final Matcher matcher = DEADLINE_FORMAT.matcher(arguments.strip());
        if (!matcher.matches()) {
            throw new InvalidDeadlineTaskException("Check that you have entered with correct syntax \n" + "\n"
                    + AddDeadlineCommand.MESSAGE_USAGE);
        }

        final String description = matcher.group("description").strip();
        final String date = matcher.group("date").strip();

        LocalDateTime deadlineDateAndTime = parseToLocalDateTime(date);

        return new AddDeadlineCommand(new Deadline(description, deadlineDateAndTime));
    }


    private static Command parseToAddEventCommand(String arguments) throws InvalidEventTaskException,
            IllegalDateFormatException {
        final Matcher matcher = EVENT_FORMAT.matcher(arguments.strip());
        if (!matcher.matches()) {
            throw new InvalidEventTaskException("Check that you have entered with correct syntax \n"
                    + AddEventCommand.MESSAGE_USAGE);
        }

        final String description = matcher.group("description").strip();
        final String date = matcher.group("date").strip();

        LocalDateTime deadlineDateAndTime = parseToLocalDateTime(date);

        return new AddEventCommand(new Event(description, deadlineDateAndTime));
    }

    /**
     * Parses the input String into LocalDate instance.
     *
     * @param date the date that the user has entered
     * @return a local date object
     * @throws IllegalDateFormatException throws an exception when the input date is not of correct format
     */
    public static LocalDateTime parseToLocalDateTime(String date) throws IllegalDateFormatException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(date,
                        DateTimeFormatter.ofPattern(dateTimeFormat));
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }
        throw new IllegalDateFormatException();
    }

    private static Command parseToFindCommand(String arguments) throws InvalidFindCommandException {
        if (arguments.isEmpty()) {
            throw new InvalidFindCommandException("Please provide some keywords for the search \n"
                    + FindCommand.MESSAGE_USAGE);
        }
        return new FindCommand(arguments);
    }
}
