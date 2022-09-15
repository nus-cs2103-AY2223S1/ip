package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;

/**
 * Represents the bridge between a user's raw input and its internal representation in the program.
 * Responsible for interpreting the user's command and converting them into a program entity.
 */
public class Parser {
    /**
     * Interprets the user's input as a corresponding command.
     *
     * @param fullCommand The user's entire input.
     * @return Command object that can be executed.
     * @throws DukeException If the command cannot be recognised or data file cannot be accessed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] spacedArr = fullCommand.split(" ", 2);
        assert spacedArr.length >= 1 : "User input should have more than 1 word";
        if (spacedArr.length == 1) {
            return parseSingleWordCommand(spacedArr[0]);
        } else {
            return parseMultipleWordCommand(spacedArr[0], spacedArr[1]);
        }
    }

    /**
     * Interprets multiple word commands and converts them into a Command.
     *
     * @param command The command type.
     * @param suffix Additional information about the command.
     * @return Command represented by the inputs.
     * @throws DukeException If command type is not recognised.
     */
    private static Command parseMultipleWordCommand(String command, String suffix) throws DukeException {
        switch (command) {
        case "mark": {
            int pos = Integer.parseInt(suffix) - 1;
            return new MarkCommand(true, pos);
        }
        case "unmark": {
            int pos = Integer.parseInt(suffix) - 1;
            return new MarkCommand(false, pos);
        }
        case "todo": {
            return new AddToDoCommand(suffix);
        }
        case "deadline": {
            return new AddDeadlineCommand(suffix);
        }
        case "event": {
            return new AddEventCommand(suffix);
        }
        case "find": {
            return new FindCommand(suffix);
        }
        case "delete": {
            int pos = Integer.parseInt(suffix) - 1;
            return new DeleteCommand(pos);
        }
        default: {
            throw new DukeException("Sorry, I don't understand that command :(");
        }
        }
    }

    /**
     * Interprets single word commands and converts them into a Command.
     *
     * @param command The command type.
     * @return Command represented by the input.
     * @throws DukeException If command type is not recognised.
     */
    private static Command parseSingleWordCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("sort")) {
            return new SortCommand();
        } else {
            throw new DukeException("Sorry, I don't understand that command :(");
        }
    }
}
