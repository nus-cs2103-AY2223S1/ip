package duke.util;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DisplayListCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkDoneCommand;
import duke.command.MarkUndoneCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeCommandFormatException;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeMissingIndexException;
import duke.exception.DukeMissingTaskDateTimeException;
import duke.exception.DukeMissingTaskTitleException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A command parser that parses user commands. It returns the corresponding Command based on input string, employing a
 * range of helper methods.
 */
public class Parser {

    private static final String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DELIMITER = "/";
    private static final String BY_DATE_DELIMITER = "/by";
    private static final String AT_DATE_DELIMITER = "/at";
    private static final String MISSING_AT_DELIMITER_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time with the delimiter " + AT_DATE_DELIMITER;
    private static final String MISSING_BY_DELIMITER_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time with the delimiter " + BY_DATE_DELIMITER;
    private static final String INDEX_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the index, starting from 1.";
    private static final String TASK_TITLE_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the title of the task.";
    private static final String TASK_DATE_TIME_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time of the task.";
    private static final String DATE_TIME_FORMAT_ERROR_MESSAGE =
            "Oops! The date and time should follow the format: " + INPUT_DATE_TIME_FORMAT;

    private static final Function<String, Command> addDeadlineCommandSupplier = commandArgument -> {
        Command newCommand;
        try {
            String taskTitle = getTaskTitle(commandArgument);
            LocalDateTime deadline = getByDate(commandArgument);
            DeadlineTask task = new DeadlineTask(taskTitle, deadline);
            newCommand = new AddDeadlineCommand(task);
        } catch (DukeMissingTaskTitleException | DukeCommandFormatException | DukeMissingTaskDateTimeException
                 | DukeDateTimeFormatException exception) {
            newCommand = new ErrorCommand(exception.getMessage());
        }
        return newCommand;
    };

    private static final Function<String, Command> addEventCommandSupplier = commandArgument -> {
        Command newCommand;
        try {
            String taskTitle = getTaskTitle(commandArgument);
            LocalDateTime dateTime = getAtDate(commandArgument);
            EventTask task = new EventTask(taskTitle, dateTime);
            newCommand = new AddEventCommand(task);
        } catch (DukeMissingTaskTitleException | DukeCommandFormatException | DukeMissingTaskDateTimeException
                 | DukeDateTimeFormatException exception) {
            newCommand = new ErrorCommand(exception.getMessage());
        }
        return newCommand;
    };

    private static final Function<String, Command> addTodoCommandSupplier = commandArgument -> {
        Command newCommand;
        try {
            String taskTitle = getTaskTitle(commandArgument);
            TodoTask task = new TodoTask(taskTitle);
            newCommand = new AddTodoCommand(task);
        } catch (DukeMissingTaskTitleException exception) {
            newCommand = new ErrorCommand(exception.getMessage());
        }
        return newCommand;
    };

    private static final Function<String, Command> deleteCommandSupplier = commandArgument -> {
        Command newCommand;
        try {
            int taskIndex = getTaskIndexFromCommand(commandArgument);
            newCommand = new DeleteCommand(taskIndex);
        } catch (DukeMissingIndexException exception) {
            newCommand = new ErrorCommand(exception.getMessage());
        }
        return newCommand;
    };

    private static final Function<String, Command> displayListCommandSupplier =
            commandArgument -> new DisplayListCommand();

    private static final Function<String, Command> exitCommandSupplier = commandArgument -> new ExitCommand();

    private static final Function<String, Command> findCommandSupplier = FindCommand::new;

    private static final Function<String, Command> markDoneCommandSupplier = commandArgument -> {
        Command newCommand;
        try {
            int taskIndex = getTaskIndexFromCommand(commandArgument);
            newCommand = new MarkDoneCommand(taskIndex);
        } catch (DukeMissingIndexException exception) {
            newCommand = new ErrorCommand(exception.getMessage());
        }
        return newCommand;
    };

    private static final Function<String, Command> markUndoneCommandSupplier = commandArgument -> {
        Command newCommand;
        try {
            int taskIndex = getTaskIndexFromCommand(commandArgument);
            newCommand = new MarkUndoneCommand(taskIndex);
        } catch (DukeMissingIndexException exception) {
            newCommand = new ErrorCommand(exception.getMessage());
        }
        return newCommand;
    };

    private static final Function<String, Command> unknownCommandSupplier = commandArgument -> new UnknownCommand();

    private final Map<String, Function<String, Command>> commandMap;

    /**
     * Initialises the commandMap that maps command instructions to their corresponding Command supplier/generator.
     * The standard constructor.
     */
    public Parser() {
        commandMap = new HashMap<>();
        commandMap.put(CommandType.ADD_DEADLINE.toString(), addDeadlineCommandSupplier);
        commandMap.put(CommandType.ADD_EVENT.toString(), addEventCommandSupplier);
        commandMap.put(CommandType.ADD_TODO.toString(), addTodoCommandSupplier);
        commandMap.put(CommandType.DELETE.toString(), deleteCommandSupplier);
        commandMap.put(CommandType.DISPLAY_LIST.toString(), displayListCommandSupplier);
        commandMap.put(CommandType.EXIT.toString(), exitCommandSupplier);
        commandMap.put(CommandType.FIND.toString(), findCommandSupplier);
        commandMap.put(CommandType.MARK_DONE.toString(), markDoneCommandSupplier);
        commandMap.put(CommandType.MARK_UNDONE.toString(), markUndoneCommandSupplier);
    }

