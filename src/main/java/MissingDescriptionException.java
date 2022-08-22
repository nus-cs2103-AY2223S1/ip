public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.";
    }
}
