package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * A class that parses the input from user into something program can understand.
 */
public class Parser {

    private enum Inputs {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        ELSE
    }

    /**
     * Returns eturns a Command after parsing through user's String command.
     * @param command A String command that the user inputs.
     * @return The Command that user wants to execute.
     * @throws DukeException If there is an invalid command.
     * @throws DateTimeParseException If there is an error in Date and Time input.
     */
    public static Command parse(String command) throws DukeException, DateTimeParseException {
        switch (getInput(command)) {
        case BYE:
            return new ByeCommand();
            // Fallthrough
        case LIST:
            return new ListCommand();
            // Fallthrough
        case MARK:
            int markNum = Integer.parseInt(command.replace("mark ", ""));
            return new MarkCommand(markNum - 1);
            // Fallthrough
        case UNMARK:
            int unMarkNum = Integer.parseInt(command.replace("unmark ", ""));
            return new UnMarkCommand(unMarkNum - 1);
            // Fallthrough
        case TODO:
            String tDes = command.replace("todo", "");
            return new AddCommand(new Todo(tDes));
            // Fallthrough
        case EVENT:
            String[] eDes = command.replace("event", "").split(" /at ");
            return new AddCommand(new Event(eDes[0], LocalDate.parse(eDes[1])));
            // Fallthrough
        case DEADLINE:
            String[] dDes = command.replace("deadline", "").split(" /by ");
            return new AddCommand(new Deadline(dDes[0], LocalDate.parse(dDes[1])));
            // Fallthrough
        case DELETE:
            int delNum = Integer.parseInt(command.replace("delete ", ""));
            return new DeleteCommand(delNum);
            // Fallthrough
        case FIND:
            String findKey = command.replace("find ", "");
            return new FindCommand(findKey);
            // Fallthrough
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
            // Fallthrough
        }
    }

    /**
     * Returns enum Inputs which program can understand.
     * @param command The user input.
     * @return An enum that the program understands.
     */
    private static Inputs getInput(String command) {
        if (command.equals("bye")) {
            return Inputs.BYE;
        } else if (command.equals("list")) {
            return Inputs.LIST;
        } else if (command.startsWith("mark")) {
            return Inputs.MARK;
        } else if (command.startsWith("unmark")) {
            return Inputs.UNMARK;
        } else if (command.startsWith("todo")) {
            return Inputs.TODO;
        } else if (command.startsWith("deadline")) {
            return Inputs.DEADLINE;
        } else if (command.startsWith("event")) {
            return Inputs.EVENT;
        } else if (command.startsWith("delete")) {
            return Inputs.DELETE;
        } else if (command.startsWith("find")) {
            return Inputs.FIND;
        } else {
            return Inputs.ELSE;
        }
    }
}
