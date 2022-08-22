package duke.ui;

import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.Command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        ELSE
    }

    /**
     * Static method that first parses the input into something the program can understand
     * and then returns a Command.
     * @param command A String command that the user inputs
     * @return The Command that user wants to execute.
     * @throws DukeException If there is an invalid command.
     * @throws DateTimeParseException If there is an error in Date and Time input.
     */
    public static Command parse(String command) throws DukeException, DateTimeParseException {
        switch (getInput(command)) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            int markNum = Integer.parseInt(command.replace("mark ", ""));
            return new MarkCommand(markNum - 1);
        case UNMARK:
            int unMarkNum = Integer.parseInt(command.replace("unmark ", ""));
            return new UnMarkCommand(unMarkNum - 1);
        case TODO:
            String tDes = command.replace("todo", "");
            return new AddCommand(new Todo(tDes));
        case EVENT:
            String[] eDes = command.replace("event", "").split(" /at ");
            return new AddCommand(new Event(eDes[0], LocalDate.parse(eDes[1])));
        case DEADLINE:
            String[] dDes = command.replace("deadline", "").split(" /by ");
            return new AddCommand(new Deadline(dDes[0], LocalDate.parse(dDes[1])));
        case DELETE:
            int delNum = Integer.parseInt(command.replace("delete ", ""));
            return new DeleteCommand(delNum);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Static method that converts user's String input into and enum that program understands.
     * @param s The user input.
     * @return An enum that the program understands.
     */
    private static Inputs getInput(String s) {
        if (s.equals("bye")) {
            return Inputs.BYE;
        } else if (s.equals("list")) {
            return Inputs.LIST;
        } else if (s.startsWith("mark")) {
            return Inputs.MARK;
        } else if (s.startsWith("unmark")) {
            return Inputs.UNMARK;
        } else if (s.startsWith("todo")) {
            return Inputs.TODO;
        } else if (s.startsWith("deadline")) {
            return Inputs.DEADLINE;
        } else if (s.startsWith("event")) {
            return Inputs.EVENT;
        } else if (s.startsWith("delete")) {
            return Inputs.DELETE;
        } else {
            return Inputs.ELSE;
        }
    }
}
