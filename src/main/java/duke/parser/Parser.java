package duke.parser;

import duke.Duke;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RemindCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeNumberFormatException;

/**
 * Parser is a Parser that parses the user input.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class Parser {

    /**
     * Returns the proper Command indicated by the user input.
     *
     * @param command String representation of the command.
     * @return The command by user.
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("remind")) {
            return new RemindCommand();
        } else if (command.startsWith("mark")) {
            return parseMark(command);
        } else if (command.startsWith("unmark")) {
            return parseUnmark(command);
        } else if (command.startsWith("delete")) {
            return parseDelete(command);
        } else if (command.startsWith("todo")) {
            return parseTodo(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadline(command);
        } else if (command.startsWith("event")) {
            return parseEvent(command);
        } else if (command.startsWith("find")) {
            return parseFind(command);
        } else {
            throw new DukeInvalidCommandException(command);
        }
    }

    /**
     * Parses the mark command.
     *
     * @param command String representation of the mark command.
     * @return The mark command.
     * @throws DukeException
     */
    public static Command parseMark(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("mark")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("mark");
        }
        try {
            String markItem = splitCommand[1];
            int itemNumber = Integer.parseInt(markItem);
            return new MarkCommand(itemNumber - 1);
        } catch (NumberFormatException nfe) {
            throw new DukeNumberFormatException();
        }
    }

    /**
     * Parses the unmark command.
     *
     * @param command String representation of the unmark command.
     * @return The unmark command.
     * @throws DukeException
     */
    public static Command parseUnmark(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("unmark")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("unmark");
        }
        try {
            String unmarkItem = splitCommand[1];
            int itemNumber = Integer.parseInt(unmarkItem);
            return new UnmarkCommand(itemNumber - 1);
        } catch (NumberFormatException nfe) {
            throw new DukeNumberFormatException();
        }
    }

    /**
     * Parses the delete command.
     *
     * @param command String representation of the delete command.
     * @return The delete command.
     * @throws DukeException
     */
    public static Command parseDelete(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("delete")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("delete");
        }
        try {
            String deleteItem = splitCommand[1];
            int itemNumber = Integer.parseInt(deleteItem);
            return new DeleteCommand(itemNumber - 1);
        } catch (NumberFormatException nfe) {
            throw new DukeNumberFormatException();
        }
    }

    /**
     * Parses the todo command.
     *
     * @param command String representation of the todo command.
     * @return The todo command.
     * @throws DukeException
     */
    public static Command parseTodo(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("todo")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("todo");
        }
        String desc = splitCommand[1];
        return new TodoCommand(desc);
    }

    /**
     * Parses the deadline command.
     *
     * @param command String representation of the deadline command.
     * @return The deadline command.
     * @throws DukeException
     */
    public static Command parseDeadline(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("deadline")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("deadline");
        }
        String desc = splitCommand[1];
        return new DeadlineCommand(desc);
    }

    /**
     * Parses the event command.
     *
     * @param command String representation of the event command.
     * @return The event command.
     * @throws DukeException
     */
    public static Command parseEvent(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("event")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("event");
        }
        String desc = splitCommand[1];
        return new EventCommand(desc);
    }

    /**
     * Parses the find command.
     *
     * @param command String representation of the find command.
     * @return The find command.
     * @throws DukeException
     */
    public static Command parseFind(String command) throws DukeException {
        String[] splitCommand = command.split("\\s+", 2);
        if (!splitCommand[0].equals("find")) {
            throw new DukeInvalidCommandException(splitCommand[0]);
        }
        if (splitCommand.length < 2) {
            throw new DukeEmptyDescriptionException("find");
        }
        String keyword = splitCommand[1];
        return new FindCommand(keyword);
    }
}
