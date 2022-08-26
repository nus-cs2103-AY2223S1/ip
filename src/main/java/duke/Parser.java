package duke;

import duke.command.*;

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
        String command = spacedArr[0];
        
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        }
        
        if (!command.equals("mark") && !command.equals("unmark") && !command.equals("todo") && !command.equals("deadline") && !command.equals("event") && !command.equals("delete") && !command.equals("find")) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (spacedArr.length == 1) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }
        
        String suffix = spacedArr[1];

        switch (command) {
        case "mark": {
            int pos = Integer.parseInt(suffix) - 1;
            return new MarkCommand(true, pos);
        }
        case "unmark": {
            int pos = Integer.parseInt(suffix) - 1;
            return new MarkCommand(false, pos);
        }
        case "todo":
            return new AddCommand("todo", suffix);
        case "deadline":
            return new AddCommand("deadline", suffix);
        case "event":
            return new AddCommand("event", suffix); 
        case "find": 
            return new FindCommand(suffix);
        default: {
            int pos = Integer.parseInt(suffix) - 1;
            return new DeleteCommand(pos);
        }
        }
    }
}