    /**
     * Transforms a command string to a real Command object, which can be subsequently executed.
     *
     * @param input The command string read by Ui.
     * @return The corresponding Command object.
     */
    public Command parse(String input) {
        String instruction = getCommandInstruction(input);
        String argument = getCommandArgument(input);
        Function<String, Command> supplier = commandMap.getOrDefault(instruction, unknownCommandSupplier);
        return supplier.apply(argument);
    }

    private static int getIndexOfFirstOccurrence(String input, String pattern) {
        int indexOfFirstOccurrence = input.indexOf(pattern);
        if (indexOfFirstOccurrence == -1) {
            indexOfFirstOccurrence = input.length();
        }
        return indexOfFirstOccurrence;
    }

    private static int getIndexOfFirstWhiteSpace(String input) {
        return getIndexOfFirstOccurrence(input, " ");
    }

    private static int getIndexOfFirstDelimiter(String input) {
        return getIndexOfFirstOccurrence(input, DELIMITER);
    }

    private static String getCommandInstruction(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        String rawInstruction = input.substring(0, indexOfFirstWhiteSpace);
        return removeHeadingAndTailingWhiteSpaces(rawInstruction);
    }

    private static String getCommandArgument(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        String rawArgument = input.substring(indexOfFirstWhiteSpace);
        return removeHeadingAndTailingWhiteSpaces(rawArgument);
    }

    private static String getTaskTitle(String commandArgument) throws DukeMissingTaskTitleException {
        int indexOfEnd = getIndexOfFirstDelimiter(commandArgument);
        String roughTaskTitle = commandArgument.substring(0, indexOfEnd);
        String realTaskTitle = removeHeadingAndTailingWhiteSpaces(roughTaskTitle);
        if (realTaskTitle.isEmpty()) {
            throw new DukeMissingTaskTitleException(TASK_TITLE_MISSING_ERROR_MESSAGE);
        }
        return removeHeadingAndTailingWhiteSpaces(roughTaskTitle);
    }

    private static LocalDateTime getByDate(String commandArgument)
            throws DukeCommandFormatException, DukeMissingTaskDateTimeException, DukeDateTimeFormatException {
        int indexOfDelimiter = getIndexOfFirstOccurrence(commandArgument, BY_DATE_DELIMITER);
        if (indexOfDelimiter == commandArgument.length()) {
            throw new DukeCommandFormatException(MISSING_BY_DELIMITER_ERROR_MESSAGE);
        }
        String rawDateString = commandArgument.substring(indexOfDelimiter + BY_DATE_DELIMITER.length());
        String refinedDateString = removeHeadingAndTailingWhiteSpaces(rawDateString);
        if (refinedDateString.isEmpty()) {
            throw new DukeMissingTaskDateTimeException(TASK_DATE_TIME_MISSING_ERROR_MESSAGE);
        }
        return getLocalDateTimeFromString(refinedDateString);
    }

    private static LocalDateTime getAtDate(String commandArgument)
            throws DukeCommandFormatException, DukeMissingTaskDateTimeException, DukeDateTimeFormatException {
        int indexOfDelimiter = getIndexOfFirstOccurrence(commandArgument, AT_DATE_DELIMITER);
        if (indexOfDelimiter == commandArgument.length()) {
            throw new DukeCommandFormatException(MISSING_AT_DELIMITER_ERROR_MESSAGE);
        }
        String rawDateString = commandArgument.substring(indexOfDelimiter + AT_DATE_DELIMITER.length());
        String refinedDateString = removeHeadingAndTailingWhiteSpaces(rawDateString);
        if (refinedDateString.isEmpty()) {
            throw new DukeMissingTaskDateTimeException(TASK_DATE_TIME_MISSING_ERROR_MESSAGE);
        }
        return getLocalDateTimeFromString(refinedDateString);
    }

    private static int getTaskIndexFromCommand(String commandArgument) throws DukeMissingIndexException {
        int indexOfFirstWhiteSpace = Parser.getIndexOfFirstWhiteSpace(commandArgument);
        String tailSubString = commandArgument.substring(0, indexOfFirstWhiteSpace);
        tailSubString = tailSubString.replace(" ", "");
        if (tailSubString.isEmpty()) {
            throw new DukeMissingIndexException(INDEX_MISSING_ERROR_MESSAGE);
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tailSubString) - 1; // Minus 1 because user input indices start from 1
        } catch (NumberFormatException ex) {
            throw new DukeMissingIndexException(INDEX_MISSING_ERROR_MESSAGE);
        }
        return taskIndex;
    }

    private static String removeHeadingAndTailingWhiteSpaces(String input) {
        int start = 0;
        int end = input.length();
        while (start < input.length() && Character.isWhitespace(input.charAt(start))) {
            start++;
        }
        while (end > start && Character.isWhitespace(input.charAt(end - 1))) {
            end--;
        }
        return input.substring(start, end);
    }

    private static LocalDateTime getLocalDateTimeFromString(String input) throws DukeDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT);
        LocalDateTime output;
        try {
            output = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeDateTimeFormatException(DATE_TIME_FORMAT_ERROR_MESSAGE);
        }
        return output;
    }
}
