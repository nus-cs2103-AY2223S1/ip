package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;

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
            return new AddCommand("todo", suffix);
        }
        case "deadline": {
            return new AddCommand("deadline", suffix);
        }
        case "event": {
            return new AddCommand("event", suffix);
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

    private static Command parseSingleWordCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("sort")){
            return new SortCommand();
        } else {
            throw new DukeException("Sorry, I don't understand that command :(");
        }
    }
}
