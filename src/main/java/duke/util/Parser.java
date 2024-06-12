package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

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
import duke.command.SortCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeCommandFormatException;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeMissingIndexException;
import duke.exception.DukeMissingTaskDateTimeException;
import duke.exception.DukeMissingTaskTitleException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

/**
 * A command parser that parses user commands. It returns the corresponding Command based on input string, employing a
 * range of helper methods.
 */
public class Parser {

    private static final String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DELIMITER = "/";
    private static final String BY_DATE_DELIMITER = "/by";
    private static final String AT_DATE_DELIMITER = "/at";
    private static final String MISSING_DATE_DELIMITER_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time with the delimiter ";
    private static final String INDEX_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the index, starting from 1.";
    private static final String TASK_TITLE_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the title of the task.";
    private static final String TASK_DATE_TIME_MISSING_ERROR_MESSAGE =
            "Oops! You didn't specify the date and time of the task.";
    private static final String DATE_TIME_FORMAT_ERROR_MESSAGE =
            "Oops! The date and time should follow the format: " + INPUT_DATE_TIME_FORMAT;

    /**
     * A nested private class that supplies commands
     */
    private static final class CommandSupplier {

        private static final Function<? super String, ? extends Command> ADD_DEADLINE_COMMAND_SUPPLIER =
                CommandSupplier::createDeadlineCommand;

        private static final Function<? super String, ? extends Command> ADD_EVENT_COMMAND_SUPPLIER =
                CommandSupplier::createAddEventCommand;

        private static final Function<? super String, ? extends Command> ADD_TODO_COMMAND_SUPPLIER =
                CommandSupplier::createAddTodoCommand;

        private static final Function<? super String, ? extends Command> DELETE_COMMAND_SUPPLIER =
                CommandSupplier::createDeleteCommand;

        private static final Function<? super String, ? extends Command> DISPLAY_LIST_COMMAND_SUPPLIER =
                commandArgument -> new DisplayListCommand();

        private static final Function<? super String, ? extends Command> EXIT_COMMAND_SUPPLIER =
                commandArgument -> new ExitCommand();

        private static final Function<? super String, ? extends Command> FIND_COMMAND_SUPPLIER = FindCommand::new;

        private static final Function<? super String, ? extends Command> MARK_DONE_COMMAND_SUPPLIER =
                CommandSupplier::createMarkDoneCommand;

        private static final Function<? super String, ? extends Command> MARK_UNDONE_COMMAND_SUPPLIER =
                CommandSupplier::createMarkUndoneCommand;

        private static final Function<? super String, ? extends Command> UNKNOWN_COMMAND_SUPPLIER =
                commandArgument -> new UnknownCommand();

        private static final Function<? super String, ? extends Command> SORT_COMMAND_SUPPLIER = x -> new SortCommand();

        private static final List<Function<? super String, ? extends Command>> suppliers =
                List.of(
                        ADD_DEADLINE_COMMAND_SUPPLIER,
                        ADD_EVENT_COMMAND_SUPPLIER,
                        ADD_TODO_COMMAND_SUPPLIER,
                        DELETE_COMMAND_SUPPLIER,
                        DISPLAY_LIST_COMMAND_SUPPLIER,
                        EXIT_COMMAND_SUPPLIER,
                        FIND_COMMAND_SUPPLIER,
                        MARK_DONE_COMMAND_SUPPLIER,
                        MARK_UNDONE_COMMAND_SUPPLIER,
                        UNKNOWN_COMMAND_SUPPLIER,
                        SORT_COMMAND_SUPPLIER
                );

        private static final List<String> instructions = List.of(
                CommandType.ADD_DEADLINE.toString(),
                CommandType.ADD_EVENT.toString(),
                CommandType.ADD_TODO.toString(),
                CommandType.DELETE.toString(),
                CommandType.DISPLAY_LIST.toString(),
                CommandType.EXIT.toString(),
                CommandType.FIND.toString(),
                CommandType.MARK_DONE.toString(),
                CommandType.MARK_UNDONE.toString(),
                CommandType.UNKNOWN.toString(),
                CommandType.SORT.toString()
        );

        /**
         * Returns a collection of all static command suppliers.
         *
         * @return all static command suppliers.
         */
        public static List<Function<? super String, ? extends Command>> getSuppliers() {
            return suppliers;
        }

        /**
         * Returns a collection of all static command instructions.
         *
         * @return all static command instructions.
         */
        public static List<String> getInstructions() {
            return instructions;
        }

        private static Command createDeadlineCommand(String commandArgument) {
            Command newCommand;
            try {
                String taskTitle = getTaskTitle(commandArgument);
                LocalDateTime deadline = getDate(commandArgument, BY_DATE_DELIMITER);
                DeadlineTask task = new DeadlineTask(taskTitle, deadline);
                newCommand = new AddDeadlineCommand(task);
            } catch (DukeMissingTaskTitleException | DukeCommandFormatException
                     | DukeMissingTaskDateTimeException
                     | DukeDateTimeFormatException exception) {
                newCommand = new ErrorCommand(exception.getMessage());
            }
            return newCommand;
        }

