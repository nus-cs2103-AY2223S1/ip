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

import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeInvalidFormatException;

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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new DukeInvalidCommandException();
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

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
        String description = "";
        String by = "";

        String[] splitted = args.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].equals("/by")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                by = by + splitted[i] + " ";
            } else {
                description = description + splitted[i] + " ";
            }
        }

        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("deadline", "/by");
        }

        String[] strArr = new String[2];
        strArr[0] = description.trim();
        strArr[1] = by.trim();
        return strArr;
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
        String description = "";
        String at = "";

        String[] splitted = args.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].equals("/at")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                at = at + splitted[i] + " ";
            } else {
                description = description + splitted[i] + " ";
            }
        }

        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("event", "/at");
        }

        String[] strArr = new String[2];
        strArr[0] = description.trim();
        strArr[1] = at.trim();
        return strArr;
    }

    /**
     * Prepares the arguments for initialising a Mark command
     *
     * @param args the description for initialising a Mark command
     * @return the command of initialising a Mark command
     */
    private Command prepareMark(String args) {
        int taskIndex;
        try {
            taskIndex = Integer.valueOf(args.trim());
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Mark should be given a number input :)");
        }
        return new MarkCommand(taskIndex);
    }

    /**
     * Prepares the arguments for initialising an Unmark command
     *
     * @param args the description for initialising an Unmark command
     * @return the command of initialising an Unmark command
     */
    private Command prepareUnmark(String args) {
        int taskIndex;
        try {
            taskIndex = Integer.valueOf(args.trim());
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Unmark should be given a number input :)");
        }
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Prepares the arguments for initialising a Delete command
     *
     * @param args the description for initialising a Delete command
     * @return the command of initialising a Delete command
     */
    private Command prepareDelete(String args) {
        int taskIndex;
        try {
            taskIndex = Integer.valueOf(args.trim());
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Delete should be given a number input :)");
        }
        return new DeleteCommand(taskIndex);
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

        boolean isMarked = false;

        Matcher m = STORED_TASK_DATA_RAW_FORMAT.matcher(args);

        m.find();

        final String markedStatus = m.group(1);
        final String commandWord = m.group(2);

        switch (markedStatus) {
        case "M":
            isMarked = true;
            break;
        case "N":
        default:
            isMarked = false;
            break;
        }

        Task t;

        switch(commandWord) {

        case TodoCommand.COMMAND_WORD:
            t = new Todo(m.group(3).trim());
            break;

        case DeadlineCommand.COMMAND_WORD:
            String[] deadlineStrArr = parseDeadlineArgument(m.group(3));
            t = new Deadline(deadlineStrArr[0], deadlineStrArr[1]);
            break;

        case EventCommand.COMMAND_WORD:
            String[] eventStrArr = parseEventArgument(m.group(3));
            t = new Event(eventStrArr[0], eventStrArr[1]);
            break;

        default:
            t = null;
            break;
        }

        if (t == null) {
            throw new DukeException("hi");
        }

        if (isMarked) {
            t.markAsDone();
        }

        return t;
    }
}
