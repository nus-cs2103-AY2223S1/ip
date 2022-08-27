package duke;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import duke.command.AddDeadLineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkItemCommand;
import duke.command.UnMarkItemCommand;

/**
 * Represents a Parser to parse user inputs.
 */
public class Parser {
    /** ArrayList of unknown commands */
    private static final List<String> UNKNOWN_COMMANDS = Arrays.asList("todo", "deadline", "event");

    /**
     * Parses the user inputs.
     *
     * @param command user input.
     * @return Command object for the next action.
     * @throws DukeException exception thrown when inputs are not recognised.
     */
    public Command parse(String command) throws DukeException {
        String task;
        String[] inputs = command.split(" ");
        if (inputs.length == 1) {
            if (command.equals("bye")) {
                return new ByeCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (UNKNOWN_COMMANDS.contains(command)) {
                throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            }
        } else if (inputs.length == 2) {
            if (inputs[0].equals("mark")) {
                return new MarkItemCommand(Integer.parseInt(inputs[1]));
            } else if (inputs[0].equals("unmark")) {
                return new UnMarkItemCommand(Integer.parseInt(inputs[1]));
            } else if (inputs[0].equals("delete")) {
                return new DeleteCommand(Integer.parseInt(inputs[1]));
            } else if (inputs[0].equals("list")) {
                return new ListCommand(true, LocalDate.parse(inputs[1]));
            } else if (inputs[0].equals("find")) {
                return new FindCommand(inputs);
            }
        } else {
            switch (inputs[0]) {
            case "todo":
                return new AddTodoCommand(inputs);
            case "deadline":
                return new AddDeadLineCommand(inputs);
            case "event":
                return new AddEventCommand(inputs);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
