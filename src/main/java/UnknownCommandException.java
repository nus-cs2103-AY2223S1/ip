public class UnknownCommandException extends DukeException {
    private static final String DESCRIPTION = "I'm sorry, but I don't know what that means :-(";

    UnknownCommandException() {
        super(DESCRIPTION);
    }
}
