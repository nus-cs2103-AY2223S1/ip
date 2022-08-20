package duke.exception;

public class DukeException extends Exception {
    public DukeException() {
        super("☹ OOPS!!! I'm Sorry, Yem doesn't know what that means.");
    }

    public DukeException(String error) {
        super("☹ OOPS!!! " + error);
    }
}
