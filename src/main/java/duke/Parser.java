package duke;

import duke.DukeException;
import duke.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] spacedArr = fullCommand.split(" ", 2);
        String command = spacedArr[0];
        
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        }
        
        if (!command.equals("mark") && !command.equals("unmark") && !command.equals("todo") && !command.equals("deadline") && !command.equals("event") && !command.equals("delete")) {
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
        default: {
            int pos = Integer.parseInt(suffix) - 1;
            return new DeleteCommand(pos);
        }
        }
    }
}
