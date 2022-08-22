public class InvalidEventException extends DukeException {
    private final static String message = "☹ OOPS!!! The description or date of an event cannot be empty.";
    public InvalidEventException() {
        super(message);
    }
}
