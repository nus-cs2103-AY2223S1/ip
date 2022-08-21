package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.commands.ColourCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.common.Messages;

/**
 * Parses user input.
 */
public class Parser {

    // commandWord arguments (. - matches any character, * - zero or more times)
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    // any character, one or more times
    private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    private static final Pattern COLOUR_FORMAT = Pattern
            .compile("\\b(black|red|green|yellow|blue|magenta|cyan|white)\\b");
    // string of any length
    private static final Pattern TODO_FORMAT = Pattern.compile("(?<description>.+)");
    // string /by string
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<description>.+\\S+)/by\\S+(?<dateTime>.+)");
    // string /at string
    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>.+\\S+)/at\\S+(?<dateTime>.+)");
    // 1 word
    private static final Pattern FIND_FORMAT = Pattern.compile("^\\S+$");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }

        final String command = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch (command) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ColourCommand.COMMAND_WORD:
            return prepareColour(arguments);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    private Command prepareTodo(String args) {
        try {
            final String description = parseTodo(args);
            return new TodoCommand(description);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDeadline(String args) {
        try {
            final String[] desTime = parseDeadline(args);
            return new DeadlineCommand(desTime[0], desTime[1]);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareEvent(String args) {
        try {
            final String[] desTime = parseEvent(args);
            return new EventCommand(desTime[0], desTime[1]);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareFind(String args) {
        final Matcher matcher = FIND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_ARGUMENTS);
        }
        return new FindCommand(args.trim());
    }

    private Command prepareColour(String args) {
        final Matcher matcher = COLOUR_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_ARGUMENTS);
        }
        return new ColourCommand(args.trim());
    }

    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareMark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkCommand(targetIndex);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareUnmark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new UnmarkCommand(targetIndex);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private String parseTodo(String args) throws DukeException {
        final Matcher matcher = TODO_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
        }
        return args.trim();
    }

    private String[] parseDeadline(String args) throws DukeException {
        if (!args.contains("/by")) {
            throw new DukeException(String.format(Messages.MESSAGE_MISSING_SEPARATOR, "/by"));
        }
        return parseArgsAsDescriptionAndDate(DEADLINE_FORMAT, args);
    }

    private String[] parseEvent(String args) throws DukeException {
        if (!args.contains("/at")) {
            throw new DukeException(String.format(Messages.MESSAGE_MISSING_SEPARATOR, "/at"));
        }
        return parseArgsAsDescriptionAndDate(EVENT_FORMAT, args);
    }

    private int parseArgsAsDisplayedIndex(String args) throws DukeException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(Messages.MESSAGE_TASK_NOT_SPECIFIED);
        }

        final int targetIndex;
        try {
            targetIndex = Integer.parseInt(matcher.group("targetIndex"));
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.MESSAGE_TASK_NOT_SPECIFIED);
        }

        return targetIndex;
    }

    private String[] parseArgsAsDescriptionAndDate(Pattern taskFormat, String args) throws DukeException {
        final Matcher matcher = taskFormat.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION_TIME);
        }

        String[] descriptionTimeArray = new String[2];
        descriptionTimeArray[0] = matcher.group("description").trim();
        descriptionTimeArray[1] = matcher.group("dateTime").trim();
        return descriptionTimeArray;
    }
}
