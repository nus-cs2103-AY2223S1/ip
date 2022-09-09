package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.BaseCommand;
import duke.commands.ExitCommand;
import duke.commands.tasks.AddDeadlineCommand;
import duke.commands.tasks.AddEventCommand;
import duke.commands.tasks.AddTodoCommand;
import duke.commands.tasks.DeleteTaskCommand;
import duke.commands.tasks.FindTaskCommand;
import duke.commands.tasks.ListTasksCommand;
import duke.commands.tasks.MarkTaskCommand;
import duke.commands.tasks.SortTasksCommand;
import duke.commands.tasks.UnmarkTaskCommand;
import duke.exceptions.NoCommandException;
import duke.exceptions.ParseException;

/**
 * Parser Class in charge of parsing the users' command.
 */
public class Parser {

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * The parse function takes a string as input and parses it into commands.
     * The parse function is responsible for parsing the user's input into
     * commands that can be executed by the program. It also handles any errors
     * that may occur during parsing, such as missing arguments or invalid date/time
     * formats.
     *
     * @param userInput
     *            Get the user input
     * @throws NoCommandException
     * @throws ParseException
     */
    public BaseCommand parse(String userInput) throws NoCommandException, ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (userInput.trim().isEmpty()) {
            throw new NoCommandException();
        }
        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddTodoCommand.COMMAND_WORD:
            return new AddTodoParser().parse(arguments);
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineParser().parse(arguments);
        case AddEventCommand.COMMAND_WORD:
            return new AddEventParser().parse(arguments);
        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskParser().parse(arguments);
        case ListTasksCommand.COMMAND_WORD:
            return new ListTaskParser().parse(arguments);
        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskParser().parse(arguments);
        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskParser().parse(arguments);
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskParser().parse(arguments);
        case SortTasksCommand.COMMAND_WORD:
            return new SortTasksParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
