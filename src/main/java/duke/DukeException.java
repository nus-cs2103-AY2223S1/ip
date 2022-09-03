package duke;

/**
 * A custom exception thrown by Duke
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class DukeException extends Exception {

    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DukeException(String message) {
        super(message);
    }
}
