package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
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


            switch (command) {
            case "delete":
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            case "bye":
                return new ExitCommand();
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(fullCommand);
            case "mark":
            case "unmark":
                return new MarkCommand(command, Integer.parseInt(parts[1]) - 1);
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(fullCommand.substring(5));
            case "help":
                return new HelpCommand();
            default:
                return new EmptyCommand();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("You did not specify which task to perform action.\n");
        }
    }
}
