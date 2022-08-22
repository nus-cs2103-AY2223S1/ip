public class InvalidInputException extends DukeException {
    private final static String message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public InvalidInputException() {
        super(message);
    }
}
