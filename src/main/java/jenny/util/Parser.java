package jenny.util;

import jenny.commands.*;
import jenny.exceptions.JennyException;

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
        boolean isMatch = matcher.matches();
        if (!isMatch) {
            assert !isMatch;
            return new InvalidCommand();
        }
        assert isMatch;
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
            case FindCommand.COMMAND:
                return new FindCommand(arguments);
            default:
                return new InvalidCommand();
            }
        } catch (IllegalStateException | IllegalArgumentException | JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
