package zeus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import zeus.command.AddCommand;
import zeus.command.ByeCommand;
import zeus.command.Command;
import zeus.command.DeleteCommand;
import zeus.command.FindCommand;
import zeus.command.ListCommand;
import zeus.command.MarkDoneCommand;
import zeus.command.MarkUndoneCommand;
import zeus.command.OnDateCommand;
import zeus.command.TagCommand;
import zeus.command.UntagCommand;
import zeus.task.Deadline;
import zeus.task.Event;
import zeus.task.Todo;


/**
 * Handles the input given by the user.
 *
 * @author Derrick Khoo
 */
public class Parser {
    public static final String ERROR_DID_NOT_SPECIFY_WHAT = "Did you forget to specify what?";

    /**
     * Handles the case where the user inputs a <code>Todo</code>.
     *
     * @param   input the input from the user
     * @return  the <code>Todo</code> that the user specified
     * @throws ZeusException if there is an error parsing the input from the user
     */
    public static Todo handleTodo(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input);
    }

    /**
     * Handles the case where the user inputs a <code>Event</code>.
     *
     * @param   input the input from the user
     * @return  the <code>Event</code> that the user specified
     * @throws ZeusException if there is an error parsing the input from the user
     */
    public static Event handleEvent(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException(ERROR_DID_NOT_SPECIFY_WHAT);
        }
        String[] modifiedInput = input.split("/", 2);
        if (modifiedInput.length == 1) {
            throw new ZeusException("Did you forget to specify when your event is at?"
                    + " In yyyy-mm-dd format please.");
        }
        String description = modifiedInput[0];
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateAt = secondModifiedInput[1];
        try {
            LocalDate.parse(dateAt);
        } catch (DateTimeParseException e) {
            throw new ZeusException("Invalid date format entered! Use YYYY-MM-DD format please!");
        }
        LocalDate localDateAt = LocalDate.parse(dateAt);
        return new Event(description, localDateAt);
    }

    /**
     * Handles the case where the user inputs a <code>Deadline</code>.
     *
     * @param   input the input from the user
     * @return  the <code>Deadline</code> that the user specified
     * @throws ZeusException if there is an error parsing the input from the user
     */
    public static Deadline handleDeadline(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException(ERROR_DID_NOT_SPECIFY_WHAT);
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new ZeusException("Did you forget to specify when your deadline for this is due by?"
            + " In yyyy-mm-dd format please.");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateBy = secondModifiedInput[1];
        try {
            LocalDate.parse(dateBy);
        } catch (DateTimeParseException e) {
            throw new ZeusException("Invalid date format entered! Use YYYY-MM-DD format please!");
        }
        LocalDate localDateBy = LocalDate.parse(dateBy);
        return new Deadline(description, localDateBy);
    }

    /**
     * Handles the number input given from the user.
     *
     * @param   input the input from the user
     * @return  the integer that the user specified
     * @throws ZeusException if there is an error parsing the input from the user
     */
    public static int parseInt(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException("Did you forget to specify which task to delete?");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ZeusException("Come on. Give me a number instead! Like 1 or 3!");
        }
    }

    /**
     * Handles the case where user inputs a <code>Find</code>.
     *
     * @param input the input string from the user
     * @return the string from the user
     * @throws ZeusException if there is an error in the input given from the user
     */
    public static String handleFind(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException("Did you forget to specify what you are looking for?");
        } else {
            return input;
        }
    }

    /**
     * Handles the case where user inputs a tag command.
     *
     * @param input the given input command from the user
     * @return a formatted array of Strings
     * @throws ZeusException if input  from user is invalid
     */
    public static String[] handleTag(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException("Please specify which task to tag and to tag as what");
        }
        String[] strArray = input.split(" ", 2);
        if (strArray.length == 1) {
            throw new ZeusException("Hello! What do you want to tag it with?");
        }
        return strArray;
    }

    /**
     * Handles the case where user inputs a untag command.
     *
     * @param input the input from the user
     * @return the integer index of the task user wants to untag
     * @throws ZeusException if input from user is invalid
     */
    public static int handleUntag(String input) throws ZeusException {
        if (input.isBlank()) {
            throw new ZeusException("Maybe you want to specify which task to untag?");
        }
        return Integer.parseInt(input);
    }
    /**
     * Handles the reading of the input from the user.
     *
     * @param   input the input from the user
     * @return  the <code>Command</code> corresponding to the input from the user
     * @throws ZeusException if there is an error parsing the input from the user
     */
    public static Command parse(String input) throws ZeusException {
        assert !input.isEmpty() && !input.isBlank();
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
        case "tag":
            String[] args = handleTag(second);
            int index = Integer.parseInt(args[0]);
            String tag = args[1];
            cmd = new TagCommand(tag, index);
            break;
        case "untag":
            cmd = new UntagCommand(handleUntag(second));
            break;
        default:
            throw new ZeusException("Invalid command entered. I don't recognize it. Sorry!");
        }
        return cmd;
    }
}
