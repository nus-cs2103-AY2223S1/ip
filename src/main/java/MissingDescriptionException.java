public class MissingDescriptionException extends DukeException {

    public MissingDescriptionException(String task) {
        super("OOPS!!! The description of a " + task + " cannot be empty");
    }
}
