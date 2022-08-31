package duke.parser;

import duke.command.*;
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
     * @throws DukeException The exception thrown when an action is unauthorized by Duke.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
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
        } else if (command.startsWith("unmark")) {
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
        } else if (command.startsWith("delete")) {
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
                return new DeleteCommand(itemNumber);
            } catch (NumberFormatException nfe) {
                throw new DukeNumberFormatException();
            }
        } else if (command.startsWith("todo")) {
            String[] splitCommand = command.split("\\s+", 2);
            if (!splitCommand[0].equals("todo")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("todo");
            }
            String desc = splitCommand[1];
            return new TodoCommand(desc);
        } else if (command.startsWith("deadline")) {
            String[] splitCommand = command.split("\\s+", 2);
            if (!splitCommand[0].equals("deadline")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("deadline");
            }
            String desc = splitCommand[1];
            return new DeadlineCommand(desc);
        } else if (command.startsWith("event")) {
            String[] splitCommand = command.split("\\s+", 2);
            if (!splitCommand[0].equals("event")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("event");
            }
            String desc = splitCommand[1];
            return new EventCommand(desc);
        } else if (command.startsWith("find")) {
            String[] splitCommand = command.split("\\s+", 2);
            if (!splitCommand[0].equals("find")) {
                throw new DukeInvalidCommandException(splitCommand[0]);
            }
            if (splitCommand.length < 2) {
                throw new DukeEmptyDescriptionException("find");
            }
            String keyword = splitCommand[1];
            return new FindCommand(keyword);
        } else {
            throw new DukeInvalidCommandException(command);
        }
    }
}
