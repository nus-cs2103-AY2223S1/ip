package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

import duke.exceptions.*;

import duke.massops.AllOperation;
import duke.massops.MassOperation;
import duke.massops.RangeOperation;
import duke.massops.SingleOperation;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the input from the user to their specific commands
 */
public class Parser {

    public static final Pattern STORED_TASK_DATA_RAW_FORMAT =
            Pattern.compile("(N|M)\\|(event|todo|deadline)\\s+(.*)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the input from the user to their commands
     *
     * @param input a string containing the command input by the user
     * @return a Command corresponding to the input string
     * @throws DukeException if the command is invalid
     */
    public Command parse(String input) throws DukeException {

        final Matcher matcher = prepareMatcher(input);

        final String commandWord = extractCommandWord(matcher);
        final String arguments = extractArguments(matcher);

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        default:
            return new IncorrectCommand("Command not found!");
        }
    }

    /**
     * Prepares the arguments for initialising a Todo command
     *
     * @param args the description for initialising a Todo command
     * @return the command of initialising a Todo command
     */
    private Command prepareTodo(String args) {
        return new TodoCommand(args.trim());
    }

    /**
     * Prepares the arguments for initialising a Deadline command
     *
     * @param args the description for initialising a Deadline command
     * @return the command of initialising a Deadline command
     */
    private Command prepareDeadline(String args) {
        try {
            String[] strArr = parseDeadlineArgument(args);
            String description = strArr[0];
            String by = strArr[1];
            return new DeadlineCommand(description.trim(), by.trim());
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Prepares the arguments for initialising an Event command
     *
     * @param args the description for initialising an Event command
     * @return the command of initialising an Event command
     */
    private Command prepareEvent(String args) {
        try {
            String[] strArr = parseEventArgument(args);
            String description = strArr[0];
            String at = strArr[1];
            return new EventCommand(description.trim(), at.trim());
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses the arguments for the Deadline arguments
     *
     * @param args string containing the description and the deadline of the task
     * @return a string array where the first element represents the description
     *     and the second element represents the deadline of the task
     * @throws DukeException if the description is empty or if the command doesn't
     *     follow a specific command
     */
    private static String[] parseDeadlineArgument(String args) throws DukeException {
        String[] splittedStrings = args.split("\\s+");
        if (splittedStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }
        String[] descAndTime = parseDeadlineDescriptionAndTime(splittedStrings);
        descAndTime = trimSpaces(descAndTime);
        return descAndTime;
    }

    /**
     * Parses the arguments for the Event arguments
     *
     * @param args string containing the description and the time of the task
     * @return a string array where the first element represents the description
     *     and the second element represents the time of the task
     * @throws DukeException if the description is empty or if the command doesn't
     *     follow a specific command
     */
    private static String[] parseEventArgument(String args) throws DukeException {
        String[] splittedStrings = args.split("\\s+");
        if (splittedStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }
        String[] descAndTime = parseEventDescriptionAndTime(splittedStrings);
        descAndTime = trimSpaces(descAndTime);
        return descAndTime;
    }

    /**
     * Prepares the arguments for initialising a Mark command
     *
     * @param args the description for initialising a Mark command
     * @return the command of initialising a Mark command
     */
    private Command prepareMark(String args) throws DukeException {
        MassOperation massOp = parseMassOpsCommand(args);
        return new MarkCommand(massOp);
    }

    /**
     * Prepares the arguments for initialising an Unmark command
     *
     * @param args the description for initialising an Unmark command
     * @return the command of initialising an Unmark command
     */
    private Command prepareUnmark(String args) throws DukeException {
        MassOperation massOperation = parseMassOpsCommand(args);
        return new UnmarkCommand(massOperation);
    }

    /**
     * Prepares the arguments for initialising a Delete command
     *
     * @param args the description for initialising a Delete command
     * @return the command of initialising a Delete command
     */
    private Command prepareDelete(String args) throws DukeException {
        MassOperation massOperation = parseMassOpsCommand(args);
        return new DeleteCommand(massOperation);
    }

    /**
     * Prepares the arguments for initialising an Event command
     *
     * @param args the description for initialising an Event command
     * @return the command of initialising an Event command
     */
    private Command prepareFind(String args) {
        return new FindCommand(args.trim());
    }

    /**
     * Parses the arguments for converting a task from storage to a Task
     * instance that can be stored in the TaskList object
     *
     * @param args the string retrieved from the local file
     * @return the task after the arguments are parsed
     */
    public static Task parseTask(String args) throws DukeException {
        Matcher m = prepareStoredDataMatcher(args);
        final boolean isMarked = isMarked(m);
        final String commandWord = extractStoredDataCommandWord(m);
        Task t;
        switch(commandWord) {
        case TodoCommand.COMMAND_WORD:
            t = createStoredTodo(m);
            break;
        case DeadlineCommand.COMMAND_WORD:
            t = createStoredDeadline(m);
            break;
        case EventCommand.COMMAND_WORD:
            t = createStoredEvent(m);
            break;
        default:
            throw new DukeException("Task was not parsed successfully");
        }
        markStoredTask(t, isMarked);
        return t;
    }

    /**
     * Prepares the Matcher object to be used for pattern matching on
     * commandWord and arguments
     *
     * @param input The input string to be applied to the matcher
     * @return The matcher object ready to be extracted
     * @throws DukeException if the input does not match the match pattern
     */
    private Matcher prepareMatcher(String input) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new DukeInvalidCommandException();
        }
        return matcher;
    }

    /**
     * Extracts the command word from a matched pattern
     *
     * @param matcher The matched character sequence with the pattern
     * @return The commandWord group of the matcher
     */
    private String extractCommandWord(Matcher matcher) {
        return matcher.group("commandWord");
    }

    /**
     * Extracts the arguments from a matched pattern
     *
     * @param matcher The matched character sequence with the pattern
     * @return The arguments group of the matcher
     */
    private String extractArguments(Matcher matcher) {
        return matcher.group("arguments");
    }

    /**
     * Parses the description and time for the arguments of the Deadline constructor
     *
     * @param splittedStrings An array of strings after being splitted
     * @return A string array containing the description as the first element and the date as the second element
     * @throws DukeException if the deadline command does not follow the specified format
     */
    private static String[] parseDeadlineDescriptionAndTime(String[] splittedStrings) throws DukeException {
        boolean isSplitterFound = false;
        String description = "";
        String time = "";
        for (int i = 0; i < splittedStrings.length; i++) {
            if (splittedStrings[i].equals("/by")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                time += (splittedStrings[i] + " ");
            } else {
                description += (splittedStrings[i] + " ");
            }
        }
        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("deadline", "/by");
        }
        return new String[] {description, time};
    }

    /**
     * Parses the description and time for the arguments of the Event constructor
     *
     * @param splittedStrings An array of strings after being splitted
     * @return A string array containing the description as the first element and the date as the second element
     * @throws DukeException if the event command does not follow the specified format
     */
    private static String[] parseEventDescriptionAndTime(String[] splittedStrings) throws DukeException {
        boolean isSplitterFound = false;
        String description = "";
        String time = "";
        for (int i = 0; i < splittedStrings.length; i++) {
            if (splittedStrings[i].equals("/at")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                time += (splittedStrings[i] + " ");
            } else {
                description += (splittedStrings[i] + " ");
            }
        }
        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("event", "/at");
        }
        return new String[] {description, time};
    }

