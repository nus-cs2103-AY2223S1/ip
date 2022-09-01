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

/**
 * The Parser class parses the input received when running DUke.
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
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else {
            String[] split = input.split(" ", 2);
            if (split.length > 0 && Arrays.asList(validInputs).contains(split[0])) {
                switch (split[0]) {
                case "delete": // Checks for delete
                    int index = Integer.parseInt(split[1]) - 1;
                    return new DeleteCommand(index);
                case "mark": // Checks for mark
                    index = Integer.parseInt(split[1]) - 1;
                    return new MarkCommand(index);
                case "unmark": // Checks for unmark
                    index = Integer.parseInt(split[1]) - 1;
                    return new UnmarkCommand(index);
                case "todo": // Checks for Todo
                    if (split.length < 2) {
                        throw new DukeException("todo");
                    } else {
                        return new TodoCommand(split[1]);
                    }
                case "deadline": // Checks for Deadline
                    if (split.length < 2) {
                        throw new DukeException("deadline");
                    } else {
                        String[] temp = split[1].split(" /by ", 2);
                        return new DeadlineCommand(temp[0], temp[1]);
                    }
                case "event": // Checks for Event
                    if (split.length < 2) {
                        throw new DukeException("event");
                    } else {
                        String[] temp = split[1].split(" /at ", 2);
                        return new EventCommand(temp[0], temp[1]);
                    }
                case "find":
                    if (split.length < 2) {
                        throw new DukeException("find");
                    } else {
                        return new FindCommand(split[1]);
                    }
                default: // Default case
                    throw new DukeException();
                }
            } else {
                throw new DukeException(); // Invalid input
            }
        }
    }
}
