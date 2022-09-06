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
import command.TentativeCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.HenryException;
import util.TextUtils;

/**
 * The parser class is responsible for parsing the user input and
 * creating the appropriate command.
 */
public class Parser {

    // REGEX PATTERNS
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S*)(?<args>.*)");
    private static final Pattern DATE_FORMAT = Pattern.compile("(?<desc>.+) /(at|by) "
                                                               + "(?<dateTime>\\d{2}-\\d{2}-\\d{4} "
                                                               + "\\d{2}:\\d{2})");
    private static final Pattern TENTATIVE_NEW_DATE_FORMAT =
        Pattern.compile("(?<index>\\d+) (?<dateTime>\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2})");
    private static final Pattern TENTATIVE_CONFIRM_DATE_FORMAT =
        Pattern.compile("(?<index>\\d+) (--confirm) (?<chosenDateIndex>\\d+)");

    // OTHER STATIC VARIABLES
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

        String[] cleanedText = performTextGrouping(text);
        String command = cleanedText[0];
        String args = cleanedText[1];

        switch (command) {
        case EchoCommand.COMMAND_WORD:
            return new EchoCommand(args);
        case FindCommand.COMMAND_WORD:
            return handleFindCommand(args);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case TentativeCommand.COMMAND_WORD:
            return handleTentativeCommand(args);

        case MarkCommand.COMMAND_WORD:
        case UnmarkCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
            return handleTaskEditCommand(command, args);

        case TodoCommand.COMMAND_WORD:
        case DeadlineCommand.COMMAND_WORD:
        case EventCommand.COMMAND_WORD:
            return handleTaskCommand(command, args);
        default:
            throw new HenryException(TextUtils.UNKNOWN_COMMAND_ERROR);
        }
    }

    private String[] performTextGrouping(String text) {
        assert text != null : "Text is null!";
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(text.trim());
        if (!matcher.matches()) {
            throw new HenryException(TextUtils.EMPTY_INPUT_ERROR);
        }
        String command = matcher.group("command").trim();
        String args = matcher.group("args").trim();
        return new String[]{command, args};
    }

    private Command handleFindCommand(String args) {
        if (!isFindInputValid(args)) {
            throw new HenryException(TextUtils.FIND_COMMAND_ERROR);
        }
        return new FindCommand(parseFindArguments(args));
    }

    private Command handleTentativeCommand(String args) {
        Matcher newDateMatcher = TENTATIVE_NEW_DATE_FORMAT.matcher(args.trim());
        Matcher confirmDateMatcher = TENTATIVE_CONFIRM_DATE_FORMAT.matcher(args.trim());

        if (!newDateMatcher.matches() && !confirmDateMatcher.matches()) {
            throw new HenryException(TextUtils.TENTATIVE_COMMAND_ERROR);
        }

        if (newDateMatcher.matches()) {
            int index = Integer.parseInt(newDateMatcher.group("index"));
            String dateTime = newDateMatcher.group("dateTime");

            try {
                LocalDateTime parsed = LocalDateTime.parse(dateTime, formatter);
                if (!isDateValid(parsed)) {
                    throw new HenryException(TextUtils.DATE_IN_PAST_ERROR);
                }
                return new TentativeCommand(index, parsed);
            } catch (NumberFormatException e) {
                throw new HenryException(TextUtils.DATE_FORMAT_ERROR);
            }
        } else {
            int index = Integer.parseInt(confirmDateMatcher.group("index"));
            int chosenDateIndex = Integer.parseInt(confirmDateMatcher.group("chosenDateIndex"));

            return new TentativeCommand(index, chosenDateIndex);
        }
    }

    private Command handleTaskEditCommand(String command, String args) {
        if (!isInputNumeric(args)) {
            throw new HenryException(TextUtils.NON_NUMBER_ERROR);
        }
        if (Integer.parseInt(args) < 1) {
            throw new HenryException(TextUtils.MUST_BE_POSITIVE_ERROR);
        }

        switch (command) {
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(Integer.parseInt(args));
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(Integer.parseInt(args.trim()));
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(Integer.parseInt(args.trim()));
        default:
            throw new HenryException(TextUtils.MALFORMED_COMMAND_ERROR);
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
            throw new HenryException(TextUtils.UNKNOWN_COMMAND_ERROR);
        }
    }

    private String[] parseFindArguments(String args) {
        assert args != null : "Arguments are null!";
        return args.split("--");
    }

    private String[] performArgumentGrouping(String args) {
        assert args != null : "Arguments are null!";

        Matcher matcher = DATE_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new HenryException(TextUtils.ARGUMENT_SYNTAX_ERROR);
        }

        String description = matcher.group("desc");
        String dateTime = matcher.group("dateTime");

        return new String[]{description, dateTime};
    }

    private Command parseDatedCommand(Commands type, String args) {
        String[] cleanedArgs = performArgumentGrouping(args);
        String description = cleanedArgs[0];
        String dateTime = cleanedArgs[1];
        
        try {
            LocalDateTime parsed = LocalDateTime.parse(dateTime, formatter);
            if (!isDateValid(parsed)) {
                throw new HenryException(TextUtils.DATE_IN_PAST_ERROR);
            }

            switch (type) {
            case DEADLINE:
                return new DeadlineCommand(description, parsed);
            case EVENT:
                return new EventCommand(description, parsed);
            default:
                throw new HenryException(TextUtils.UNKNOWN_COMMAND_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new HenryException(TextUtils.DATE_FORMAT_ERROR);
        }
    }

    private boolean isFindInputValid(String args) {
        assert args != null : "Arguments are null!";
        return args.matches("(--\\w*\\s*)+");
    }

    private boolean isInputNumeric(String args) {
        assert args != null : "Arguments are null!";
        return args.matches("\\d+");
    }

    private boolean isDateValid(LocalDateTime dateTime) {
        return !dateTime.isBefore(LocalDateTime.now());
    }
}
