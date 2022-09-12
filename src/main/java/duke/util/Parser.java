package duke.util;

import duke.DukeException;
import duke.util.command.Command;
import duke.util.command.CommandBye;
import duke.util.command.CommandDeadline;
import duke.util.command.CommandDelete;
import duke.util.command.CommandEvent;
import duke.util.command.CommandFind;
import duke.util.command.CommandList;
import duke.util.command.CommandMark;
import duke.util.command.CommandTodo;
import duke.util.command.CommandUnmark;
import duke.util.command.CommandUpdateDescription;

/**
 * Class to deal with making sense of the user's command.
 *
 * @author Kavan
 */
public class Parser {
    /**
     * Handles the user's input accordingly.
     *
     * @param command User input.
     * @throws DukeException If command is not known.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new CommandBye(command);
        } else if (command.equals("list")) {
            return new CommandList(command);
        } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("mark")) {
            return new CommandMark(command);
        } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("unmark")) {
            return new CommandUnmark(command);
        } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("delete")) {
            return new CommandDelete(command);
        } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("find")) {
            return new CommandFind(command);
        } else if (command.split(" ").length > 1 && command.split(" ")[0].equals("todo")) {
            return new CommandTodo(command);
        } else if (command.split(" ").length > 3 && command.split(" ")[0].equals("deadline")) {
            return new CommandDeadline(command);
        } else if (command.split(" ").length > 3 && command.split(" ")[0].equals("event")) {
            return new CommandEvent(command);
        } else if (command.split(" ").length > 2 && command.split(" ")[0].equals("update")) {
            return new CommandUpdateDescription(command);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
