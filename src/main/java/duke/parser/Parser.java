package duke.parser;

import duke.command.AddItemCommand;
import duke.command.ClearScreenCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListItemsCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Encapsulates parsing of input messages.
 *
 * @author Kartikeya
 */
public class Parser {
    /**
     * Processes chat inputs using a switch statement, throwing a duke.exception.DukeException
     * on incorrect inputs.
     *
     * @param inputString String given to Apollo
     * @throws DukeException Indicates incorrect inputs
     */
    public static Command parseUserInput(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
        try {
            switch (input[0]) {
            case "bye": {
                return new ExitCommand();
            }
            case "list": {
                return new ListItemsCommand();
            }
            case "mark": {
                return new MarkCommand(input[1]);
            }
            case "unmark": {
                return new UnmarkCommand(input[1]);
            }
            case "delete": {
                return new DeleteCommand(input[1]);
            }
            case "find": {
                return new FindCommand(input);
            }
            case "clear": {
                return new ClearScreenCommand();
            }
            default: {
                return new AddItemCommand(input);
            }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing command description.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please recheck your number input, "
                    + "including trailing spaces.");
        }
    }
}
