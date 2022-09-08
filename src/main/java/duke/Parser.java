package duke;

import java.util.Arrays;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.UndoCommand;

/**
 * The Parser class parses the input received when running Duke.
 */

public class Parser {
    private static String[] validInputs = {"delete", "mark", "unmark", "todo", "deadline", "event", "find"};
    private TaskList list;

    /**
     * Constructor for Parser.
     *
     * @param list
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * @param input The String input from the user.
     * @return Whether "bye" has been inputted.
     */
    public static Command parse(String input) throws DukeException {
        String[] split = input.split(" ", 2);
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("undo")) {
            return new UndoCommand(StoreUndo.getUndo());
        }
        else if (split.length > 0 && Arrays.asList(validInputs).contains(split[0])) {
            if (split.length < 2) {
                throw new DukeException(split[0]);
            }
            switch (split[0]) {
                case "delete": // Checks for delete
                    int index = Integer.parseInt(split[1]) - 1;
                    assert index >= 0 : "index should be >= 0";
                    return new DeleteCommand(index);
                case "mark": // Checks for mark
                    index = Integer.parseInt(split[1]) - 1;
                    assert index >= 0 : "index should be >= 0";
                    return new MarkCommand(index);
                case "unmark": // Checks for unmark
                    index = Integer.parseInt(split[1]) - 1;
                    assert index >= 0 : "index should be >= 0";
                    return new UnmarkCommand(index);
                case "todo": // Checks for Todo
                    return new TodoCommand(split[1]);
                case "deadline": // Checks for Deadline
                    String[] temp = split[1].split(" /by ", 2);
                    return new DeadlineCommand(temp[0], temp[1]);
                case "event": // Checks for Event
                    temp = split[1].split(" /at ", 2);
                    return new EventCommand(temp[0], temp[1]);
                case "find":
                        return new FindCommand(split[1]);
                default: // Default case
                    throw new DukeException();
            }
        } else {
            throw new DukeException(); // Invalid input
        }
    }
}
