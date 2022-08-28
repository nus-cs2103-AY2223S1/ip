package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Make sense of the user's input by parsing.
     * @param userInput The command user input in Duke command line.
     * @return A Command object representing user command.
     * @throws DukeException when user command is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] strings = userInput.split(" ");

        switch (strings[0]) {

        case DeleteCommand.COMMAND_WORD:
            try {
                if (strings.length > 2) {
                    throw new DukeException("Invalid Input.");
                }
                return new DeleteCommand(Integer.parseInt(strings[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter an index to delete.");
            }

        case TodoCommand.COMMAND_WORD:
            try {
                userInput = userInput.substring(5);
                return new TodoCommand(userInput);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a task todo.");
            }

        case EventCommand.COMMAND_WORD:
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

        case DeadlineCommand.COMMAND_WORD:
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

        case MarkCommand.COMMAND_WORD:
            try {
                if (strings.length > 2) {
                    throw new DukeException("Invalid Input.");
                }
                return new MarkCommand(Integer.parseInt(strings[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter an index to mark.");
            }

        case UnmarkCommand.COMMAND_WORD:
            try {
                if (strings.length > 2) {
                    throw new DukeException("Invalid Input.");
                }
                return new UnmarkCommand(Integer.parseInt(strings[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter an index to unmark.");
            }

        case DateCommand.COMMAND_WORD:
            try {
                if (strings.length > 2) {
                    throw new DukeException("Invalid Input.");
                }
                return new DateCommand(strings[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter a date.");
            }

        case ListCommand.COMMAND_WORD:
            if (strings.length > 1) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            return new ListCommand();

        case ByeCommand.COMMAND_WORD:
            if (strings.length > 1) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            return new ByeCommand();

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");

        }
    }

}

