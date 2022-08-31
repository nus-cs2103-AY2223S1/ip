package exceptions;

public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String task) {
        super("Description of a " + task + " cannot be empty!\n");
    }

}
