package duke;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.MarkUndoneCommand;
import duke.command.OnDateCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * Handles the input given by the user.
 *
 * @author Derrick Khoo
 */
public class Parser {

    /**
     * Handles the case where the user inputs a <code>Todo</code>.
     *
     * @param   input the input from the user
     * @return  the <code>Todo</code> that the user specified
     * @throws DukeException if there is an error parsing the input from the user
     */
    public static Todo handleTodo(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input);
    }

    /**
     * Handles the case where the user inputs a <code>Event</code>.
     *
     * @param   input the input from the user
     * @return  the <code>Event</code> that the user specified
     * @throws DukeException if there is an error parsing the input from the user
     */
    public static Event handleEvent(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new DukeException("Did you forget to specify when your event is at?"
            + " In yyyy-mm-dd format please.");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateAt = secondModifiedInput[1];
        LocalDate localDateAt = LocalDate.parse(dateAt);
        return new Event(description, localDateAt);
    }

    /**
     * Handles the case where the user inputs a <code>Deadline</code>.
     *
     * @param   input the input from the user
     * @return  the <code>Deadline</code> that the user specified
     * @throws DukeException if there is an error parsing the input from the user
     */
    public static Deadline handleDeadline(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new DukeException("Did you forget to specify when your deadline for this is due by?"
            + " In yyyy-mm-dd format please.");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateBy = secondModifiedInput[1];
        LocalDate localDateBy = LocalDate.parse(dateBy);
        return new Deadline(description, localDateBy);
    }

    /**
     * Handles the number input given from the user.
     *
     * @param   input the input from the user
     * @return  the integer that the user specified
     * @throws DukeException if there is an error parsing the input from the user
     */
    public static int parseInt(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify which task to delete?");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("Come on. Give me a number instead! Like 1 or 3!");
        }
    }

    /**
     * Handles the case where user inputs a <code>Find</code>.
     *
     * @param input the input string from the user
     * @return the string from the user
     * @throws DukeException if there is an error in the input given from the user
     */
    public static String handleFind(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what you are looking for?");
        } else {
            return input;
        }
    }

    /**
     * Handles the reading of the input from the user.
     *
     * @param   input the input from the user
     * @return  the <code>Command</code> corresponding to the input from the user
     * @throws DukeException if there is an error parsing the input from the user
     */
    public static Command parse(String input) throws DukeException {
        String[] strArray = input.split(" ", 2);
        String first = strArray[0];
        String second = "";
        if (strArray.length == 2) {
            second = strArray[1];
        }
        Command cmd;
        switch (first) {
        case "bye":
            cmd = new ByeCommand();
            break;
        case "list":
            cmd = new ListCommand();
            break;
        case "mark":
            cmd = new MarkDoneCommand(parseInt(second));
            break;
        case "unmark":
            cmd = new MarkUndoneCommand(parseInt(second));
            break;
        case "todo":
            cmd = new AddCommand(handleTodo(second));
            break;
        case "deadline":
            cmd = new AddCommand(handleDeadline(second));
            break;
        case "event":
            cmd = new AddCommand(handleEvent(second));
            break;
        case "delete":
            cmd = new DeleteCommand(parseInt(second));
            break;
        case "on":
            cmd = new OnDateCommand(LocalDate.parse(second));
            break;
        case "find":
            cmd = new FindCommand(handleFind(second));
            break;
        default:
            throw new DukeException("Invalid command entered. I don't recognize it. Sorry!");
        }
        return cmd;
    }
}
