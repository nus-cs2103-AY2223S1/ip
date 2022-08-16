public class OutOfBoundException extends DukeException {
    private static final String DESCRIPTION = "The provided index exceeds the total number of tasks available!";

    OutOfBoundException() {
        super(DESCRIPTION);
    }
}
