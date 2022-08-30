package jenny.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jenny.commands.ByeCommand;
import jenny.commands.Command;
import jenny.commands.DeadlineCommand;
import jenny.commands.DeleteCommand;
import jenny.commands.EventCommand;
import jenny.commands.InvalidCommand;
import jenny.commands.ListCommand;
import jenny.commands.MarkCommand;
import jenny.commands.TodoCommand;
import jenny.commands.UnmarkCommand;
import jenny.exceptions.JennyException;


/**
 * Handles making sense of user input.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Parser {
    private static final String MESSAGE_SCOPE = Parser.class.getSimpleName();
    private static final Pattern VALID_COMMAND = Pattern.compile(
        "(?<command>\\S+)(?<arguments>.*)?");

    /**
     * Parse the provided string into valid commands and arguments.
     *
     * @param nextLine a string command to parse.
     * @return a {@link Command} matching the parsed string.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public static Command parse(String nextLine) throws JennyException {
        Matcher matcher = VALID_COMMAND.matcher(nextLine);
        if (!matcher.matches()) {
            return new InvalidCommand();
        }

        try {
            String command = matcher.group("command").strip();
            String arguments = matcher.group("arguments").strip();

            switch (command) {
            case ListCommand.COMMAND:
                return new ListCommand();
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
            case ByeCommand.COMMAND:
                return new ByeCommand(arguments);
            default:
                return new InvalidCommand();
            }
        } catch (IllegalStateException | IllegalArgumentException | JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