        private static Command createAddEventCommand(String commandArgument) {
            Command newCommand;
            try {
                String taskTitle = getTaskTitle(commandArgument);
                LocalDateTime dateTime = getDate(commandArgument, AT_DATE_DELIMITER);
                EventTask task = new EventTask(taskTitle, dateTime);
                newCommand = new AddEventCommand(task);
            } catch (DukeMissingTaskTitleException | DukeCommandFormatException
                     | DukeMissingTaskDateTimeException
                     | DukeDateTimeFormatException exception) {
                newCommand = new ErrorCommand(exception.getMessage());
            }
            return newCommand;
        }

        private static Command createAddTodoCommand(String commandArgument) {
            Command newCommand;
            try {
                String taskTitle = getTaskTitle(commandArgument);
                TodoTask task = new TodoTask(taskTitle);
                newCommand = new AddTodoCommand(task);
            } catch (DukeMissingTaskTitleException exception) {
                newCommand = new ErrorCommand(exception.getMessage());
            }
            return newCommand;
        }

        private static Command createDeleteCommand(String commandArgument) {
            Command newCommand;
            try {
                int taskIndex = getTaskIndexFromCommand(commandArgument);
                newCommand = new DeleteCommand(taskIndex);
            } catch (DukeMissingIndexException exception) {
                newCommand = new ErrorCommand(exception.getMessage());
            }
            return newCommand;
        }

        private static Command createMarkDoneCommand(String commandArgument) {
            Command newCommand;
            try {
                int taskIndex = getTaskIndexFromCommand(commandArgument);
                newCommand = new MarkDoneCommand(taskIndex);
            } catch (DukeMissingIndexException exception) {
                newCommand = new ErrorCommand(exception.getMessage());
            }
            return newCommand;
        }

        private static Command createMarkUndoneCommand(String commandArgument) {
            Command newCommand;
            try {
                int taskIndex = getTaskIndexFromCommand(commandArgument);
                newCommand = new MarkUndoneCommand(taskIndex);
            } catch (DukeMissingIndexException exception) {
                newCommand = new ErrorCommand(exception.getMessage());
            }
            return newCommand;
        }
    }


    private final Map<String, Function<? super String, ? extends Command>> commandMap;

    /**
     * Initialises the commandMap that maps command instructions to their corresponding Command supplier/generator.
     * The standard constructor.
     */
    public Parser() {
        commandMap = new HashMap<>();
        List<Function<? super String, ? extends Command>> suppliers = CommandSupplier.getSuppliers();
        List<String> instructions = CommandSupplier.getInstructions();
        IntStream.range(0, instructions.size())
                .forEach(x -> commandMap.put(instructions.get(x), suppliers.get(x)));
    }

    /**
     * Transforms a command string to a real Command object, which can be subsequently executed.
     *
     * @param input The command string read by Ui.
     * @return The corresponding Command object.
     */
    public Command parse(String input) {
        assert (input != null);
        String instruction = getCommandInstruction(input);
        String argument = getCommandArgument(input);
        Function<? super String, ? extends Command> supplier =
                commandMap.getOrDefault(instruction, commandMap.get(CommandType.UNKNOWN.toString()));
        Command command = supplier.apply(argument);
        assert (command != null);
        return command;
    }

    private static int getIndexOfFirstOccurrence(String input, String pattern) {
        assert (input != null);
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
        return rawInstruction.strip();
    }

    private static String getCommandArgument(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        String rawArgument = input.substring(indexOfFirstWhiteSpace);
        return rawArgument.strip();
    }

    private static String getTaskTitle(String... commandArgument) throws DukeMissingTaskTitleException {
        assert (commandArgument[0] != null);
        int indexOfEnd = getIndexOfFirstDelimiter(commandArgument[0]);
        String roughTaskTitle = commandArgument[0].substring(0, indexOfEnd);
        String realTaskTitle = roughTaskTitle.strip();
        if (realTaskTitle.isEmpty()) {
            throw new DukeMissingTaskTitleException(TASK_TITLE_MISSING_ERROR_MESSAGE);
        }
        return roughTaskTitle.strip();
    }

    private static LocalDateTime getDate(String commandArgument, String delimiter)
            throws DukeCommandFormatException, DukeMissingTaskDateTimeException, DukeDateTimeFormatException {
        int indexOfDelimiter = getIndexOfFirstOccurrence(commandArgument, delimiter);
        if (indexOfDelimiter == commandArgument.length()) {
            throw new DukeCommandFormatException(TASK_DATE_TIME_MISSING_ERROR_MESSAGE);
        }
        String rawDateString = commandArgument.substring(indexOfDelimiter + BY_DATE_DELIMITER.length());
        String refinedDateString = rawDateString.strip();
        if (refinedDateString.isEmpty()) {
            throw new DukeMissingTaskDateTimeException(MISSING_DATE_DELIMITER_ERROR_MESSAGE + delimiter);
        }
        return getLocalDateTimeFromString(refinedDateString);
    }

    private static int getTaskIndexFromCommand(String... commandArgument) throws DukeMissingIndexException {
        int indexOfFirstWhiteSpace = Parser.getIndexOfFirstWhiteSpace(commandArgument[0]);
        String tailSubString = commandArgument[0].substring(0, indexOfFirstWhiteSpace);
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
