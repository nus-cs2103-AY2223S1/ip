public class OutOfBoundException extends DukeException {
    private static final String DESCRIPTION = "The provided index number is invalid!";

    OutOfBoundException() {
        super(DESCRIPTION);
    }
}
