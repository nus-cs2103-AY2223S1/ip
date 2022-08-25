package duke;

import duke.commands.*;

public class Parser {

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
                userInput = userInput.substring(userInput.indexOf(" ") + 1);
                return new TodoCommand(userInput);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a task todo.");
            }

        case EventCommand.COMMAND_WORD:
            try {
                userInput = userInput.substring(userInput.indexOf(" ") + 1);
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
                userInput = userInput.substring(userInput.indexOf(" ") + 1);
                String[] stringsEvent = userInput.split(" /by ");
                if (stringsEvent.length > 2) {
                    throw new DukeException("Please only enter one deadline.");
                }
                return new DeadlineCommand(stringsEvent);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a deadline.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use /at to specify event time.");
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

