package john.parser;

import java.util.regex.Pattern;

import john.commands.ByeCommand;
import john.commands.Command;
import john.commands.DeadlineCommand;
import john.commands.DeleteCommand;
import john.commands.EventCommand;
import john.commands.FindCommand;
import john.commands.IncorrectCommand;
import john.commands.ListCommand;
import john.commands.MarkCommand;
import john.commands.TodoCommand;
import john.commands.UnmarkCommand;


/**
 * Represents a parser to parse user input.
 */
public class Parser {
    private static final String REGEX_DEADLINE =
            "[^ ](.*) /by ([1-9]|[0-2][0-9]|(3)[0-1])/(((0)?[0-9])|((1)[0-2]))/[0-9]{4}"
                    + "(( ([01][0-9]|2[0-3])([0-5][0-9]))|)";
    private static final String REGEX_EVENT =
            "[^ ](.*) /at ([1-9]|[0-2][0-9]|(3)[0-1])/(((0)?[0-9])|((1)[0-2]))/[0-9]{4}"
                    + "(( ([01][0-9]|2[0-3])([0-5][0-9]))|)";
    private static final String REGEX_LIST =
            "(([1-9]|[0-2][0-9]|(3)[0-1])/(((0)?[0-9])|((1)[0-2]))/[0-9]{4}|)";

    /**
     * Parses the user input, and returns the corresponding command.
     *
     * @param input The user input.
     * @return Command representing the user input.
     */
    public Command parseCommand(String input) {
        String mainCommand = input.split(" ", 2)[0].toLowerCase();
        String params = parseParams(input);

        switch (mainCommand) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(params);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(params);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(params);

        case ListCommand.COMMAND_WORD:
            return prepareList(params);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(params);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(params);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(params);

        case FindCommand.COMMAND_WORD:
            return prepareFind(params);

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        default:
            return new IncorrectCommand(input);
        }
    }
    private String parseParams(String input) {
        if (input.split(" ", 2).length == 1) {
            return "";
        }
        return input.split(" ", 2)[1];
    }

    private Command prepareTodo(String params) {
        if (!params.matches("[^ ](.*)")) {
            return new IncorrectCommand(TodoCommand.COMMAND_WORD, TodoCommand.FORMAT);
        }
        return new TodoCommand(params);
    }

    private Command prepareDeadline(String params) {
        if (!Pattern.matches(REGEX_DEADLINE, params.toLowerCase())) {
            return new IncorrectCommand(DeadlineCommand.COMMAND_WORD, DeadlineCommand.FORMAT);
        }
        return new DeadlineCommand(params);
    }

    private Command prepareEvent(String params) {
        if (!Pattern.matches(REGEX_EVENT, params.toLowerCase())) {
            return new IncorrectCommand(EventCommand.COMMAND_WORD, EventCommand.FORMAT);
        }
        return new EventCommand(params);
    }

    private Command prepareList(String params) {
        if (!Pattern.matches(REGEX_LIST, params.toLowerCase())) {
            return new IncorrectCommand(ListCommand.COMMAND_WORD, ListCommand.FORMAT);
        }
        return new ListCommand(params);
    }

    private Command prepareMark(String params) {
        if (!params.matches("([0-9]+)")) {
            return new IncorrectCommand(MarkCommand.COMMAND_WORD, MarkCommand.FORMAT);
        }
        return new MarkCommand(params);
    }

    private Command prepareUnmark(String params) {
        if (!params.matches("([0-9]+)")) {
            return new IncorrectCommand(UnmarkCommand.COMMAND_WORD, UnmarkCommand.FORMAT);
        }
        return new UnmarkCommand(params);
    }

    private Command prepareDelete(String params) {
        if (!params.matches("([0-9]+)")) {
            return new IncorrectCommand(DeleteCommand.COMMAND_WORD, DeleteCommand.FORMAT);
        }
        return new DeleteCommand(params);
    }

    private Command prepareFind(String params) {
        if (!params.matches("[^ ](.*)")) {
            return new IncorrectCommand(FindCommand.COMMAND_WORD, FindCommand.FORMAT);
        }
        return new FindCommand(params);
    }
}
