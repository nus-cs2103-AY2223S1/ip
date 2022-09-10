package tuna.utility;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import tuna.TunaException;
import tuna.command.AddDeadLineCommand;
import tuna.command.AddEventCommand;
import tuna.command.AddTodoCommand;
import tuna.command.ByeCommand;
import tuna.command.Command;
import tuna.command.DeleteCommand;
import tuna.command.FindCommand;
import tuna.command.ListCommand;
import tuna.command.MarkItemCommand;
import tuna.command.SortCommand;
import tuna.command.UnMarkItemCommand;

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
     * @throws TunaException exception thrown when inputs are not recognised.
     */
    public Command parse(String command) throws TunaException {
        String[] inputs = command.split(" ");
        if (inputs.length == 1) {
            if (command.equals("bye")) {
                return new ByeCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("sort")) {
                return new SortCommand();
            } else if (UNKNOWN_COMMANDS.contains(command)) {
                throw new TunaException("I'm sorry I do not understand the command. The description of a " + command
                        + " command cannot be empty.");
            }
        } else if (inputs.length == 2) {
            if (inputs[0].equals("mark")) {
                return new MarkItemCommand(Integer.parseInt(inputs[1]));
            } else if (inputs[0].equals("unmark")) {
                return new UnMarkItemCommand(Integer.parseInt(inputs[1]));
            } else if (inputs[0].equals("delete")) {
                return new DeleteCommand(Integer.parseInt(inputs[1]));
            } else if (inputs[0].equals("list")) {
                return new ListCommand(LocalDate.parse(inputs[1]));
            } else if (inputs[0].equals("find")) {
                return new FindCommand(inputs);
            }
        }
        switch (inputs[0]) {
        case "todo":
            return new AddTodoCommand(inputs);
        case "deadline":
            return new AddDeadLineCommand(inputs);
        case "event":
            return new AddEventCommand(inputs);
        default:
            throw new TunaException("Oops! Sorry! I do not know what that means");
        }
    }
}
