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
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

/**
 * Represents a parser in the Duke application.
 */
public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s?(?<arguments>.*)");
    private static final DukeException UNKNOWN_COMMAND = new DukeException("Unknown command!");

    /**
     * Parses a user input into a command.
     *
     * @param userInput      Input that the user has provided.
     * @return               Command parsed from the user input.
     * @throws DukeException Exception that occurred during the parsing of the command.
     */
    public Command parseCommand(String userInput) throws DukeException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        String commandWord;
        String arguments;
        if (matcher.matches()) {
            commandWord = matcher.group("commandWord");
            arguments = matcher.group("arguments");
        } else {
            throw Parser.UNKNOWN_COMMAND;
        }

        Command command;
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case MarkCommand.COMMAND_WORD: {
            command = new MarkCommand(arguments);
            break;
        }
        case UnmarkCommand.COMMAND_WORD: {
            command = new UnmarkCommand(arguments);
            break;
        }
        case DeleteCommand.COMMAND_WORD: {
            command = new DeleteCommand(arguments);
            break;
        }
        case TodoCommand.COMMAND_WORD: {
            command = new TodoCommand(arguments);
            break;
        }
        case DeadlineCommand.COMMAND_WORD: {
            command = new DeadlineCommand(arguments);
            break;
        }
        case EventCommand.COMMAND_WORD: {
            command = new EventCommand(arguments);
            break;
        }
        case FindCommand.COMMAND_WORD: {
            command = new FindCommand(arguments);
            break;
        }
        default:
            throw Parser.UNKNOWN_COMMAND;
        }

        return command;
    }
}
