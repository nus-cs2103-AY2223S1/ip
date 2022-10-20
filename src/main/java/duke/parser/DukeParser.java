package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;

public class DukeParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String MESSAGE_INVALID_COMMAND = "Invalid command";

    public Command parseCommand(String userInput) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new DukeException(MESSAGE_INVALID_COMMAND);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case AddCommand.COMMAND_WORD_TODO:
        case AddCommand.COMMAND_WORD_TODO_SHORT:
            return new TodoCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD_EVENT:
        case AddCommand.COMMAND_WORD_EVENT_SHORT:
            return new EventCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD_DEADLINE:
        case AddCommand.COMMAND_WORD_DEADLINE_SHORT:
            return new DeadlineCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD_COMPLETED:
            return new MarkCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD_NOT_COMPLETED:
            return new UnmarkCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments.trim());

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        default:
            throw new DukeException(MESSAGE_INVALID_COMMAND);
        }
    }
}
