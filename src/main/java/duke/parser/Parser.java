package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
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

public class Parser {

    public static final Pattern STORED_TASK_DATA_FORMAT =
            Pattern.compile("(N|M)\\|(event|todo|deadline)\\s+(.+)\\s+(/at|/by|/empty)\\s+(.*)");

    public static final Pattern STORED_TASK_DATA_RAW_FORMAT =
            Pattern.compile("(N|M)\\|(event|todo|deadline)\\s+(.*)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

//    public static final Pattern STORED_TASK_DATA_FORMAT =
//            Pattern.compile("(?<marked>\\S+)(?<commandWord>\\S+)(?<arguments>.*)");

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

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        default:
            return new IncorrectCommand("Command not found!");
        }
    }

    private Command prepareTodo(String args) {
        return new TodoCommand(args.trim());
    }

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

    private static String[] parseDeadlineArgument(String args) throws DukeException {
        String description = "";
        String by = "";

        String[] splitted = args.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
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
        strArr[0] = description;
        strArr[1] = by;
        return strArr;
    }

    private static String[] parseEventArgument(String args) throws DukeException {
        String description = "";
        String at = "";

        String[] splitted = args.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
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
        strArr[0] = description;
        strArr[1] = at;
        return strArr;
    }

    private Command prepareMark(String args) {
        int taskIndex;
        try {
            taskIndex = Integer.valueOf(args.trim());
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Mark should be given a number input :)");
        }
        return new MarkCommand(taskIndex);
    }

    private Command prepareUnmark(String args) {
        int taskIndex;
        try {
            taskIndex = Integer.valueOf(args.trim());
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Unmark should be given a number input :)");
        }
        return new UnmarkCommand(taskIndex);
    }

    private Command prepareDelete(String args) {
        int taskIndex;
        try {
            taskIndex = Integer.valueOf(args.trim());
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Delete should be given a number input :)");
        }
        return new DeleteCommand(taskIndex);
    }

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

//        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(arguments.trim());
//        if (!matcher.matches()) {
//            throw new DukeInvalidCommandException();
//        }

//        final String commandWord = matcher.group("commandWord");
//        final String taskArguments = matcher.group("arguments");

        Task t;

        switch(commandWord) {

        case TodoCommand.COMMAND_WORD:
            t = new Todo(m.group(2));
            break;

        case DeadlineCommand.COMMAND_WORD:
            String[] deadlineStrArr = parseDeadlineArgument(m.group(2));
            t = new Deadline(deadlineStrArr[0], deadlineStrArr[1]);
            break;

        case EventCommand.COMMAND_WORD:
            String[] eventStrArr = parseEventArgument(m.group(2));
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
