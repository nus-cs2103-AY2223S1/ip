package henry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;
import command.Commands;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EchoCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.HenryException;

/**
 * The parser class is responsible for parsing the user input and
 * creating the appropriate command.
 */
public class Parser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S*)(?<args>.*)");
    private static final Pattern DATE_FORMAT = Pattern.compile("(?<desc>.+) /(at|by) "
                                                               + "(?<dateTime>\\d{2}-\\d{2}-\\d{4} "
                                                               + "\\d{2}:\\d{2})");
    private static final String DATE_FORMATTER = "dd-MM-yyyy HH:mm";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    /**
     * Parses a command from a string. There are two types of commands:
     * single-argument commands, and double-argument commands. The function
     * first checks if the command matches the appropriate pattern. If it does,
     * then the corresponding command is returned. If not, then an exception is thrown.
     *
     * @param text the string to be parsed.
     * @return the command corresponding to the string, or an exception if the string is malformed.
     */
    public Command parseCommand(String text) {
        assert text != null : "Text is null!";

        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(text.trim());
        if (!matcher.matches()) {
            throw new HenryException("UNKNOWN COMMAND!");
        }

        String command = matcher.group("command").trim();
        String args = matcher.group("args").trim();

        switch (command) {
        case EchoCommand.COMMAND_WORD:
            return new EchoCommand(args);
        case FindCommand.COMMAND_WORD:
            if (!isFindInputValid(args)) {
                throw new HenryException("PLEASE PREFIX YOUR SEARCH TERMS WITH \"--\"!");
            }
            return new FindCommand(parseFindArguments(args));
        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
        case UnmarkCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
            return handleTaskEditCommand(command, args);

        case TodoCommand.COMMAND_WORD:
        case DeadlineCommand.COMMAND_WORD:
        case EventCommand.COMMAND_WORD:
            return handleTaskCommand(command, args);
        default:
            throw new HenryException("UNKNOWN COMMAND!");
        }
    }

    private Command handleTaskEditCommand(String command, String args) {
        if (!isInputValid(args)) {
            throw new HenryException("ARGUMENT IS NOT A NUMBER!");
        } else if (Integer.parseInt(args) < 1) {
            throw new HenryException("ARGUMENT MUST BE A POSITIVE INTEGER!");
        }
        switch (command) {
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(Integer.parseInt(args));
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(Integer.parseInt(args.trim()));
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(Integer.parseInt(args.trim()));
        default:
            throw new HenryException("COMMAND IS MALFORMED!");
        }
    }

    private Command handleTaskCommand(String command, String args) {
        switch (command) {
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDatedCommand(Commands.DEADLINE, args);
        case EventCommand.COMMAND_WORD:
            return parseDatedCommand(Commands.EVENT, args);
        default:
            throw new HenryException("UNKNOWN COMMAND!");
        }
    }

    private String[] parseFindArguments(String args) {
        assert args != null : "Arguments are null!";
        return args.split("--");
    }

    private Command parseDatedCommand(Commands type, String args) {
        assert args != null : "Arguments are null!";

        Matcher matcher = DATE_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new HenryException("ARGUMENT HAS THE WRONG FORMAT!");
        }

        String description = matcher.group("desc");
        String dateTime = matcher.group("dateTime");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime parsed = LocalDateTime.parse(dateTime, formatter);
        if (parsed.isBefore(now)) {
            throw new HenryException("DATE IS IN THE PAST!");
        }

        try {
            if (type == Commands.DEADLINE) {
                return new DeadlineCommand(description, parsed);
            } else if (type == Commands.EVENT) {
                return new EventCommand(description, parsed);
            } else {
                throw new HenryException("UNKNOWN COMMAND!");
            }
        } catch (NumberFormatException e) {
            throw new HenryException("DATE AND TIME NUMBERS ARE OUT OF RANGE!");
        }
    }

    private boolean isFindInputValid(String args) {
        assert args != null : "Arguments are null!";
        return args.matches("(--\\w*\\s*)+");
    }

    private boolean isInputValid(String args) {
        assert args != null : "Arguments are null!";
        return args.matches("\\d+");
    }
}
