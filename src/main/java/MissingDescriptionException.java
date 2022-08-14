public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String str) {
        super("OOPS!!! The description of a " + str + " cannot be empty");
    }
}