    /**
     * Trims the trailing white spaces of the elements of a string array
     *
     * @param arr an array of strings
     * @return an array of strings with the trailing white spaces removed
     */
    private static String[] trimSpaces(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    /**
     * Checks whether a stored task is marked
     *
     * @param matcher A matched character sequence with a pattern
     * @return true if the stored task is marked, false otherwise
     */
    private static boolean isMarked(Matcher matcher) {
        final String markedStatus = matcher.group(1);
        switch (markedStatus) {
        case "M":
            return true;
        case "N":
        default:
            return false;
        }
    }

    /**
     * Prepares the matcher for the stored data
     *
     * @param args the arguments to be matched to the Stored task data pattern
     * @return the matcher containing the matched character sequence
     */
    private static Matcher prepareStoredDataMatcher(String args) {
        Matcher m = STORED_TASK_DATA_RAW_FORMAT.matcher(args);
        m.find();
        return m;
    }

    /**
     * Extracts the command word from a stored data
     *
     * @param matcher the matcher containing the matched character sequence
     * @return the extracted command word
     */
    private static String extractStoredDataCommandWord(Matcher matcher) {
        final String commandWord = matcher.group(2);
        return commandWord;
    }

    /**
     * Marks a stored task as done
     *
     * @param task a Task object from the stored task data
     * @param isMarked a boolean value, true if the task is marked, false otherwise
     */
    private static void markStoredTask(Task task, boolean isMarked) {
        if (isMarked) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    /**
     * Creates a Todo instance for the stored data
     *
     * @param m the matcher containing the matched character sequence
     * @return the Todo instance containing the description
     */
    private static Task createStoredTodo(Matcher m) {
        return new Todo(m.group(3).trim());
    }

    /**
     * Creates a Deadline instance for the stored data
     *
     * @param m the matcher containing the matched character sequence
     * @return the Deadline instance containing the description and time
     * @throws DukeException if the date cannot be parsed correctly in Deadline
     */
    private static Task createStoredDeadline(Matcher m) throws DukeException {
        String[] deadlineStrArr = parseDeadlineArgument(m.group(3));
        return createDeadlineFromArray(deadlineStrArr);
    }

    /**
     * Creates an Event instance for the stored data
     *
     * @param m the matcher containing the matched character sequence
     * @return the Event instance containing the description and time
     * @throws DukeException if the date cannot be parsed correctly in Event
     */
    private static Task createStoredEvent(Matcher m) throws DukeException {
        String[] eventStrArr = parseEventArgument(m.group(3));
        return createEventFromArray(eventStrArr);
    }

    /**
     * Creates a Deadline instance from a string array containing the description and
     * the time
     *
     * @param strArr a string array of 2 elements, containing the description and the time
     * @return the Deadline instance containing the description and time
     * @throws DukeInvalidDateException  if the date cannot be parsed correctly in Deadline
     */
    private static Task createDeadlineFromArray(String[] strArr) throws DukeInvalidDateException {
        return new Deadline(strArr[0], strArr[1]);
    }

    /**
     * Creates an Event instance from a string array containing the description and
     * the time
     *
     * @param strArr a string array of 2 elements, containing the description and the time
     * @return the Event instance containing the description and time
     * @throws DukeInvalidDateException  if the date cannot be parsed correctly in Event
     */
    private static Task createEventFromArray(String[] strArr) throws DukeInvalidDateException {
        return new Event(strArr[0], strArr[1]);
    }

    private MassOperation parseMassOpsCommand(String args) throws DukeException {
        args = args.trim();
        if (args.equals("all")) {
            return new AllOperation();
        } else if (args.contains("-")) {
            int[] range = parseRange(args);
            return new RangeOperation(range);
        } else {
            int idx = tryParseToInteger(args);
            return new SingleOperation(idx);
        }
    }

    private int[] parseRange(String args) throws DukeException {
        String[] rangeSplit = args.split("-");
        int firstValue = tryParseToInteger(rangeSplit[0]);
        int secondValue = tryParseToInteger(rangeSplit[1]);
        return new int[] { firstValue, secondValue };
    }

    private int tryParseToInteger(String args) throws DukeException {
        try {
            int idx = Integer.valueOf(args);
            return idx;
        } catch (NumberFormatException e) {
            throw new DukeException("A non-numerical value has been encountered");
        }
    }
}
