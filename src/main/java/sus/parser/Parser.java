package sus.parser;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sus.DukeException;
import sus.commands.ColourCommand;
import sus.commands.Command;
import sus.commands.DeadlineCommand;
import sus.commands.DeleteCommand;
import sus.commands.EventCommand;
import sus.commands.ExitCommand;
import sus.commands.FindCommand;
import sus.commands.HelpCommand;
import sus.commands.InvalidCommand;
import sus.commands.ListCommand;
import sus.commands.MarkCommand;
import sus.commands.TodoCommand;
import sus.commands.UnmarkCommand;
import sus.commands.UpdateCommand;
import sus.common.Messages;
import sus.common.Utils;

/**
 * Parses user input.
 */
public class Parser {

    // commandWord arguments (. - matches any character, * - zero or more times)
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    // any character, one or more times
    private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("\\d+");
    private static final Pattern COLOUR_FORMAT = Pattern
            .compile("\\b(black|red|green|yellow|blue|magenta|cyan|white)\\b");
    // string of any length
    private static final Pattern TODO_FORMAT = Pattern.compile("(?<description>.+)");
    // 1 word
    private static final Pattern FIND_FORMAT = Pattern.compile("^\\S+$");
    // index description
    private static final Pattern UPDATE_FORMAT = Pattern.compile("(?<targetIndex>\\d+)(?<description>.+)");

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
        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);
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
            return new DeadlineCommand(desTime[0].trim(), desTime[1].trim());
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareEvent(String args) {
        try {
            final String[] desTime = parseEvent(args);
            return new EventCommand(desTime[0].trim(), desTime[1].trim());
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareUpdate(String args) {
        try {
            final Matcher matcher = UPDATE_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new InvalidCommand(Messages.MESSAGE_INVALID_ARGUMENTS);
            }
            final int targetIndex = parseArgsAsDisplayedIndex(matcher.group("targetIndex"));
            return new UpdateCommand(targetIndex, matcher.group("description").trim());
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
        return parseArgsAsDescriptionDate(args, "/by");
    }

    private String[] parseEvent(String args) throws DukeException {
        return parseArgsAsDescriptionDate(args, "/at");
    }

    /**
     * Takes the visible index as input and translates into list index.
     *
     * @param args user input
     * @return index in the task list
     * @throws DukeException if input is not a number
     */
    private int parseArgsAsDisplayedIndex(String args) throws DukeException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(Messages.MESSAGE_TASK_NOT_SPECIFIED);
        }
        return Integer.parseInt(matcher.group());
    }

    private String[] parseArgsAsDescriptionDate(String args, String separator) throws DukeException {
        if (!args.contains(separator)) {
            throw new DukeException(String.format(Messages.MESSAGE_MISSING_SEPARATOR, separator));
        }

        String[] argsArray = args.trim().split(separator);
        if (argsArray.length == 0) {
            throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
        }
        if (argsArray.length == 1) {
            throw new DukeException(Messages.MESSAGE_EMPTY_DATE);
        }
        LocalDate date = Utils.parseDate(argsArray[1].trim());

        return new String[] { argsArray[0].trim(), date.toString() };
    }
}
