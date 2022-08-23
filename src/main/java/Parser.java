import java.text.NumberFormat;
import java.time.LocalDate;

public class Parser {

    public static Todo handleTodo(String input) throws Duke.DukeException {
        if (input.length() == 0) {
            throw new Duke.DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input);
    }

    public static Event handleEvent(String input) throws Duke.DukeException {
        if (input.length() == 0) {
            throw new Duke.DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new Duke.DukeException("Did you forget to specify when your event is at?");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateAt = secondModifiedInput[1];
        LocalDate localDateAt = LocalDate.parse(dateAt);
        return new Event(description, localDateAt);
    }

    public static Deadline handleDeadline(String input) throws Duke.DukeException {
        if (input.length() == 0) {
            throw new Duke.DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new Duke.DukeException("Did you forget to specify when your deadline for this is due by?");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateBy = secondModifiedInput[1];
        LocalDate localDateBy = LocalDate.parse(dateBy);
        return new Deadline(description, localDateBy);
    }

    public static int parseInt(String input) throws Duke.DukeException {
        if (input.length() == 0) {
            throw new Duke.DukeException("Did you forget to specify which task to delete?");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new Duke.DukeException("Come on. Give me a number instead! Like 1 or 3!");
        }
    }

    public static Command parse(String input) throws Duke.DukeException {
        String[] strArray = input.split(" ", 2);
        String first = strArray[0];
        String second = "";
        if (strArray.length == 2) {
            second = strArray[1];
        }
        Command cmd;
        switch (first) {
        case ("bye"): {
            cmd = new ByeCommand();
            break;
        }
        case ("list"): {
            cmd = new ListCommand();
            break;
        }
        case ("mark"): {
            cmd = new MarkDoneCommand(parseInt(second));
            break;
        }
        case ("unmark"): {
            cmd = new MarkUndoneCommand(parseInt(second));
            break;
        }
        case ("todo"): {
            cmd = new AddCommand(handleTodo(second));
            break;
        }
        case ("deadline"): {
            cmd = new AddCommand(handleDeadline(second));
            break;
        }
        case ("event"): {
            cmd = new AddCommand(handleEvent(second));
            break;
        }
        case ("delete"): {
            cmd = new DeleteCommand(parseInt(second));
            break;
        }
        case ("on"): {
            cmd = new OnDateCommand(LocalDate.parse(second));
            break;
        }
        default: {
            throw new Duke.DukeException("Invalid command entered. I don't recognize it. Sorry!");
        }
        }
        return cmd;
    }
}
