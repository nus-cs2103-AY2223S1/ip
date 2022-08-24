package duke.parser;

import duke.dukeexception.DukeException;

import duke.command.OtherCommand;
import duke.command.MarkingCommand;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.split(" ")[0].equals("event")
                || fullCommand.split(" ")[0].equals("deadline")
                ||fullCommand.split(" ")[0].equals("todo")) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("mark")
                ||fullCommand.split(" ")[0].equals("unmark")) {
            return new MarkingCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("Get")||
                fullCommand.split(" ")[0].equals("list")) {
            return new OtherCommand(fullCommand);
        } else {
            throw new DukeException("Sorry, I don't know your meanings.");
        }
    }
}
