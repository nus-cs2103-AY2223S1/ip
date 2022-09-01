package henry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;
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
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<desc>.+) /by "
                                                                   + "(?<dateTime>\\d{2}-\\d{2}-\\d{4} "
                                                                   + "\\d{2}:\\d{2})");

    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<desc>.+) /at "
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
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(text.trim());
        if (!matcher.matches()) {
            throw new HenryException("UNKNOWN COMMAND!");
        }
        String command = matcher.group("command").trim();
        String args = matcher.group("args").trim();
        switch (command) {
        case EchoCommand.COMMAND_WORD:
            return new EchoCommand(args);
        case MarkCommand.COMMAND_WORD:
            if (isInputValid(args)) {
                throw new HenryException("ARGUMENT IS NOT A NUMBER!");
            }
            return new MarkCommand(Integer.parseInt(args));
        case UnmarkCommand.COMMAND_WORD:
            if (isInputValid(args)) {
                throw new HenryException("ARGUMENT IS NOT A NUMBER!");
            }
            return new UnmarkCommand(Integer.parseInt(args.trim()));
        case DeleteCommand.COMMAND_WORD:
            if (isInputValid(args)) {
                throw new HenryException("ARGUMENT IS NOT A NUMBER!");
            }
            return new DeleteCommand(Integer.parseInt(args.trim()));
        case FindCommand.COMMAND_WORD:
            return new FindCommand(parseFindArgs(args.trim()));
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadlineArguments(args);
        case EventCommand.COMMAND_WORD:
            return parseEventArguments(args);
        default:
            throw new HenryException("UNKNOWN COMMAND!");
        }
    }

    private String[] parseFindArgs(String args) {
        return args.split(" ");
    }

    private Command parseDeadlineArguments(String args) {
        Matcher matcher = DEADLINE_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new HenryException("ARGUMENT HAS THE WRONG FORMAT!");
        }
        String description = matcher.group("desc");
        String dateTime = matcher.group("dateTime");
        try {
            return new DeadlineCommand(description, LocalDateTime.parse(dateTime, formatter));
        } catch (NumberFormatException e) {
            throw new HenryException("DATE AND TIME NUMBERS ARE OUT OF RANGE!");
        }
    }

    private Command parseEventArguments(String args) {
        Matcher matcher = EVENT_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new HenryException("ARGUMENT HAS THE WRONG FORMAT!");
        }
        String description = matcher.group("desc");
        String dateTime = matcher.group("dateTime");
        try {
            return new EventCommand(description, LocalDateTime.parse(dateTime, formatter));
        } catch (NumberFormatException e) {
            throw new HenryException("DATE AND TIME NUMBERS ARE OUT OF RANGE!");
        }
    }

    private boolean isInputValid(String args) {
        return !args.matches("\\d+");
    }
}
