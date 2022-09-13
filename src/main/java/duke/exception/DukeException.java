package duke.exception;

/**
 * Represents a DukeException class.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeException extends Exception {

    /**
     * Represents a message.
     */
    private final String msg;

    /**
     * Constructor for DukeException.
     * @param message Message to determine the error.
     */
    public DukeException(String message) {
        switch (message) {
        case "todo":
            this.msg = "OOPS!!! The description of a todo cannot be empty.";
            break;
        case "deadline":
            this.msg = "OOPS!!! The description of a deadline cannot be empty.";
            break;
        case "event":
            this.msg = "OOPS!!! The description of an event cannot be empty.";
            break;
        case "delete":
            this.msg = "OOPS!!! The description of a delete cannot be empty.";
            break;
        case "mark":
            this.msg = "OOPS!!! The description of a mark cannot be empty.";
            break;
        case "unmark":
            this.msg = "OOPS!!! The description of an unmark cannot be empty.";
            break;
        case "find":
            this.msg = "OOPS!!! The description of a find cannot be empty.";
            break;
        case "deadline format":
            this.msg = "OOPS!!! The formatting of the deadline message is wrong (deadline 'thing' /by yyyy-mm-dd).";
            break;
        case "event format":
            this.msg = "OOPS!!! The formatting of the event message is wrong (event 'event' /at yyyy-mm-dd).";
            break;
        case "unknown":
            this.msg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case "non integer input when marking":
        case "non integer input when deleting":
            this.msg = "OOPS!!! You did not give an integer for the task number :-(";
            break;
        case "index out of bounds":
            this.msg = "OOPS!!! The index number is out of bounds :-(";
            break;
        default:
            this.msg = "unknown error";
            break;
        }
    }

    /**
     * Represents the string format of the exception.
     * @return string representing the exception.
     */
    @Override
    public String toString() {
        return msg;
    }
}
