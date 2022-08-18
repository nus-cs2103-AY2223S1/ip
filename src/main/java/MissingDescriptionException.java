public class MissingDescriptionException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.";
    }
}
