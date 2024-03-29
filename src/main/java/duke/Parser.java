package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.PriorityCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;


/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Makes sense of the user's input by parsing.
     *
     * @param userInput The command user input in Duke command line.
     * @return A Command object representing user command.
     * @throws DukeException when user command is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        assert userInput != null : "Parser::parse has null input";
        String[] strings = userInput.split(" ");

        switch (strings[0]) {

        case DeleteCommand.COMMAND_WORD:
            return parseDelete(strings);

        case TodoCommand.COMMAND_WORD:
            return parseTodo(userInput);

        case EventCommand.COMMAND_WORD:
            return parseEvent(userInput);

        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(userInput);

        case MarkCommand.COMMAND_WORD:
            return parseMark(strings);

        case UnmarkCommand.COMMAND_WORD:
            return parseUnmark(strings);

        case DateCommand.COMMAND_WORD:
            return parseDate(strings);

        case ListCommand.COMMAND_WORD:
            checkNoExtraInput(strings);
            return new ListCommand();

        case ByeCommand.COMMAND_WORD:
            checkNoExtraInput(strings);
            return new ByeCommand();

        case FindCommand.COMMAND_WORD:
            return parseFind(strings);

        case PriorityCommand.COMMAND_WORD:
            return parsePriority(strings);

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");

        }
    }


    private static void checkNoExtraInput(String[] strings) throws DukeException {
        if (strings.length > 1) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static DeleteCommand parseDelete(String[] strings) throws DukeException {
        try {
            if (strings.length > 2) {
                throw new DukeException("Invalid Input.");
            }
            return new DeleteCommand(Integer.parseInt(strings[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an index to delete.");
        }
    }

    private static MarkCommand parseMark(String[] strings) throws DukeException {
        try {
            if (strings.length > 2) {
                throw new DukeException("Invalid Input.");
            }
            return new MarkCommand(Integer.parseInt(strings[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an index to mark.");
        }
    }

    private static UnmarkCommand parseUnmark(String[] strings) throws DukeException {
        try {
            if (strings.length > 2) {
                throw new DukeException("Invalid Input.");
            }
            return new UnmarkCommand(Integer.parseInt(strings[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an index to unmark.");
        }

    }

    private static PriorityCommand parsePriority(String[] strings) throws DukeException {
        try {
            if (strings.length > 3) {
                throw new DukeException("Invalid Input.");
            }
            return new PriorityCommand(Integer.parseInt(strings[1]), strings[2].toUpperCase());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an index to unmark.");
        }

    }

    private static FindCommand parseFind(String[] strings) throws DukeException {
        try {
            if (strings.length > 2) {
                throw new DukeException("Invalid Input.");
            }
            return new FindCommand(strings[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a keyword.");
        }
    }

    private static DateCommand parseDate(String[] strings) throws DukeException {
        try {
            if (strings.length > 2) {
                throw new DukeException("Invalid Input.");
            }
            return new DateCommand(strings[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a date.");
        }
    }

    private static TodoCommand parseTodo(String userInput) throws DukeException {
        try {
            userInput = userInput.substring(5);
            return new TodoCommand(userInput);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task todo.");
        }
    }

    private static EventCommand parseEvent(String userInput) throws DukeException {
        try {
            userInput = userInput.substring(6);
            String[] stringsEvent = userInput.split(" /at ");
            if (stringsEvent.length > 2) {
                throw new DukeException("Please only enter one event.");
            }
            return new EventCommand(stringsEvent);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please enter an event.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use /at to specify event time.");
        }
    }

    private static DeadlineCommand parseDeadline(String userInput) throws DukeException {
        try {
            userInput = userInput.substring(9);
            String[] stringsEvent = userInput.split(" /by ");
            if (stringsEvent.length > 2) {
                throw new DukeException("Please only enter one deadline.");
            }
            return new DeadlineCommand(stringsEvent);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use /by to specify event time.");
        }
    }
}

