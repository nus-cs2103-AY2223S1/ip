package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UndoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

/**
 * Represents a parser in the Duke application.
 */
public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s?(?<arguments>.*)");

    /**
     * Represents the command along with its arguments, before parsing.
     */
    private static class CommandArguments {
        public final String commandWord;
        public final String arguments;

        public CommandArguments(String commandWord, String arguments) {
            this.commandWord = commandWord;
            this.arguments = arguments;
        }
    }

    private CommandArguments getArguments(String userInput) throws DukeException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new UnknownCommandException();
        }
        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");
        return new CommandArguments(commandWord, arguments);
    }

    private Command createCommand(CommandArguments commandArguments) throws DukeException {
        String arguments = commandArguments.arguments;

        switch (commandArguments.commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(arguments);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses a user input into a command.
     *
     * @param userInput      Input that the user has provided.
     * @return               Command parsed from the user input.
     * @throws DukeException Exception that occurred during the parsing of the command.
     */
    public Command parseCommand(String userInput) throws DukeException {
        CommandArguments commandArguments = getArguments(userInput);
        return createCommand(commandArguments);
    }
}
