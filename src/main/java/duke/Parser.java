package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

/**
 * Breaks input by user into smaller parts to find respective actions to be taken.
 */
public class Parser {

    /**
     * Constructs a Parser instance without initiating any parameter
     */
    public Parser() {

    }

    /**
     * Split the fullCommand into smaller parts and decide which Command to be created and returned.
     *
     * @param fullCommand the whole command input by user.
     * @return the corresponding Command subclasses.
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] parts = fullCommand.split(" ", 0);
            String command = parts[0];
            String mainCommand = "";
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                mainCommand = "Add";
            } else if (command.equals("delete")) {
                mainCommand = "Delete";
            } else if (command.equals("bye")) {
                mainCommand = "Exit";
            } else if (command.equals("mark") || command.equals("unmark")) {
                mainCommand = "Mark";
            } else if (command.equals("list")) {
                mainCommand = "List";
            } else if (command.equals("find")) {
                mainCommand = "Find";
            }

            switch (mainCommand) {
            case "Delete":
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            case "Exit":
                return new ExitCommand();
                case "Add":
                    return new AddCommand(fullCommand);
                case "Mark":
                    return new MarkCommand(command, Integer.parseInt(parts[1]) - 1);
                case "List":
                    return new ListCommand();
                case "Find":
                    return new FindCommand(fullCommand.substring(5));
                default:
                    return new EmptyCommand();
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("You did not specify which task to perform action.\n");
        }
    }
}
