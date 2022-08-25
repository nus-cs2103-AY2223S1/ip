package jenny.util;

import jenny.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles making sense of user input.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Parser {
    private static final Pattern VALID_COMMAND = Pattern.compile(
            "(?<command>\\S+)(?<arguments>.*)?");

    public static AbstractCommand parse(String nextLine) {
        Matcher matcher = VALID_COMMAND.matcher(nextLine);
        if (!matcher.matches()) return new InvalidCommand();

        String command = matcher.group("command").strip();
        String arguments = matcher.group("arguments").strip();

        switch (command) {
        case ListCommand.COMMAND:
            return new ListCommand(arguments);
        case MarkCommand.COMMAND:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND:
            return new UnmarkCommand(arguments);
        case TodoCommand.COMMAND:
            return new TodoCommand(arguments);
        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(arguments);
        case EventCommand.COMMAND:
            return new EventCommand(arguments);
        case DeleteCommand.COMMAND:
            return new DeleteCommand(arguments);
        case SaveCommand.COMMAND:
            return new SaveCommand(arguments);
        case LoadCommand.COMMAND:
            return new LoadCommand(arguments);
        case ByeCommand.COMMAND:
            return new ByeCommand(arguments);
        default:
            return new InvalidCommand();
        }
    }
}
